package com.zelyder.physics.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.zelyder.user.physics.R;

public class HelpActivity extends AppCompatActivity {

    TextView tvHelp;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(preferences.getBoolean("cb_pref_dark_style", false)){
            setTheme(R.style.darkTheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvHelp = findViewById(R.id.tvHelp);

        switch (getIntent().getStringExtra(FActivity.TITLE)){
            case "Кинематика":
                tvHelp.setText(R.string.helpKinematics);
                break;
            case "Динамика":
                tvHelp.setText(R.string.helpDynamics);
                break;
            case "Статика":
                tvHelp.setText(R.string.helpStatics);
                break;
            case "Гидростатика":
                tvHelp.setText(R.string.helpHydroStatics);
                break;
            case "Работа, мощность, энергия":
                tvHelp.setText(R.string.helpEnergy);
                break;
            case "Молекулярная физика":
                tvHelp.setText(R.string.helpMolecularPhysics);
                break;
            case "Термодинамика":
                tvHelp.setText(R.string.helpThermodynamics);
                break;
            case "Электростатика":
                tvHelp.setText(R.string.helpElectroStatics);
                break;
            case "Законы постоянного тока":
                tvHelp.setText(R.string.helpConstantCurrent);
                break;
            case "Магнитное поле, электромагнитная индукция":
                tvHelp.setText(R.string.helpMagneticField);
                break;
            case "Механические колебания и волны":
                tvHelp.setText(R.string.helpMechanicalVibrations);
                break;
            case "Электромагнитные колебания и волны":
                tvHelp.setText(R.string.helpElectromagneticOscillations);
                break;
            case "Оптика":
                tvHelp.setText(R.string.helpOptics);
                break;
            case "Специальная теория относительности (СТО)":
                tvHelp.setText(R.string.helpSto);
                break;
            case "Квантовая физика":
                tvHelp.setText(R.string.helpQuantumPhysics);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
