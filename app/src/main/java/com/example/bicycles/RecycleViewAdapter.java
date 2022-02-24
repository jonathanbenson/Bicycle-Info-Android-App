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

        private final TextView textView;
        private final RecycleViewClickListener clickListener;

        public ViewHolder(View view, RecycleViewClickListener clickListener) {
            super(view);

            this.clickListener = clickListener;

            view.setOnClickListener(this);
            this.textView = (TextView)view.findViewById(R.id.textView);

        }

        @Override
        public void onClick(View view) {
            this.clickListener.recycleViewOnClick(view, this.getLayoutPosition());
        }

        public TextView getTextView() {
            return this.textView;
        }
    }

    public RecycleViewAdapter(String[] dataSet, RecycleViewClickListener clickListener) {

        this.localDataSet = dataSet;

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

        viewHolder.getTextView().setText(localDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }



}
