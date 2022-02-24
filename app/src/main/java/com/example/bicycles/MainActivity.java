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

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        intent.putExtra("mainActivityState", state);
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
        else if (bundle.getString("mainActivityState") == "recycleView")
            this.loadFragment(new RecycleViewFragment());

    }

    private void loadFragment(Fragment fragment) {

        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();
    }

}