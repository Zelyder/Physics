package com.zelyder.physics.help;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.tooltip.Tooltip;
import com.zelyder.physics.activity.CorrectListActivity;
import com.zelyder.physics.activity.FActivity;
import com.zelyder.user.physics.R;


public class TabCorrectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView mTitleTask;
    private String tipText = "Количество формул: ";
    private static final String Tag = "TabCorrectViewHolder";
    private Context context;

    public TabCorrectViewHolder(View itemView) {
        super(itemView);
        mTitleTask = itemView.findViewById(R.id.item_tvTitle);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), CorrectListActivity.class);
        intent.putExtra(FActivity.TITLE, mTitleTask.getText().toString());
        v.getContext().startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {

        new Tooltip.Builder(mTitleTask)
                .setText(tipText)
                .setBackgroundColor(Color.rgb(68, 138, 255))
                .setTextColor(Color.WHITE)
                .setGravity(Gravity.TOP)
                .setCornerRadius(15f)
                .setCancelable(true)
                .show();
        return true;
    }

    public void setTipText(String text) {
        tipText = "Количество формул: " + text;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
