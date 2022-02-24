package com.example.bicycles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BikeInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_info);

        Intent intent = this.getIntent();
        int bikeIndex = intent.getIntExtra("bikeIndex", 0);

        String bikeTitle = getResources().getStringArray(R.array.bicycle_names)[bikeIndex];
        String bikeDescription = getResources().getStringArray(R.array.bicycle_descriptions)[bikeIndex];

        TextView bikeTitleView = this.findViewById(R.id.bikeTitle);
        bikeTitleView.setText(bikeTitle);

        TextView bikeDescriptionView = this.findViewById(R.id.bikeDescription);
        bikeDescriptionView.setText(bikeDescription);

        Button backButton = this.findViewById(R.id.backButton);

        BikeInfoActivity currentActivity = this;

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentActivity.goBack();
            }
        });

    }

    public void goBack() {

        Intent previousIntent = this.getIntent();

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("mainActivityState", previousIntent.getStringExtra("mainActivityState"));

        this.startActivity(intent);
    }
}