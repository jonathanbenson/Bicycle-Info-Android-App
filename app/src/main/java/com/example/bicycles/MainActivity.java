package com.example.bicycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button listViewButton = this.findViewById(R.id.listViewButton);

        MainActivity currentActivity = this;

        listViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                currentActivity.loadFragment(new ListViewFragment());
            }
        });

        Button recycleViewButton = this.findViewById(R.id.recycleViewButton);

        recycleViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                currentActivity.loadFragment(new RecycleViewFragment());
            }
        });

        this.loadFragment(new ListViewFragment());

    }

    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();
    }

}