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
public class KeyboardMagneticField extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardMagneticField() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_magnetic_field, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnMagneticFieldDelete);
        Button btnMinus =  view.findViewById(R.id.btnMagneticFieldMinus);
        Button btnDelta =  view.findViewById(R.id.btnMagneticFieldDelta);
        ImageButton btnOk =  view.findViewById(R.id.btnMagneticFieldOk);
        Button btnForce =  view.findViewById(R.id.btnMagneticFieldForce);
        Button btnMagneticInduction =  view.findViewById(R.id.btnMagneticFieldMagneticInduction);
        Button btnAmperage =  view.findViewById(R.id.btnMagneticFieldAmperage);
        Button btnSin_a =  view.findViewById(R.id.btnMagneticFieldSin_a);
        Button btnLength =  view.findViewById(R.id.btnMagneticFieldLength);
        Button btnCharge =  view.findViewById(R.id.btnMagneticFieldCharge);
        Button btnSpeed =  view.findViewById(R.id.btnMagneticFieldSpeed);
        Button btnCos_a =  view.findViewById(R.id.btnMagneticFieldCos_a);
        Button btnMagneticFlow =  view.findViewById(R.id.btnMagneticFieldMagneticFlow);
        Button btnArea =  view.findViewById(R.id.btnMagneticFieldArea);
        Button btnInductance =  view.findViewById(R.id.btnMagneticFieldInductance);
        Button btnSquare =  view.findViewById(R.id.btnMagneticFieldSquare);
        Button btnEnergyOfCapacitor =  view.findViewById(R.id.btnMagneticFieldEnergyOfCapacitor);
        Button btnEpsilon =  view.findViewById(R.id.btnMagneticFieldEpsilon);
        Button btnTime =  view.findViewById(R.id.btnMagneticFieldTime);
        Button btnTwo =  view.findViewById(R.id.btnMagneticFieldTwo);

        btnDelete.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnForce.setOnClickListener(this);
        btnMagneticInduction.setOnClickListener(this);
        btnAmperage.setOnClickListener(this);
        btnSin_a.setOnClickListener(this);
        btnLength.setOnClickListener(this);
        btnCharge.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnCos_a.setOnClickListener(this);
        btnMagneticFlow.setOnClickListener(this);
        btnArea.setOnClickListener(this);
        btnInductance.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnEnergyOfCapacitor.setOnClickListener(this);
        btnEpsilon.setOnClickListener(this);
        btnTime.setOnClickListener(this);
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
            case R.id.btnMagneticFieldDelete:
                fActivity.delCh();
                break;
            case R.id.btnMagneticFieldOk:
                fActivity.clickBtnOk();
                break;
            case R.id.btnMagneticFieldSin_a:
                fActivity.insertChar("#sinα#");
                break;
            case R.id.btnMagneticFieldCos_a:
                fActivity.insertChar("#cosα#");
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
