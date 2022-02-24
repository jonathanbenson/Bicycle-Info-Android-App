package com.example.bicycles;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewFragment extends Fragment implements RecycleViewClickListener, BikeInfoActivityLoader {

    private String[] items;

    private RecyclerView recycleView;
    private RecycleViewAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    public RecycleViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycle_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Retrieve the names of the bicycle types
        this.items = getResources().getStringArray(R.array.bicycle_names);

        // Get the recycler view that will hold the names of the bicycles
        this.recycleView = view.findViewById(R.id.recycleView1);

        // Initialize and set the adapter for the recycler view
        this.adapter = new RecycleViewAdapter(this.items, this);
        this.recycleView.setAdapter(this.adapter);

        // Initialize and set the layout manager to manage the recycler view
        this.layoutManager = new LinearLayoutManager(this.getActivity());
        this.recycleView.setLayoutManager(new LinearLayoutManager(this.getContext()));


    }

    @Override
    public void recycleViewOnClick(View view, int position) {
        // Handles the onClick event of an item in the recycler view
        this.loadBikeInfoActivity(position);
    }

    @Override
    public void loadBikeInfoActivity(int position) {
        // Starts the BikeInfoActivity based on which bike was selected
        // position = the index of the bike in the list
        BikeInfoActivity.start(this.getContext(), "recycleView", position);
    }



}