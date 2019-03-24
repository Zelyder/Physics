package com.zelyder.physics.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.zelyder.physics.activity.FActivity;
import com.zelyder.user.physics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeyboardHydrostatics extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardHydrostatics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity =(FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_hydrostatics, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnHydrostaticsDelete);
        Button btnPlus =  view.findViewById(R.id.btnHydrostaticsPlus);
        Button btnPressure =  view.findViewById(R.id.btnHydrostaticsPressure);
        ImageButton btnOk =  view.findViewById(R.id.btnHydrostaticsOk);
        Button btnForce =  view.findViewById(R.id.btnHydrostaticsForce);
        Button btnArea =  view.findViewById(R.id.btnHydrostaticsArea);
        Button btnDensity =  view.findViewById(R.id.btnHydrostaticsDensity);
        Button btnFall =  view.findViewById(R.id.btnHydrostaticsFall);
        Button btnHeight =  view.findViewById(R.id.btnHydrostaticsHeight);
        Button btnVolume =  view.findViewById(R.id.btnHydrostaticsVolume);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnPressure.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnForce.setOnClickListener(this);
        btnArea.setOnClickListener(this);
        btnDensity.setOnClickListener(this);
        btnFall.setOnClickListener(this);
        btnHeight.setOnClickListener(this);
        btnVolume.setOnClickListener(this);

        btnOk.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fActivity.LongClickBtnOk();
                return true;
            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHydrostaticsDelete:
                fActivity.delCh();
                break;
            case R.id.btnHydrostaticsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }

    }
}
