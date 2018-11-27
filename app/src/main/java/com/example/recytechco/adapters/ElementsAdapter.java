package com.example.recytechco.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recytechco.R;
import com.example.recytechco.models.History;

import java.util.ArrayList;

public class ElementsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<History> mHistoryList;

    public ElementsAdapter(Context context, ArrayList<History> historyList) {
        mContext = context;
        mHistoryList = historyList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new ElementViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ElementViewHolder) holder).mElementNameTextView
                .setText(mHistoryList.get(position).getElementName());
        ((ElementViewHolder) holder).mElementsNumberTextView
                .setText(mHistoryList.get(position).getAmount() + "");
        ((ElementViewHolder) holder).mDateTextView
                .setText(mHistoryList.get(position).getDateInString());
    }

    @Override
    public int getItemCount() {
        return mHistoryList.size();
    }

    private class ElementViewHolder extends RecyclerView.ViewHolder {

        private final TextView mElementNameTextView, mElementsNumberTextView, mPlaceTextView,
                mDateTextView;

        public ElementViewHolder(View view) {
            super(view);
            mElementNameTextView = view.findViewById(R.id.elementNameTextView);
            mElementsNumberTextView = view.findViewById(R.id.elementsNumberTextView);
            mPlaceTextView = view.findViewById(R.id.placeTextView);
            mDateTextView = view.findViewById(R.id.dateTextView);
        }
    }
}
