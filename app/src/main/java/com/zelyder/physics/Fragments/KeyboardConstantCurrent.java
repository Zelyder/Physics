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
public class KeyboardConstantCurrent extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardConstantCurrent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_constant_current, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnConstantCurrentDelete);
        Button btnPlus =  view.findViewById(R.id.btnConstantCurrentPlus);
        Button btnDelta =  view.findViewById(R.id.btnConstantCurrentDelta);
        ImageButton btnOk =  view.findViewById(R.id.btnConstantCurrentOk);
        Button btnAmperage =  view.findViewById(R.id.btnConstantCurrentAmperage);
        Button btnVoltage =  view.findViewById(R.id.btnConstantCurrentVoltage);
        Button btnResistance =  view.findViewById(R.id.btnConstantCurrentResistance);
        Button btnSquare =  view.findViewById(R.id.btnConstantCurrentSquare);
        Button btnCharge =  view.findViewById(R.id.btnConstantCurrentCharge);
        Button btnTime =  view.findViewById(R.id.btnConstantCurrentTime);
        Button btnDensity =  view.findViewById(R.id.btnConstantCurrentDensity);
        Button btnLength =  view.findViewById(R.id.btnConstantCurrentLength);
        Button btnArea =  view.findViewById(R.id.btnConstantCurrentArea);
        Button btnPower =  view.findViewById(R.id.btnConstantCurrentPower);
        Button btnWork =  view.findViewById(R.id.btnConstantCurrentWork);
        Button btnEpsilon =  view.findViewById(R.id.btnConstantCurrentEpsilon);
        Button btnSourceResistance =  view.findViewById(R.id.btnConstantCurrentSourceResistance);
        Button btnHeat =  view.findViewById(R.id.btnConstantCurrentHeat);
        Button btnConcentration =  view.findViewById(R.id.btnConstantCurrentConcentration);
        Button btnSpeed =  view.findViewById(R.id.btnConstantCurrentSpeed);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnAmperage.setOnClickListener(this);
        btnVoltage.setOnClickListener(this);
        btnResistance.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnCharge.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnDensity.setOnClickListener(this);
        btnLength.setOnClickListener(this);
        btnArea.setOnClickListener(this);
        btnPower.setOnClickListener(this);
        btnWork.setOnClickListener(this);
        btnEpsilon.setOnClickListener(this);
        btnSourceResistance.setOnClickListener(this);
        btnHeat.setOnClickListener(this);
        btnConcentration.setOnClickListener(this);
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
            case R.id.btnConstantCurrentDelete:
                fActivity.delCh();
                break;
            case R.id.btnConstantCurrentOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
