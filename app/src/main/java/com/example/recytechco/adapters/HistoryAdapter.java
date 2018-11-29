package com.example.recytechco.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recytechco.R;
import com.example.recytechco.models.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter {

    private ArrayList<History> mHistories;

    public HistoryAdapter(ArrayList<History> histories) {
        mHistories = histories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new HistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HistoryViewHolder) holder).mElementNameTextView.setText(mHistories.get(position)
                .getElementName());
        ((HistoryViewHolder) holder).mElementsNumberTextView.setText("" + mHistories.get(position)
                .getAmount());
        ((HistoryViewHolder) holder).mDateTextView.setText(mHistories.get(position).getDateInString());
    }

    @Override
    public int getItemCount() {
        return mHistories.size();
    }

    private class HistoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView mElementNameTextView, mElementsNumberTextView, mDateTextView;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            mElementNameTextView = itemView.findViewById(R.id.elementNameTextView);
            mElementsNumberTextView = itemView.findViewById(R.id.elementsNumberTextView);
            mDateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
