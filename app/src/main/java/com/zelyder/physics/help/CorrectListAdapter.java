package com.zelyder.physics.help;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.zelyder.physics.Fragments.DoubleFractionView;
import com.zelyder.physics.Fragments.FractionView;
import com.zelyder.physics.Fragments.STOFractionView;
import com.zelyder.physics.model.Formula;
import com.zelyder.user.physics.R;

import java.util.ArrayList;

import cdflynn.android.library.crossview.CrossView;


public class CorrectListAdapter extends RecyclerView.Adapter<CorrectListAdapter.Holder> {

    private ArrayList<Formula> listData;
    private LayoutInflater inflater;
    private CorrectListCallback callback;
    private boolean isDelete;


    public CorrectListAdapter(ArrayList<Formula> listData, Context context) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
    }

    public void setCorrectListCallback(CorrectListCallback callback ){
        this.callback = callback;
    }

    public boolean getLikedState(int position){
        return callback.getLikedState(position);
    }
    public boolean getCrossState(int position){
        return callback.getCrossState(position);
    }

    private void checkState(CrossView crossView, int position, Context context){
        if(!getCrossState(position)){
            crossView.cross(0);
            isDelete = false;
        }else {
            crossView.plus(0);
            crossView.setColor(ContextCompat.getColor(context, R.color.colorCorrect));
            isDelete = true;
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.correct_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Formula item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.starButton.setLiked(getLikedState(holder.getAdapterPosition()));
        holder.starButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                callback.liked(holder.getAdapterPosition());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                callback.unLiked(holder.getAdapterPosition());
            }
        });

        checkState(holder.crossButton, position, holder.context);
        holder.crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkState(holder.crossButton, position, holder.context);
                holder.crossButton.toggle();
                isDelete = !isDelete;
                if (isDelete){
                    holder.crossButton.setColor(ContextCompat.getColor(holder.context, R.color.colorCorrect));
                }else {
                    holder.crossButton.setColor(ContextCompat.getColor(holder.context, R.color.colorDelCross));
                }
                callback.crossToggle(holder.getAdapterPosition(), isDelete);
            }
        });

        if (item.getFormula().split("[=]")[0].contains("/")) {
            holder.doubleFractionView.setVisibility(View.VISIBLE);
            holder.fractionView.setVisibility(View.GONE);
            holder.stoFractionView.setVisibility(View.GONE);
            holder.doubleFractionView.setDoubleFraction(item.getFormula());
        } else if (item.getFormula().split("[=]")[1].contains("◤")
                || item.getFormula().split("[=]")[1].contains("◢")
                || item.getFormula().split("[=]")[1].contains("◇")) {
            holder.stoFractionView.setVisibility(View.VISIBLE);
            holder.doubleFractionView.setVisibility(View.GONE);
            holder.fractionView.setVisibility(View.GONE);
            holder.stoFractionView.setSTOFraction(item.getFormula());
        } else {
            holder.fractionView.setVisibility(View.VISIBLE);
            holder.doubleFractionView.setVisibility(View.GONE);
            holder.stoFractionView.setVisibility(View.GONE);
            holder.fractionView.setFraction(item.getFormula());
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView title;
        private FractionView fractionView;
        private DoubleFractionView doubleFractionView;
        private STOFractionView stoFractionView;
        private LikeButton starButton;
        private CrossView crossButton;
        private Context context;

        Holder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.correctItem_title);
            fractionView = itemView.findViewById(R.id.correctItem_formula);
            doubleFractionView = itemView.findViewById(R.id.correctItem_doubleFormula);
            stoFractionView = itemView.findViewById(R.id.correctItem_STOFormula);
            starButton = itemView.findViewById(R.id.correctItem_star_button);
            crossButton = itemView.findViewById(R.id.sample_cross_view);
            context = itemView.getContext();

        }
    }

}
