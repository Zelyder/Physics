package com.zelyder.physics.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zelyder.user.physics.R;


public class DoubleFractionView extends LinearLayout {
    String fraction;

    public DoubleFractionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.double_fraction_view, this);

    }

    public void setDoubleFraction(@NonNull String fraction) {
        this.fraction = fraction;

        final String[] arr1 = fraction.split("[=]");

        if (arr1[0].contains("#")) {
            arr1[0] = arr1[0].replace("#", "");
        }

        if (arr1[1].contains("#")) {
            arr1[1] = arr1[1].replace("#", "");
        }

        TextView side1NominatorTv = findViewById(R.id.doubleF_side1NominatorTv);
        TextView side1DenominatorTv = findViewById(R.id.doubleF_side1DenominatorTv);
        TextView equally = findViewById(R.id.doubleF_equally);
        TextView side2NominatorTv = findViewById(R.id.doubleF_side2NominatorTv);
        TextView side2DenominatorTv = findViewById(R.id.doubleF_side2DenominatorTv);
        TextView side2postTv = findViewById(R.id.doubleF_side2postTv);
        TextView side3NominatorTv = findViewById(R.id.doubleF_side3NominatorTv);
        TextView side3DenominatorTv = findViewById(R.id.doubleF_side3DenominatorTv);

        side1NominatorTv.setText("");
        side1DenominatorTv.setText("");
        equally.setText("");
        side2NominatorTv.setText("");
        side2DenominatorTv.setText("");
        side2postTv.setText("");
        side3NominatorTv.setText("");
        side3DenominatorTv.setText("");

        if (arr1[0].contains("/")) {
            final String[] arr = arr1[0].split("[/]");
            side1NominatorTv.setText(arr[0]);
            side1DenominatorTv.setText(arr[1]);
            equally.setText(" = ");
        }else {
            equally.setText(arr1[0].concat(" = "));
        }

        if (arr1[1].contains("~")) {
            final String[] arr = arr1[1].split("[~]");
            side2postTv.setText(arr[1]);
        }

        if (arr1[1].contains("/")) {
            if(arr1[1].contains("~")){
                final String[] arr2 = arr1[1].split("[/,~]");
                side2NominatorTv.setText(arr2[0]);
                side2DenominatorTv.setText(arr2[1]);
                side3NominatorTv.setText(arr2[3]);
                side3DenominatorTv.setText(arr2[4]);
            }else {
                final String[] arr2 = arr1[1].split("[/]");
                side2NominatorTv.setText(arr2[0]);
                side2DenominatorTv.setText(arr2[1]);
            }
        }

    }

    public String getDoubleFraction() {
        return fraction;
    }
}
