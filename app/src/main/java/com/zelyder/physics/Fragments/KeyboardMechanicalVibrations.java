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
public class KeyboardMechanicalVibrations extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardMechanicalVibrations() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_mechanical_vibrations, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnMechanicalVibrationsDelete);
        Button btnPlus =  view.findViewById(R.id.btnMechanicalVibrationsPlus);
        Button btnPi =  view.findViewById(R.id.btnMechanicalVibrationsPi);
        ImageButton btnOk =  view.findViewById(R.id.btnMechanicalVibrationsOk);
        Button btnPeriod =  view.findViewById(R.id.btnMechanicalVibrationsPeriod);
        Button btnFrequency =  view.findViewById(R.id.btnMechanicalVibrationsFrequency);
        Button btnOmega =  view.findViewById(R.id.btnMechanicalVibrationsOmega);
        Button btnOne =  view.findViewById(R.id.btnMechanicalVibrationsOne);
        Button btnMass =  view.findViewById(R.id.btnMechanicalVibrationsMass);
        Button btnK =  view.findViewById(R.id.btnMechanicalVibrationsK);
        Button btnLength =  view.findViewById(R.id.btnMechanicalVibrationsLength);
        Button btnTwo =  view.findViewById(R.id.btnMechanicalVibrationsTwo);
        Button btnFall =  view.findViewById(R.id.btnMechanicalVibrationsFall);
        Button btnLambda =  view.findViewById(R.id.btnMechanicalVibrationsLambda);
        Button btnSpeed =  view.findViewById(R.id.btnMechanicalVibrationsSpeed);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnPi.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnPeriod.setOnClickListener(this);
        btnFrequency.setOnClickListener(this);
        btnOmega.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnLength.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnFall.setOnClickListener(this);
        btnLambda.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);

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
            case R.id.btnMechanicalVibrationsDelete:
                fActivity.delCh();
                break;
            case R.id.btnMechanicalVibrationsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
