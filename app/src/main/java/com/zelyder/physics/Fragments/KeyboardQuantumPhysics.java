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
public class KeyboardQuantumPhysics extends Fragment implements View.OnClickListener{
    FActivity fActivity;

    public KeyboardQuantumPhysics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fActivity = (FActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_keyboard_quantum_physics, container, false);

        ImageButton btnDelete =  view.findViewById(R.id.btnQuantumPhysicsDelete);
        Button btnPlus =  view.findViewById(R.id.btnQuantumPhysicsPlus);
        Button btnMinus =  view.findViewById(R.id.btnQuantumPhysicsMinus);
        ImageButton btnOk =  view.findViewById(R.id.btnQuantumPhysicsOk);
        Button btnEnergy =  view.findViewById(R.id.btnQuantumPhysicsEnergy);
        Button btnPlanckConstant =  view.findViewById(R.id.btnQuantumPhysicsPlanckConstant);
        Button btnFrequency =  view.findViewById(R.id.btnQuantumPhysicsFrequency);
        Button btnSquare =  view.findViewById(R.id.btnQuantumPhysicsSquare);
        Button btnMomentum =  view.findViewById(R.id.btnQuantumPhysicsMomentum);
        Button btnMass =  view.findViewById(R.id.btnQuantumPhysicsMass);
        Button btnSpeedOfLight =  view.findViewById(R.id.btnQuantumPhysicsSpeedOfLight);
        Button btnTwo =  view.findViewById(R.id.btnQuantumPhysicsTwo);
        Button btnSpeed =  view.findViewById(R.id.btnQuantumPhysicsSpeed);
        Button btnWork =  view.findViewById(R.id.btnQuantumPhysicsWork);
        Button btnLambda =  view.findViewById(R.id.btnQuantumPhysicsLambda);
        Button btnRetardingStress =  view.findViewById(R.id.btnQuantumPhysicsRetardingStress);
        Button btnDelta =  view.findViewById(R.id.btnQuantumPhysicsDelta);
        Button btnElectConst =  view.findViewById(R.id.btnQuantumPhysicsElectConst);
        Button btnRadiationEnergy =  view.findViewById(R.id.btnQuantumPhysicsRadiationEnergy);
        Button btnAmount =  view.findViewById(R.id.btnQuantumPhysicsAmount);
        Button btnN =  view.findViewById(R.id.btnQuantumPhysicsN);
        Button btnTime =  view.findViewById(R.id.btnQuantumPhysicsTime);
        Button btnIntensity =  view.findViewById(R.id.btnQuantumPhysicsIntensity);
        Button btnArea =  view.findViewById(R.id.btnQuantumPhysicsArea);


        btnDelete.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnEnergy.setOnClickListener(this);
        btnPlanckConstant.setOnClickListener(this);
        btnFrequency.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnMomentum.setOnClickListener(this);
        btnMass.setOnClickListener(this);
        btnSpeedOfLight.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnSpeed.setOnClickListener(this);
        btnWork.setOnClickListener(this);
        btnLambda.setOnClickListener(this);
        btnRetardingStress.setOnClickListener(this);
        btnDelta.setOnClickListener(this);
        btnElectConst.setOnClickListener(this);
        btnRadiationEnergy.setOnClickListener(this);
        btnAmount.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnIntensity.setOnClickListener(this);
        btnArea.setOnClickListener(this);

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
            case R.id.btnQuantumPhysicsDelete:
                fActivity.delCh();
                break;
            case R.id.btnQuantumPhysicsOk:
                fActivity.clickBtnOk();
                break;
            default:
                fActivity.insertChar(((Button) v).getText().toString());
                break;
        }
    }
}
