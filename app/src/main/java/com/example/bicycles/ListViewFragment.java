package com.example.bicycles;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListViewFragment extends Fragment implements BikeInfoActivityLoader {

    private String[] items;
    private ArrayAdapter<String> adapter;

    private ListView listView;

    public ListViewFragment() {
        // Required empty public constructor
    }

    public static ListViewFragment newInstance() {
        ListViewFragment fragment = new ListViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Populates the list view with the names of the bicycles

        // Get the list of bicycles from a string array in res/values/string.xml
        this.items = getResources().getStringArray(R.array.bicycle_names);

        // Create and set an adapter for the list view to facilitate adding items
        this.adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, this.items);
        this.listView = (ListView) this.getView().findViewById(R.id.listView1);
        this.listView.setAdapter(this.adapter);

        ListViewFragment currentFragment = this;

        // Create an onClick event listener for each item in the list view
        // It will start a new BikeInfoActivity to display the relevant information about the bike that was clicked on
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                currentFragment.loadBikeInfoActivity(position);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    public void loadBikeInfoActivity(int position) {
        // Starts the BikeInfoActivity based on which bike was selected
        // position = the index of the bike in the list
        BikeInfoActivity.start(this.getContext(), "listView", position);
    }


}