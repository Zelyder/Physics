package com.zelyder.physics.activity;

import android.app.Service;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zelyder.physics.help.TaskViewHolder;
import com.zelyder.user.physics.R;

import java.util.Objects;

public class MainActivity extends Fragment {

    private DatabaseReference myRef;
    ProgressBar progressBar;
    TextView tvNoConnection;
    RecyclerView recyclerView;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.keepSynced(true);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        progressBar = rootView.findViewById(R.id.progressBar);
        tvNoConnection = rootView.findViewById(R.id.tvNoConnection);

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setHasFixedSize(true);

        final FirebaseRecyclerAdapter<String, TaskViewHolder> adapter;

        adapter = new FirebaseRecyclerAdapter<String, TaskViewHolder>(String.class, R.layout.item,
                TaskViewHolder.class, myRef.child("Список")) {
            @Override
            protected void populateViewHolder(final TaskViewHolder viewHolder, String title, final int position) {
                viewHolder.mTitleTask.setText(title);
                myRef.child(title).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        viewHolder.setTipText(dataSnapshot.getChildrenCount() + "");
                        viewHolder.setContext(getContext());
                        checkInternet();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(),"Что-то пошло не так. Попробуйте обновить приложение",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

        checkInternet();
        return rootView;
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                Objects.requireNonNull(getActivity()).getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            return info != null && info.getState() == NetworkInfo.State.CONNECTED;
        }
        return false;
    }
    private void checkInternet() {
        Log.d("LOL", "count " + String.valueOf(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()) +
                "\n connect " + !isConnected());
        if(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() == 0 && !isConnected()){
            progressBar.setVisibility(View.VISIBLE);
            tvNoConnection.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
            tvNoConnection.setVisibility(View.GONE);
        }
    }
}
