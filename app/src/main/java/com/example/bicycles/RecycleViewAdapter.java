package com.example.bicycles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private String[] localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textView;

        public ViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);
            this.textView = (TextView)view.findViewById(R.id.textView);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), this.textView.getText(), Toast.LENGTH_SHORT).show();
        }

        public TextView getTextView() {
            return this.textView;
        }
    }

    public RecycleViewAdapter(String[] dataSet) {

        this.localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleview_item_layout, viewGroup, false);

        return new ViewHolder(view);
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
