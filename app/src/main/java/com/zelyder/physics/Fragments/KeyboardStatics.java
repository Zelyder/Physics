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
public class KeyboardStatics extends Fragment implements View.OnClickListener{
    FActivity fActivity;


    public KeyboardStatics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_statics, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnStaticsDelete);
        Button btnAmount =  view.findViewById(R.id.btnStaticsAmount);
        Button btnSquare =  view.findViewById(R.id.btnStaticsSquare);
        ImageButton btnOk =  view.findViewById(R.id.btnStaticsOk);
        Button btnM =  view.findViewById(R.id.btnStaticsM);
        Button btnForce =  view.findViewById(R.id.btnStaticsForce);
        Button btnDiameter =  view.findViewById(R.id.btnStaticsDiameter);
        Button btnZero =  view.findViewById(R.id.btnStaticsZero);
        Button btnMomentOfInertia =  view.findViewById(R.id.btnStaticsMomentOfInertia);
        Button btnMass =  view.findViewById(R.id.btnStaticsMass);
        Button btnRadius =  view.findViewById(R.id.btnStaticsRadius);
        Button btnTwo =  view.findViewById(R.id.btnStaticsTwo);

        btnDelete.setOnClickListener(this);
        btnAmount.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnForce.setOnClickListener(this);
        btnDiameter.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnMomentOfInertia.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnRadius.setOnClickListener(this);
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
            case R.id.btnStaticsDelete:
                fActivity.delCh();
                break;
            case R.id.btnStaticsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
