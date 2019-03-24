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
public class KeyboardKinematicsFragment extends Fragment implements View.OnClickListener{


    FActivity fActivity;

    public KeyboardKinematicsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_kinematics, container, false);

        ImageButton btnDelete = view.findViewById(R.id.btnKinematicsDelete);
        Button btnPlus =  view.findViewById(R.id.btnKinematicsPlus);
        Button btnMinus =  view.findViewById(R.id.btnKinematicsMinus);
        ImageButton btnOk =  view.findViewById(R.id.btnKinematicsOk);
        Button btnSpeed = view.findViewById(R.id.btnKinematicsSpeed);
        Button btnTime =  view.findViewById(R.id.btnKinematicsTime);
        Button btnDistance =  view.findViewById(R.id.btnKinematicsDistance);
        Button btnAcceleration =  view.findViewById(R.id.btnKinematicsAcceleration);
        Button btnSquare = view.findViewById(R.id.btnKinematicsSquare);
        Button btnTwo = view.findViewById(R.id.btnKinematicsTwo);
        Button btnFall = view.findViewById(R.id.btnKinematicsFall);
        Button btnHeight = view.findViewById(R.id.btnKinematicsHeight);
        Button btnX = view.findViewById(R.id.btnKinematicsX);
        Button btnN = view.findViewById(R.id.btnKinematicsN);
        Button btnRadius = view.findViewById(R.id.btnKinematicsRadius);
        Button btnPi = view.findViewById(R.id.btnKinematicsPi);
        Button btnFrequency = view.findViewById(R.id.btnKinematicsFrequency);
        Button btnPeriod = view.findViewById(R.id.btnKinematicsPeriod);
        Button btnOmega = view.findViewById(R.id.btnKinematicsOmega);
        Button btnPhi = view.findViewById(R.id.btnKinematicsPhi);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnDistance.setOnClickListener(this);
        btnAcceleration.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnFall.setOnClickListener(this);
        btnHeight.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnRadius.setOnClickListener(this);
        btnPi.setOnClickListener(this);
        btnFrequency.setOnClickListener(this);
        btnPeriod.setOnClickListener(this);
        btnOmega.setOnClickListener(this);
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
            case R.id.btnKinematicsDelete:
                fActivity.delCh();
                break;
            case R.id.btnKinematicsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
