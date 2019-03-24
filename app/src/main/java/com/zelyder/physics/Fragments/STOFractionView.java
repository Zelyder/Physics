package com.zelyder.physics.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zelyder.user.physics.R;

public class STOFractionView extends LinearLayout {
    String fraction;

    public STOFractionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.sto_fraction_view, this);
    }

    public void setSTOFraction(@NonNull String fraction) {
        this.fraction = fraction;

        final String[] arr1 = fraction.split("[=]");

        if (arr1[0].contains("#")) {
            arr1[0] = arr1[0].replace("#", "");
        }

        if (arr1[1].contains("#")) {
            arr1[1] = arr1[1].replace("#", "");
        }

        TextView tvEqually = findViewById(R.id.sto_equally);
        TextView tvBefore = findViewById(R.id.sto_beforeTv);
        TextView tvBeforeInRoot = findViewById(R.id.sto_beforeInRootTv);
        TextView tvNominator = findViewById(R.id.sto_nominatorTv);
        TextView tvBeforeDenominator = findViewById(R.id.sto_beforeDenominatorTv);
        TextView tvD_nominator = findViewById(R.id.sto_D_nominatorTv);
        TextView tvD_denominatorTv = findViewById(R.id.sto_D_denominatorTv);
        View rootLine = findViewById(R.id.sto_rootLine);
        View rootDenominatorLine = findViewById(R.id.sto_rootDenominatorLine);
        ImageView root = findViewById(R.id.sto_root);
        ImageView rootDenominator = findViewById(R.id.sto_rootDenominator);

        tvEqually.setText("");
        tvBefore.setText("");
        tvBeforeInRoot.setText("");
        tvNominator.setText("");
        tvBeforeDenominator.setText("");
        tvD_nominator.setText("");
        tvD_denominatorTv.setText("");

        rootLine.setVisibility(GONE);
        rootDenominatorLine.setVisibility(GONE);
        root.setVisibility(GONE);
        rootDenominator.setVisibility(GONE);

        tvEqually.setText(arr1[0].concat(" = "));

        if(arr1[1].contains("◤")){
            arr1[1] = arr1[1].replace("◤","");
            root.setVisibility(VISIBLE);
            rootLine.setVisibility(VISIBLE);
        }
        if(arr1[1].contains("◢")){
            arr1[1] = arr1[1].replace("◢", "");
            rootDenominator.setVisibility(VISIBLE);
            rootDenominatorLine.setVisibility(VISIBLE);
        }
        if(arr1[1].contains("◇")){
            arr1[1] = arr1[1].replace("◇", "");
        }

        if (arr1[1].contains("~")) {
            final String[] arr = arr1[1].split("[~]");
            tvBefore.setText(arr[1]);
            tvBeforeInRoot.setText(arr[2]);
        }

        if (arr1[1].contains("/")) {
            if (arr1[1].contains("~")) {
                final String[] arr2 = arr1[1].split("[/,~]");
                tvNominator.setText(arr2[3]);
                tvBeforeDenominator.setText(arr2[4]);
            } else if (arr1[1].contains("_")){
                final String[] arr2 = arr1[1].split("[/,_]");
                tvNominator.setText(arr2[0]);
                tvBeforeDenominator.setText(arr2[2]);
                tvD_nominator.setText(arr2[3]);
                tvD_denominatorTv.setText(arr2[4]);
            }
        }
    }
    public String getSTOFraction() {
        return fraction;
    }
}
