package com.example.bicycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BikeInfoActivity extends AppCompatActivity {

    public static void start(Context context, String mainActivityState, int position) {
        // Starts a new BikeInfoActivity

        Intent intent = new Intent(context, BikeInfoActivity.class);

        // Setting these flags will ensure only one activity is running at a time, conserving battery power
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Let the BikeInfoActivity know whether it came from ListView or RecycleView
        intent.putExtra("mainActivityState", mainActivityState);

        // Let the BikeInfoActivity know which bike was selected based on its index
        intent.putExtra("bikeIndex", position);

        // Start a new BikeInfoActivity
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_info);

        // Figure out which bicycle was clicked by retrieving its index from the intent
        Intent intent = this.getIntent();
        int bikeIndex = intent.getIntExtra("bikeIndex", 0);

        // Get the title and description of the bike from the bike index
        // The bike tiles and descriptions are stored as string arrays in the string.xml file
        String bikeTitle = getResources().getStringArray(R.array.bicycle_names)[bikeIndex];
        String bikeDescription = getResources().getStringArray(R.array.bicycle_descriptions)[bikeIndex];

        // Retrieve the title of the bike by indexing the array of bike titles
        TextView bikeTitleView = this.findViewById(R.id.bikeTitle);
        bikeTitleView.setText(bikeTitle);

        // Get the description of the bike by indexing the array of bike descriptions
        TextView bikeDescriptionView = this.findViewById(R.id.bikeDescription);
        bikeDescriptionView.setText(bikeDescription);

        // Get the image of the bike and load it into the ImageView
        this.populateImage(bikeTitle);


        // Set the "go back" button's onClick listener to BikeInfoActivity.goBack()
        // This will cause the MainActivity to start back up when the go back button is clicked
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
        // Go back to the MainActivity

        // Let the MainActivity know what state it was in before it launched the BikeInfoActivity
        // The state is which fragment it had loaded: ListViewFragment or RecycleViewFragment
        String prevMainActivityState = this.getIntent().getExtras().getString("mainActivityState");
        MainActivity.start(this, prevMainActivityState);
    }

    private void populateImage(String bikeTitle) {
        // Loads an image into the ImageView given the title of the bike.

        ImageView imageView = this.findViewById(R.id.bikeImage);

        // Load the image into the image view by formatting it to match the id of the drawable image (located in res/drawable folder
        // All the bike titles names are converted to lowercase and spaces replaced by underscores
        imageView.setImageDrawable(ContextCompat.getDrawable(this, this.getResources().
                getIdentifier(bikeTitle.replaceAll(" ", "_").toLowerCase(), "drawable", this.getPackageName())));
    }

}