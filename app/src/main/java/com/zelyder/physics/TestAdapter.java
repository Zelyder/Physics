package com.zelyder.physics;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zelyder.physics.Fragments.FractionView;
import com.zelyder.physics.model.Formula;
import com.zelyder.user.physics.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.PersonViewHolder> {
    ArrayList<Formula> list;

    public TestAdapter(ArrayList<Formula> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.correct_list_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        personViewHolder.title.setText(list.get(i).getTitle());
        personViewHolder.fractionView.setFraction(list.get(i).getFormula());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private FractionView fractionView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.correctItem_title);
            fractionView = itemView.findViewById(R.id.correctItem_formula);
        }
    }
}
