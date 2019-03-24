package com.zelyder.physics.Fragments;


import android.app.Fragment;
import android.os.Bundle;
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
public class KeyboardEnergy extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardEnergy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_energy, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnEnergyDelete);
        Button btnPlus =  view.findViewById(R.id.btnEnergyPlus);
        Button btnCos_a =  view.findViewById(R.id.btnEnergyCos_a);
        ImageButton btnOk =  view.findViewById(R.id.btnEnergyOk);
        Button btnWork =  view.findViewById(R.id.btnEnergyWork);
        Button btnForce =  view.findViewById(R.id.btnEnergyForce);
        Button btnPower =  view.findViewById(R.id.btnEnergyPower);
        Button btnSquare =  view.findViewById(R.id.btnEnergySquare);
        Button btnECE =  view.findViewById(R.id.btnEnergyECE);
        Button btnSpeed =  view.findViewById(R.id.btnEnergySpeed);
        Button btnTime =  view.findViewById(R.id.btnEnergyTime);
        Button btnTwo =  view.findViewById(R.id.btnEnergyTwo);
        Button btnEnergy =  view.findViewById(R.id.btnEnergyEnergy);
        Button btnMass =  view.findViewById(R.id.btnEnergyMass);
        Button btnFall =  view.findViewById(R.id.btnEnergyFall);
        Button btnHeight =  view.findViewById(R.id.btnEnergyHeight);
        Button btnK =  view.findViewById(R.id.btnEnergyK);
        Button btnX =  view.findViewById(R.id.btnEnergyX);
        Button btnDistance =  view.findViewById(R.id.btnEnergyDistance);
        Button btnMinus =  view.findViewById(R.id.btnEnergyMinus);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnCos_a.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnWork.setOnClickListener(this);
        btnForce.setOnClickListener(this);
        btnPower.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnECE.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnEnergy.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnFall.setOnClickListener(this);
        btnHeight.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnDistance.setOnClickListener(this);
        btnMinus.setOnClickListener(this);

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
            case R.id.btnEnergyDelete:
                fActivity.delCh();
                break;
            case R.id.btnEnergyOk:
                fActivity.clickBtnOk();
                break;
            case R.id.btnEnergyCos_a:
                fActivity.insertChar("#cosα#");
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
