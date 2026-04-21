package com.zelyder.physics.activity;

import android.app.Service;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zelyder.physics.help.TaskViewHolder;
import com.zelyder.physics.R;
import com.zelyder.physics.help.WrapContentLinearLayoutManager;

import java.util.Objects;

public class MainActivity extends Fragment {

    private DatabaseReference myRef;
    ProgressBar progressBar;
    TextView tvNoConnection;
    RecyclerView recyclerView;
    SharedPreferences preferences;
    private FirebaseRecyclerAdapter<String, TaskViewHolder> adapter;

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

        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(rootView.getContext()));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<String> options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(myRef.child("Список"), String.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<String, TaskViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final TaskViewHolder viewHolder, int position, @NonNull String title) {
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

            @NonNull
            @Override
            public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item, parent, false);
                return new TaskViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);

        checkInternet();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                requireActivity().getSystemService(Service.CONNECTIVITY_SERVICE);
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
