package com.example.bicycles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private final String[] localDataSet;
    private final RecycleViewClickListener clickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // The ViewHolder class holds the view that represents each bicycle in the recycler view
        // It also handles the onClick event

        private final TextView textView;
        private final RecycleViewClickListener clickListener;

        public ViewHolder(View view, RecycleViewClickListener clickListener) {
            super(view);

            // Initialize the onClick event listener which is sourced from the RecycleViewFragment
            this.clickListener = clickListener;
            view.setOnClickListener(this);

            // Initialize the view that represents each item in the list
            this.textView = (TextView)view.findViewById(R.id.textView);

        }

        @Override
        public void onClick(View view) {
            // Calls the onClick handler from the RecycleViewFragment
            this.clickListener.recycleViewOnClick(view, this.getLayoutPosition());
        }

        // Return the TextView representing each item in the list
        public TextView getTextView() {
            return this.textView;
        }
    }

    public RecycleViewAdapter(String[] dataSet, RecycleViewClickListener clickListener) {

        // Initialize the data set which is a string array
        this.localDataSet = dataSet;

        // Initialize the click listener
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleview_item_layout, viewGroup, false);

        return new ViewHolder(view, this.clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Set the text of each item in the list to the bicycle title corresponding to its position
        viewHolder.getTextView().setText(localDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }



}
