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
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("state", state);
        context.startActivity(intent);
    }

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

        Bundle bundle = this.getIntent().getExtras();

        if (bundle == null || bundle.getString("mainActivityState") == "listView")
            this.loadFragment(new ListViewFragment());
        else
            this.loadFragment(new RecycleViewFragment());

    }

    private void loadFragment(Fragment fragment) {

        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();
    }

}