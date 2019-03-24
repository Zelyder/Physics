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
public class KeyboardElectromagneticOscillations extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardElectromagneticOscillations() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_electromagnetic_oscillations, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnElectromagneticOscillationsDelete);
        Button btnPlus =  view.findViewById(R.id.btnElectromagneticOscillationsPlus);
        Button btnMinus =  view.findViewById(R.id.btnElectromagneticOscillationsMinus);
        ImageButton btnOk =  view.findViewById(R.id.btnElectromagneticOscillationsOk);
        Button btnPeriod =  view.findViewById(R.id.btnElectromagneticOscillationsPeriod);
        Button btnL =  view.findViewById(R.id.btnElectromagneticOscillationsL);
        Button btnElectric_capacity =  view.findViewById(R.id.btnElectromagneticOscillationsElectric_capacity);
        Button btnDelta =  view.findViewById(R.id.btnElectromagneticOscillationsDelta);
        Button btnEnergyOfCapacitor =  view.findViewById(R.id.btnElectromagneticOscillationsEnergyOfCapacitor);
        Button btnAmperage =  view.findViewById(R.id.btnElectromagneticOscillationsAmperage);
        Button btnCharge =  view.findViewById(R.id.btnElectromagneticOscillationsCharge);
        Button btnPi =  view.findViewById(R.id.btnElectromagneticOscillationsPi);
        Button btnX =  view.findViewById(R.id.btnElectromagneticOscillationsX);
        Button btnOmega =  view.findViewById(R.id.btnElectromagneticOscillationsOmega);
        Button btnLambda =  view.findViewById(R.id.btnElectromagneticOscillationsLambda);
        Button btnSquare =  view.findViewById(R.id.btnElectromagneticOscillationsSquare);
        Button btnSpeed =  view.findViewById(R.id.btnElectromagneticOscillationsSpeed);
        Button btnArea =  view.findViewById(R.id.btnElectromagneticOscillationsArea);
        Button btnTime =  view.findViewById(R.id.btnElectromagneticOscillationsTime);
        Button btnOne =  view.findViewById(R.id.btnElectromagneticOscillationsOne);
        Button btnZ =  view.findViewById(R.id.btnElectromagneticOscillationsZ);
        Button btnR =  view.findViewById(R.id.btnElectromagneticOscillationsR);
        Button btnFour =  view.findViewById(R.id.btnElectromagneticOscillationsFour);
        Button btnTwo =  view.findViewById(R.id.btnElectromagneticOscillationsTwo);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnPeriod.setOnClickListener(this);
        btnL.setOnClickListener(this);
        btnElectric_capacity.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnEnergyOfCapacitor.setOnClickListener(this);
        btnAmperage.setOnClickListener(this);
        btnCharge.setOnClickListener(this);
        btnPi.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnOmega.setOnClickListener(this);
        btnLambda.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnArea.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnZ.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnTwo.setOnClickListener(this);

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
            case R.id.btnElectromagneticOscillationsDelete:
                fActivity.delCh();
                break;
            case R.id.btnElectromagneticOscillationsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
