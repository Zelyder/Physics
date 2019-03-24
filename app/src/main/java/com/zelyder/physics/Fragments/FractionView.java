package com.zelyder.physics.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zelyder.user.physics.R;

public class FractionView extends LinearLayout {
    String fraction;

    public FractionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.fraction_view, this);
    }

    public void setFraction(@NonNull String fraction) {
        this.fraction = fraction;

        final String[] arr1 = fraction.split("[=]");

        if (arr1[0].contains("#")) {
            arr1[0] = arr1[0].replace("#", "");
        }

        if (arr1[1].contains("#")) {
            arr1[1] = arr1[1].replace("#", "");
        }

        ((TextView) findViewById(R.id.equally)).setText(arr1[0].concat(" = "));

        TextView nominatorTv = findViewById(R.id.nominatorTv);
        TextView denominatorTv = findViewById(R.id.denominatorTv);
        TextView postTv = findViewById(R.id.postTv);
        TextView beforeTv = findViewById(R.id.beforeTv);
        View rootLineSmall = findViewById(R.id.rootLineSmall);
        View rootLineBig = findViewById(R.id.rootLineBig);
        ImageView rootSmall = findViewById(R.id.rootSmall);
        ImageView rootBig = findViewById(R.id.rootBig);

        nominatorTv.setText("");
        denominatorTv.setText("");
        postTv.setText("");
        beforeTv.setText("");
        rootLineSmall.setVisibility(GONE);
        rootSmall.setVisibility(GONE);
        rootLineBig.setVisibility(GONE);
        rootBig.setVisibility(GONE);

        if(arr1[1].contains("Ѩ")){
            arr1[1] = arr1[1].replace("Ѩ","");
            rootSmall.setVisibility(VISIBLE);
            rootLineSmall.setVisibility(VISIBLE);
        }
        if(arr1[1].contains("Ѫ")){
            arr1[1] = arr1[1].replace("Ѫ", "");
            rootBig.setVisibility(VISIBLE);
            rootLineBig.setVisibility(VISIBLE);
        }

        if (arr1[1].contains("~")) {
            final String[] arr4 = arr1[1].split("[~]");
            beforeTv.setText(arr4[1]);
        }

        if (arr1[1].contains("/")) {
            if (arr1[1].contains("~")) {
                final String[] arr2 = arr1[1].split("[/,~]");
                nominatorTv.setText(arr2[2]);
                denominatorTv.setText(arr2[3]);
            } else {
                final String[] arr2 = arr1[1].split("[/,_]");
                nominatorTv.setText(arr2[0]);
                denominatorTv.setText(arr2[1]);
            }
        }
        if (arr1[1].contains("_")) {
            final String[] arr3 = arr1[1].split("[_]");
            postTv.setText(arr3[1]);
        }
    }

    public String getFraction() {
        return fraction;
    }
}