package com.zelyder.physics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.zelyder.user.physics.R;

public class SettingsFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);



        CheckBoxPreference cbPrefDarkStyle = (CheckBoxPreference) findPreference("cb_pref_dark_style");
        final SwitchPreference swConsider = (SwitchPreference) findPreference("pref_sw_consider");

        cbPrefDarkStyle.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue.equals(true)){
                    restartApp();
                }else {
                    restartApp();
                }
                return true;
            }
        });

        if(swConsider.isChecked()){
            swConsider.setTitle(R.string.pref_sw_consider_on);
        }else {
            swConsider.setTitle(R.string.pref_sw_consider_off);
        }

        swConsider.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue.equals(true)){
                    swConsider.setTitle(R.string.pref_sw_consider_on);
                }else {
                    swConsider.setTitle(R.string.pref_sw_consider_off);
                }
                return true;
            }
        });


    }
    void restartApp(){
        Intent intent = new Intent(getActivity(),SettingsActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
