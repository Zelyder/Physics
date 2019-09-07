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
public class KeyboardPropertiesOfSteam extends Fragment implements View.OnClickListener{
    FActivity fActivity;


    public KeyboardPropertiesOfSteam() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_properties_of_steam, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnSteamDelete);
        Button btnPlus =  view.findViewById(R.id.btnSteamPlus);
        Button btnDelta =  view.findViewById(R.id.btnSteamMinus);
        ImageButton btnOk =  view.findViewById(R.id.btnSteamOk);
        Button btnSteamHeight =  view.findViewById(R.id.btnSteamHeight);
        Button btnSteamSigma =  view.findViewById(R.id.btnSteamSigma);
        Button btnSteamRo =  view.findViewById(R.id.btnSteamRo);
        Button btnSteamTwo =  view.findViewById(R.id.btnSteamTwo);
        Button btnSteamR =  view.findViewById(R.id.btnSteamR);
        Button btnSteamGravity =  view.findViewById(R.id.btnSteamGravity);
        Button btnSteamEnergy =  view.findViewById(R.id.btnSteamEnergy);
        Button btnSteamEpsilon =  view.findViewById(R.id.btnSteamEpsilon);
        Button btnSteamForce =  view.findViewById(R.id.btnSteamForce);
        Button btnSteamLength =  view.findViewById(R.id.btnSteamLength);
        Button btnSteamDistance =  view.findViewById(R.id.btnSteamDistance);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnSteamHeight.setOnClickListener(this);
        btnSteamSigma.setOnClickListener(this);
        btnSteamRo.setOnClickListener(this);
        btnSteamTwo.setOnClickListener(this);
        btnSteamR.setOnClickListener(this);
        btnSteamGravity.setOnClickListener(this);
        btnSteamEnergy.setOnClickListener(this);
        btnSteamEpsilon.setOnClickListener(this);
        btnSteamForce.setOnClickListener(this);
        btnSteamLength.setOnClickListener(this);
        btnSteamDistance.setOnClickListener(this);

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
            case R.id.btnSteamDelete:
                fActivity.delCh();
                break;
            case R.id.btnSteamOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
