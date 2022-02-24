package com.example.bicycles;

import androidx.appcompat.app.AppCompatActivity;
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
                currentActivity.loadListViewFragment();
            }
        });

    }

    private void loadListViewFragment() {
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, new ListViewFragment())
                .commit();
    }


}