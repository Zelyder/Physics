package com.zelyder.physics.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.zelyder.physics.PhysicsApp;
import com.zelyder.physics.model.Favorite;
import com.zelyder.user.physics.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class TabbedActivity extends AppCompatActivity {

    private Realm mRealm;

    private boolean fromSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(preferences.getBoolean("cb_pref_dark_style", false)){
            setTheme(R.style.darkTheme_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout =  findViewById(R.id.tabs);

        mRealm = Realm.getInstance(PhysicsApp.getConfigForFavorites());

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));




    }

    @Override
    protected void onResume() {
        super.onResume();

        if(fromSettings){
            fromSettings = false;
            Intent intent = new Intent(this,TabbedActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAbout:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemFavorites:
                RealmResults<Favorite> favorites = mRealm.where(Favorite.class).findAll();
                if(!favorites.isEmpty()) {
                    Intent intent1 = new Intent(this, FActivity.class);
                    intent1.putExtra(FActivity.BOOL, true);
                    startActivity(intent1);
                }else {
                    Toast.makeText(this,"Нет избранных формул",Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.itemSettings:
                startActivity(new Intent(this, SettingsActivity.class));
                fromSettings = true;
                return true;
            case R.id.itemPrivacy_policy:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/e/2PACX-1vSwBOKdmLDNHqNnPTZ1AZLuKRTIzsl1OxPUQUJuTRiiRw_4s2rhh-KdI8013MT07HNmpajFeMb4tQ_o/pub")));
                fromSettings = true;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MainActivity();
                case 1:
                    return new TabCorrectList();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Тест";
                case 1:
                    return "Правильные формулы";
            }
            return null;
        }
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
            TextView textView =  rootView.findViewById(R.id.section_label);
            if (getArguments() != null) {
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            }
            return rootView;
        }
    }

}

