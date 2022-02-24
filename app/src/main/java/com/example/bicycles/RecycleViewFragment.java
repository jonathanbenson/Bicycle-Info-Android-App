package com.example.bicycles;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecycleViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecycleViewFragment extends Fragment implements RecycleViewClickListener, BikeInfoActivityLoader {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private String[] items;

    private RecyclerView recycleView;
    private RecycleViewAdapter adapter;

    RecyclerView.LayoutManager layoutManager;

    public RecycleViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecycleViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecycleViewFragment newInstance(String param1, String param2) {
        RecycleViewFragment fragment = new RecycleViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycle_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        this.layoutManager = new LinearLayoutManager(this.getActivity());


        this.items = getResources().getStringArray(R.array.bicycle_names);
        this.recycleView = view.findViewById(R.id.recycleView1);

        this.adapter = new RecycleViewAdapter(this.items, this);
        this.recycleView.setAdapter(this.adapter);

        this.recycleView.setLayoutManager(new LinearLayoutManager(this.getContext()));


    }

    @Override
    public void recycleViewOnClick(View view, int position) {
        this.loadBikeInfoActivity(position);
    }

    public void loadBikeInfoActivity(int position) {

        Intent intent = new Intent(this.getContext(), BikeInfoActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        intent.putExtra("mainActivityState", "recycleView");
        intent.putExtra("bikeIndex", position);

        this.startActivity(intent);

    }



}