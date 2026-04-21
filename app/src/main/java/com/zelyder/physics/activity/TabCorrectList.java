package com.zelyder.physics.activity;


import android.app.Service;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zelyder.physics.help.TabCorrectViewHolder;
import com.zelyder.physics.R;
import com.zelyder.physics.help.WrapContentLinearLayoutManager;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabCorrectList extends Fragment {

    private DatabaseReference myRef;
    ProgressBar progressBar;
    TextView tvNoConnection;
    RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<String, TabCorrectViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_correct_list, container, false);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.keepSynced(true);

        recyclerView = rootView.findViewById(R.id.correct_recyclerView);
        progressBar = rootView.findViewById(R.id.correct_progressBar);
        tvNoConnection = rootView.findViewById(R.id.correct_tvNoConnection);

        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(rootView.getContext()));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<String> options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(myRef.child("Список"), String.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<String, TabCorrectViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final TabCorrectViewHolder viewHolder, int position, @NonNull String title) {
                Log.d("LOL",title);
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

                    }
                });
            }

            @NonNull
            @Override
            public TabCorrectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item, parent, false);
                return new TabCorrectViewHolder(view);
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
        if(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() == 0 && !isConnected()){
            progressBar.setVisibility(View.VISIBLE);
            tvNoConnection.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
            tvNoConnection.setVisibility(View.GONE);
        }
    }

}
