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
public class KeyboardSTO extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardSTO() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_keyboard_sto, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnSTODelete);
        Button btnPlus =  view.findViewById(R.id.btnSTOPlus);
        Button btnMinus =  view.findViewById(R.id.btnSTOMinus);
        ImageButton btnOk =  view.findViewById(R.id.btnSTOOk);
        Button btnEnergy =  view.findViewById(R.id.btnSTOEnergy);
        Button btnMass =  view.findViewById(R.id.btnSTOMass);
        Button btnSpeedOfLight =  view.findViewById(R.id.btnSTOSpeedOfLight);
        Button btnSquare =  view.findViewById(R.id.btnSTOSquare);
        Button btnTau =  view.findViewById(R.id.btnSTOTau);
        Button btnSpeed =  view.findViewById(R.id.btnSTOSpeed);
        Button btnLength =  view.findViewById(R.id.btnSTOLength);
        Button btnOne =  view.findViewById(R.id.btnSTOOne);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnEnergy.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnSpeedOfLight.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnTau.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnLength.setOnClickListener(this);
        btnOne.setOnClickListener(this);

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
            case R.id.btnSTODelete:
                fActivity.delCh();
                break;
            case R.id.btnSTOOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
