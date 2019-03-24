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
public class KeyboardDynamics extends Fragment implements View.OnClickListener{

    FActivity fActivity;


    public KeyboardDynamics() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_dynamics, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnDynamicsDelete);
        Button btnPlus =  view.findViewById(R.id.btnDynamicsPlus);
        Button btnDelta =  view.findViewById(R.id.btnDynamicsDelta);
        ImageButton btnOk =  view.findViewById(R.id.btnDynamicsOk);
        Button btnForce =  view.findViewById(R.id.btnDynamicsForce);
        Button btnMass =  view.findViewById(R.id.btnDynamicsMass);
        Button btnAcceleration =  view.findViewById(R.id.btnDynamicsAcceleration);
        Button btnSquare =  view.findViewById(R.id.btnDynamicsSquare);
        Button btnK =  view.findViewById(R.id.btnDynamicsK);
        Button btnX =  view.findViewById(R.id.btnDynamicsX);
        Button btnMu =  view.findViewById(R.id.btnDynamicsMu);
        Button btnN =  view.findViewById(R.id.btnDynamicsN);
        Button btnG =  view.findViewById(R.id.btnDynamicsG);
        Button btnM =  view.findViewById(R.id.btnDynamicsM);
        Button btnTime =  view.findViewById(R.id.btnDynamicsTime);
        Button btnFall =  view.findViewById(R.id.btnDynamicsFall);
        Button btnR =  view.findViewById(R.id.btnDynamicsR);
        Button btnHeight =  view.findViewById(R.id.btnDynamicsHeight);
        Button btnSpeed =  view.findViewById(R.id.btnDynamicsSpeed);
        Button btnMomentum =  view.findViewById(R.id.btnDynamicsMomentum);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnForce.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnAcceleration.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnMu.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnG.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnFall.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnHeight.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnMomentum.setOnClickListener(this);

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
            case R.id.btnDynamicsDelete:
                fActivity.delCh();
                break;
            case R.id.btnDynamicsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
