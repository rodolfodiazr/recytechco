package com.example.recytechco.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recytechco.R;
import com.example.recytechco.models.Element;

import java.util.ArrayList;

public class ElementsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Element> mElements;
    private RecycledElementListener mListener;

    public interface RecycledElementListener {
        void onRecycledElement(int position, Element element);
    }

    public ElementsAdapter(Context context, ArrayList<Element> elements,
                           RecycledElementListener listener) {
        mContext = context;
        mElements = elements;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_list_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new ElementViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ElementViewHolder) holder).mElementNameTextView
                .setText(mElements.get(position).getName());
        ((ElementViewHolder) holder).mPointsTextView
                .setText("" + mElements.get(position).getPoints());
        ((ElementViewHolder) holder).mAmountEditText.setText("");
        ((ElementViewHolder) holder).mRecycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountInString = ((ElementViewHolder) holder).mAmountEditText.getText().toString();
                mElements.get(position).setAmountInString(amountInString);
                mListener.onRecycledElement(position, mElements.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mElements.size();
    }

    private class ElementViewHolder extends RecyclerView.ViewHolder {

        private final TextView mElementNameTextView, mPointsTextView;
        private final EditText mAmountEditText;
        private final Button mRecycleButton;

        public ElementViewHolder(View view) {
            super(view);
            mElementNameTextView = view.findViewById(R.id.elementNameTextView);
            mPointsTextView = view.findViewById(R.id.pointsTextView);
            mAmountEditText = view.findViewById(R.id.amountEditText);
            mRecycleButton = view.findViewById(R.id.recycleButton);
        }
    }
}
