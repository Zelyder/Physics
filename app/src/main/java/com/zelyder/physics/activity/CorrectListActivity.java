package com.zelyder.physics.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zelyder.physics.PhysicsApp;
import com.zelyder.physics.help.CorrectListAdapter;
import com.zelyder.physics.help.CorrectListCallback;
import com.zelyder.physics.help.OnGetDataListener;
import com.zelyder.physics.model.DelFormula;
import com.zelyder.physics.model.Favorite;
import com.zelyder.physics.model.Formula;
import com.zelyder.user.physics.R;

import java.util.ArrayList;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;

public class CorrectListActivity extends AppCompatActivity {
    String TAG = "CorrectListActivity";
    RecyclerView recyclerView;
    ArrayList<Formula> listFormulas;
    ArrayList<Formula> tempListFormulas;
    private Iterator<DataSnapshot> iterable;
    private DatabaseReference myRef;
    private Realm mRealm;
    private Realm mRealmForDel;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (preferences.getBoolean("cb_pref_dark_style", false)) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_list);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.keepSynced(true);
        mRealm = Realm.getInstance(PhysicsApp.getConfigForFavorites());
        mRealmForDel = Realm.getInstance(PhysicsApp.getConfigForDelFormulas());


        listFormulas = new ArrayList<>();
        tempListFormulas = new ArrayList<>();

        recyclerView = findViewById(R.id.correctList_recyclerView);
        setUpDB(new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                recyclerView.setLayoutManager(new LinearLayoutManager(CorrectListActivity.this));
                recyclerView.setHasFixedSize(true);

                Log.d("LOL", "listFormulas = " + listFormulas.size());

                CorrectListAdapter adapter = new CorrectListAdapter(listFormulas, CorrectListActivity.this);

                adapter.setCorrectListCallback(new CorrectListCallback() {
                    @Override
                    public void liked(int position) {
                        Formula item = listFormulas.get(position);
                        mRealm.beginTransaction();
                        Favorite favorite = mRealm.createObject(Favorite.class);
                        favorite.setSection(getIntent().getStringExtra(FActivity.TITLE));
                        favorite.setName(item.getTitle());
                        favorite.setFormula(item.getFormula());
                        mRealm.commitTransaction();
                    }

                    @Override
                    public void unLiked(int position) {
                        Formula item = listFormulas.get(position);

                        mRealm.beginTransaction();
                        RealmResults<Favorite> favorites = mRealm.where(Favorite.class)
                                .equalTo("name", item.getTitle()).findAll();
                        for (int i = favorites.size() - 1; i >= 0; i--) {
                            if (favorites.get(i) != null) {
                                favorites.get(i).deleteFromRealm();
                            }
                        }
                        mRealm.commitTransaction();
                    }

                    @Override
                    public boolean getLikedState(int position) {
                        Favorite favorite = mRealm.where(Favorite.class)
                                .equalTo("name",
                                        listFormulas.get(position).getTitle())
                                .findFirst();
                        return favorite != null;

                    }

                    @Override
                    public void crossToggle(int position, boolean isDelete) {
                        Formula item = listFormulas.get(position);
                        if (isDelete) {
                            mRealmForDel.beginTransaction();
                            DelFormula delFormula = mRealmForDel.createObject(DelFormula.class);
                            delFormula.setParent(getIntent().getStringExtra(FActivity.TITLE));
                            delFormula.setName(item.getTitle());
                            mRealmForDel.commitTransaction();
                        } else {
                            mRealmForDel.beginTransaction();
                            RealmResults<DelFormula> delFormulas = mRealmForDel.where(DelFormula.class)
                                    .equalTo("name",
                                            item.getTitle()).findAll();
                            for (int i = delFormulas.size() - 1; i >= 0; i--) {
                                if (delFormulas.get(i) != null) {
                                    delFormulas.get(i).deleteFromRealm();
                                }
                            }
                            mRealmForDel.commitTransaction();
                        }
                    }

                    @Override
                    public boolean getCrossState(int position) {
                        DelFormula delFormula = mRealmForDel.where(DelFormula.class)
                                .equalTo("name",
                                        listFormulas.get(position).getTitle())
                                .findFirst();
                        return delFormula != null;
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_correct_list, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemHelp:
                Intent intent = new Intent(this, HelpActivity.class);
                intent.putExtra(FActivity.TITLE, getIntent().getStringExtra(FActivity.TITLE));
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpDB(final OnGetDataListener listener) {
        listener.onStart();

        myRef.child(getIntent().getStringExtra(FActivity.TITLE)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null && dataSnapshot.exists()){
                    iterable = dataSnapshot.getChildren().iterator();
                    for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                        DataSnapshot dataFormula = iterable.next();
                        Formula formula = new Formula(dataFormula.getKey(), String.valueOf(dataFormula
                                .getValue()));
                        Log.d("LOL",formula.getFormula());
                        listFormulas.add(formula);
                    }
                }
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
                listener.onFailed(databaseError);
            }
        });
    }



    private void setUpTestDB() {

        for (int i = 0; i < 5; i++) {

            Formula formula = new Formula("Test " + i, "a= _ω²R");

            listFormulas.add(formula);
        }


    }
}
