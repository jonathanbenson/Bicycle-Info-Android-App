package com.example.bicycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context, String state) {
        // A function that ListViewFragment and RecycleViewFragment call to return to MainActivity

        Intent intent = new Intent(context, MainActivity.class);

        // Adding these flags will ensure only one activity is running at a time, conserving battery life
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Tell the MainActivity whether to load a ListViewFragment or RecycleViewFragment into its FragmentContainerView
        // "mainActivityState" can be either "listView" or "recycleView"
        intent.putExtra("mainActivityState", state);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button listViewButton = this.findViewById(R.id.listViewButton);

        MainActivity currentActivity = this;

        // Set the list view button's event listener
        // When clicked, it will load the FragmentContainerView with a ListViewFragment
        listViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                currentActivity.loadFragment(new ListViewFragment());
            }
        });


        Button recycleViewButton = this.findViewById(R.id.recycleViewButton);

        // Set the recycle view button's event listener
        // When clicked, it will load the FragmentContainerView with a RecycleViewFragment
        recycleViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                currentActivity.loadFragment(new RecycleViewFragment());
            }
        });

        Bundle bundle = this.getIntent().getExtras();

        // Initialize the FragmentContainerView

        // If the app has just started up or it is returning from BikeInfoActivity having previously loaded a ListViewFragment
        // then load the ListViewFragment back into the FragnmentContainerView
        if (bundle == null || bundle.getString("mainActivityState") == "listView")
            this.loadFragment(new ListViewFragment());

        // Else if the app is returning from BikeInfoActivity having previously loaded a RecycleViewFragment
        // then load the RecycleViewFragment back into the FragmentContainerView
        else if (bundle.getString("mainActivityState") == "recycleView")
            this.loadFragment(new RecycleViewFragment());

    }

    private void loadFragment(Fragment fragment) {
        // Loads a fragment into the FragmentContainerView
        // It will either be a ListViewFragment or a RecycleViewFragment
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();
    }

}