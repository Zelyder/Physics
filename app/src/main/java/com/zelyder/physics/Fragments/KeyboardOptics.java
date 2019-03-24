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
public class KeyboardOptics extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardOptics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_optics, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnOpticsDelete);
        Button btnPlus =  view.findViewById(R.id.btnOpticsPlus);
        Button btnDelta =  view.findViewById(R.id.btnOpticsDelta);
        ImageButton btnOk =  view.findViewById(R.id.btnOpticsOk);
        Button btnRefIndex =  view.findViewById(R.id.btnOpticsRefIndex);
        Button btnSpeedOfLight =  view.findViewById(R.id.btnOpticsSpeedOfLight);
        Button btnSpeed =  view.findViewById(R.id.btnOpticsSpeed);
        Button btnOne =  view.findViewById(R.id.btnOpticsOne);
        Button btnD =  view.findViewById(R.id.btnOpticsD);
        Button btnFocalLength =  view.findViewById(R.id.btnOpticsFocalLength);
        Button btnDisI =  view.findViewById(R.id.btnOpticsDisI);
        Button btnTwo =  view.findViewById(R.id.btnOpticsTwo);
        Button btnDisS =  view.findViewById(R.id.btnOpticsDisS);
        Button btnGamma =  view.findViewById(R.id.btnOpticsGamma);
        Button btnSizeI =  view.findViewById(R.id.btnOpticsSizeI);
        Button btnSizeS =  view.findViewById(R.id.btnOpticsSizeS);
        Button btnK =  view.findViewById(R.id.btnOpticsK);
        Button btnLambda =  view.findViewById(R.id.btnOpticsLambda);
        Button btnSin_phi =  view.findViewById(R.id.btnOpticsSin_phi);
        Button btnBigOmega =  view.findViewById(R.id.btnOpticsBigOmega);
        Button btnL =  view.findViewById(R.id.btnOpticsL);

        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnRefIndex.setOnClickListener(this);
        btnSpeedOfLight.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnFocalLength.setOnClickListener(this);
        btnDisI.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnDisS.setOnClickListener(this);
        btnGamma.setOnClickListener(this);
        btnSizeI.setOnClickListener(this);
        btnSizeS.setOnClickListener(this);
        btnK.setOnClickListener(this);
        btnLambda.setOnClickListener(this);
        btnSin_phi.setOnClickListener(this);
        btnBigOmega.setOnClickListener(this);
        btnL.setOnClickListener(this);

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
            case R.id.btnOpticsDelete:
                fActivity.delCh();
                break;
            case R.id.btnOpticsOk:
                fActivity.clickBtnOk();
                break;
            case R.id.btnOpticsSin_phi:
                fActivity.insertChar("#sinφ#");
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
