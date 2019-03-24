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
public class KeyboardElectrostatics extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardElectrostatics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_electrostatics, container, false);

        ImageButton btnDelete = view.findViewById(R.id.btnElectrostaticsDelete);
        Button btnSquare =  view.findViewById(R.id.btnElectrostaticsSquare);
        Button btnMinus =  view.findViewById(R.id.btnElectrostaticsMinus);
        ImageButton btnOk =  view.findViewById(R.id.btnElectrostaticsOk);
        Button btnForce =  view.findViewById(R.id.btnElectrostaticsForce);
        Button btnK =  view.findViewById(R.id.btnElectrostaticsK);
        Button btnCharge =  view.findViewById(R.id.btnElectrostaticsCharge);
        Button btnTwo =  view.findViewById(R.id.btnElectrostaticsTwo);
        Button btnRadius =  view.findViewById(R.id.btnElectrostaticsRadius);
        Button btnEnergy =  view.findViewById(R.id.btnElectrostaticsEnergy);
        Button btnInternal_energy =  view.findViewById(R.id.btnElectrostaticsInternal_energy);
        Button btnPhi =  view.findViewById(R.id.btnElectrostaticsPhi);
        Button btnD =  view.findViewById(R.id.btnElectrostaticsD);
        Button btnElectric_capacity =  view.findViewById(R.id.btnElectrostaticsElectric_capacity);
        Button btnEpsilon =  view.findViewById(R.id.btnElectrostaticsEpsilon);
        Button btnArea =  view.findViewById(R.id.btnElectrostaticsArea);
        Button btnEnergyOfCapacitor =  view.findViewById(R.id.btnElectrostaticsEnergyOfCapacitor);
        Button btnWork =  view.findViewById(R.id.btnElectrostaticsWork);
        Button btnN =  view.findViewById(R.id.btnElectrostaticsN);
        Button btnDelta =  view.findViewById(R.id.btnElectrostaticsDelta);

        btnDelete.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnForce.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnCharge.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnRadius.setOnClickListener(this);
        btnEnergy.setOnClickListener(this);
        btnInternal_energy.setOnClickListener(this);
        btnPhi.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnElectric_capacity.setOnClickListener(this);
        btnEpsilon.setOnClickListener(this);
        btnArea.setOnClickListener(this);
        btnEnergyOfCapacitor.setOnClickListener(this);
        btnWork.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnDelta.setOnClickListener(this);

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
            case R.id.btnElectrostaticsDelete:
                fActivity.delCh();
                break;
            case R.id.btnElectrostaticsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
