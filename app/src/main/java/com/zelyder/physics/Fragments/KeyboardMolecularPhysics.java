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
public class KeyboardMolecularPhysics extends Fragment implements View.OnClickListener{

    FActivity fActivity;

    public KeyboardMolecularPhysics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_molecular_physics, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnMolecularDelete);
        Button btnConst =  view.findViewById(R.id.btnMolecularConst);
        Button btnSquare =  view.findViewById(R.id.btnMolecularSquare);
        ImageButton btnOk =  view.findViewById(R.id.btnMolecularOk);
        Button btnFrequency =  view.findViewById(R.id.btnMolecularFrequency);
        Button btnMass =  view.findViewById(R.id.btnMolecularMass);
        Button btnM =  view.findViewById(R.id.btnMolecularM);
        Button btnOne =  view.findViewById(R.id.btnMolecularOne);
        Button btnConcentration =  view.findViewById(R.id.btnMolecularConcentration);
        Button btnN =  view.findViewById(R.id.btnMolecularN);
        Button btnDensity =  view.findViewById(R.id.btnMolecularDensity);
        Button btnTwo =  view.findViewById(R.id.btnMolecularTwo);
        Button btnVolume =  view.findViewById(R.id.btnMolecularVolume);
        Button btnSpeed =  view.findViewById(R.id.btnMolecularSpeed);
        Button btnPressure =  view.findViewById(R.id.btnMolecularPressure);
        Button btnThree =  view.findViewById(R.id.btnMolecularThree);
        Button btnK =  view.findViewById(R.id.btnMolecularK);
        Button btnR =  view.findViewById(R.id.btnMolecularR);
        Button btnTemp =  view.findViewById(R.id.btnMolecularTemp);
        Button btnEnergy =  view.findViewById(R.id.btnMolecularEnergy);

        btnDelete.setOnClickListener(this);
        btnConst.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnFrequency.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnConcentration.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnDensity.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnVolume.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnPressure.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnTemp.setOnClickListener(this);
        btnEnergy.setOnClickListener(this);

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
            case R.id.btnMolecularDelete:
                fActivity.delCh();
                break;
            case R.id.btnMolecularOk:
                fActivity.clickBtnOk();
                break;
            case R.id.btnMolecularConst:
                fActivity.insertChar("#const#");
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
