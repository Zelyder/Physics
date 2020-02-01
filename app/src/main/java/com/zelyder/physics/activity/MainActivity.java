package com.zelyder.physics.activity;

import android.app.Service;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdSize;
import com.yandex.mobile.ads.AdView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zelyder.physics.help.TaskViewHolder;
import com.zelyder.user.physics.R;
public class MainActivity extends Fragment {

    private static final String TAG = "MainActivity";
    private DatabaseReference myRef;
    ProgressBar progressBar;
    TextView tvNoConnection;
    RecyclerView recyclerView;
    private AdView mAdView;
    private AdRequest mAdRequest;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.keepSynced(true);

        mAdView = rootView.findViewById(R.id.adView);


        if(!isConnected() || preferences.getBoolean(DonationActivity.PREFERENCES_ADS, false)) {
            mAdView.setVisibility(View.GONE);
            mAdView.destroy();
        }else {
            initBannerView();
            refreshBannerAd();
        }




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
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        viewHolder.setTipText(dataSnapshot.getChildrenCount() + "");
                        viewHolder.setContext(getContext());
                        checkInternet();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(),"Что-то пошло не так. Попробуйте обновить приложение",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //toolTip.removeAllViewsInLayout();
            }
        });

        checkInternet();
        return rootView;
    }

    private void initBannerView() {
        mAdView.setBlockId(getResources().getString(R.string.banner_ad_yandex_id));
        mAdView.setAdSize(AdSize.BANNER_320x50);

        mAdRequest = AdRequest.builder().build();
        mAdView.setAdEventListener(mBannerAdEventListener);
    }

    private AdEventListener mBannerAdEventListener = new AdEventListener.SimpleAdEventListener() {
        @Override
        public void onAdLoaded() {
            mAdView.setVisibility(View.VISIBLE);
        }
    };
    private void refreshBannerAd() {
        mAdView.setVisibility(View.INVISIBLE);
        mAdView.loadAd(mAdRequest);
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
    private void checkInternet() {
        if(recyclerView.getAdapter().getItemCount() == 0 && !isConnected()){
            progressBar.setVisibility(View.VISIBLE);
            tvNoConnection.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
            tvNoConnection.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mAdView.resume();
        super.onResume();
    }
}
