package com.example.bicycles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BikeInfoActivity extends AppCompatActivity {

    private String bikeTitle, bikeDescription;


    public BikeInfoActivity(String title, String description) {
        this.bikeTitle = title;
        this.bikeDescription = description;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_info);

        TextView bikeTitleView = this.findViewById(R.id.bikeTitle);
        bikeTitleView.setText(this.bikeTitle);

        TextView bikeDescriptionView = this.findViewById(R.id.bikeDescription);
        bikeDescriptionView.setText(this.bikeDescription);

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