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
public class KeyboardThermodynamics extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardThermodynamics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_thermodynamics, container, false);

        ImageButton btnDelete = view.findViewById(R.id.btnThermodynamicsDelete);
        Button btnPlus =  view.findViewById(R.id.btnThermodynamicsPlus);
        Button btnMinus =  view.findViewById(R.id.btnThermodynamicsMinus);
        ImageButton btnOk =  view.findViewById(R.id.btnThermodynamicsOk);
        Button btnHeat =  view.findViewById(R.id.btnThermodynamicsHeat);
        Button btnInternal_energy =  view.findViewById(R.id.btnThermodynamicsInternal_energy);
        Button btnWork =  view.findViewById(R.id.btnThermodynamicsWork);
        Button btnDelta =  view.findViewById(R.id.btnThermodynamicsDelta);
        Button btnFrequency =  view.findViewById(R.id.btnThermodynamicsFrequency);
        Button btnR =  view.findViewById(R.id.btnThermodynamicsR);
        Button btnTemperature =  view.findViewById(R.id.btnThermodynamicsTemperature);
        Button btnTwo =  view.findViewById(R.id.btnThermodynamicsTwo);
        Button btnSpecific_heat =  view.findViewById(R.id.btnThermodynamicsSpecific_heat);
        Button btnMass =  view.findViewById(R.id.btnThermodynamicsMass);
        Button btnTime =  view.findViewById(R.id.btnThermodynamicsTime);
        Button btnThree =  view.findViewById(R.id.btnThermodynamicsThree);
        Button btnLambda =  view.findViewById(R.id.btnThermodynamicsLambda);
        Button btnL =  view.findViewById(R.id.btnThermodynamicsL);
        Button btnCharge =  view.findViewById(R.id.btnThermodynamicsCharge);
        Button btnFive =  view.findViewById(R.id.btnThermodynamicsFive);
        Button btnPressure =  view.findViewById(R.id.btnThermodynamicsPressure);
        Button btnVolume =  view.findViewById(R.id.btnThermodynamicsVolume);
        Button btnECE =  view.findViewById(R.id.btnThermodynamicsECE);
        Button btnPhi =  view.findViewById(R.id.btnThermodynamicsPhi);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnHeat.setOnClickListener(this);
        btnInternal_energy.setOnClickListener(this);
        btnWork.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnFrequency.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnTemperature.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnSpecific_heat.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnLambda.setOnClickListener(this);
        btnL.setOnClickListener(this);
        btnCharge.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnPressure.setOnClickListener(this);
        btnVolume.setOnClickListener(this);
        btnECE.setOnClickListener(this);
        btnPhi.setOnClickListener(this);

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
            case R.id.btnThermodynamicsDelete:
                fActivity.delCh();
                break;
            case R.id.btnThermodynamicsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
