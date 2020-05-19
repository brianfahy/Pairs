package com.example.pairs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pairs2.R;

public class Activity2 extends AppCompatActivity {

    // name of save file
    public static final String SHARED_PREFS = "sharedPrefs";
    // Global variables
    // High Scores
    int easyHighScore = 1;
    int mediumHighScore = 2;
    int hardHighScore = 3;
    String easyHighName = "Brian";
    String mediumHighName = "Lauren";
    String hardHighName = "LizBuff";

    @Override
    // Start of onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

// get persistently stored name for Easy
        loadData();

// update all of the views for high scores and names
        updateViews();


    } // end of onCreate


    // OnClick Jump to Easy level of game
    public void easyGame(View view) {
        Intent i = new Intent(this, Activity3.class);
        startActivity(i);
    }

    // OnClick Jump to Medium level of game
    public void mediumGame(View view) {
        Intent i = new Intent(this, Activity4.class);
        startActivity(i);
    }

    // OnClick Jump to Hard level of game
    public void hardGame(View view) {
        Intent i = new Intent(this, Activity5.class);
        startActivity(i);
    }

    // OnClick Clear all scores
    public void clearScores(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("keyname1", "Name");
        editor.putInt("keyname2", 0);
        editor.putString("keyname3", "Name");
        editor.putInt("keyname4", 0);
        editor.putString("keyname5", "Name");
        editor.putInt("keyname6", 0);

        editor.apply();

        // refresh all scores
        loadData();
        updateViews();
        Toast.makeText(this, "high score cleared ", Toast.LENGTH_SHORT).show();
    }


    //method to get saved data
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        easyHighName = sharedPreferences.getString("keyname1", "name"); //"" = default value if none
        easyHighScore = sharedPreferences.getInt("keyname2", 0); //"" = default value if none
        mediumHighName = sharedPreferences.getString("keyname3", "name"); //"" = default value if none
        mediumHighScore = sharedPreferences.getInt("keyname4", 0); //"" = default value if none
        hardHighName = sharedPreferences.getString("keyname5", "name"); //"" = default value if none
        hardHighScore = sharedPreferences.getInt("keyname6", 0); //"" = default value if none
    }


    //method to update views
    public void updateViews() {

//      update easy high scores
        TextView easyScoreView = (TextView) findViewById(R.id.easyScore);
        easyScoreView.setText(String.valueOf(easyHighScore));

//      update easyHighScore name
        TextView easyNameView = (TextView) findViewById(R.id.easyName);
        easyNameView.setText(easyHighName);

//      update medium high scores
        TextView mediumScoreView = (TextView) findViewById(R.id.mediumScore);
        mediumScoreView.setText(String.valueOf(mediumHighScore));

//      update mediumHighScore name
        TextView mediumNameView = (TextView) findViewById(R.id.mediumName);
        mediumNameView.setText(mediumHighName);

//      update hard high scores
        TextView hardScoreView = (TextView) findViewById(R.id.hardScore);
        hardScoreView.setText(String.valueOf(hardHighScore));

//      update hardHighScore name
        TextView hardNameView = (TextView) findViewById(R.id.hardName);
        hardNameView.setText(hardHighName);

    }

}
