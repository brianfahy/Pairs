package com.example.pairs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    // Global variables
    // High Scores
    int easyHighScore = 1;
    int mediumHighScore = 2;
    int hardHighScore = 3;
    String easyHighName = "Brian";
    String mediumHighName = "Lauren";
    String hardHighName = "LizBuff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

//      update high scores
        TextView easyScoreView = (TextView) findViewById(R.id.easyScore);
        easyScoreView.setText(String.valueOf(easyHighScore));

//      update easyHighScore name
        TextView easyNameView = (TextView) findViewById(R.id.easyName);
        easyNameView.setText(easyHighName);

//      update high scores
        TextView mediumScoreView = (TextView) findViewById(R.id.mediumScore);
        mediumScoreView.setText(String.valueOf(mediumHighScore));

//      update mediumHighScore name
        TextView mediumNameView = (TextView) findViewById(R.id.mediumName);
        mediumNameView.setText(mediumHighName);

//      update high scores
        TextView hardScoreView = (TextView) findViewById(R.id.hardScore);
        hardScoreView.setText(String.valueOf(hardHighScore));

//      update hardHighScore name
        TextView hardNameView = (TextView) findViewById(R.id.hardName);
        hardNameView.setText(hardHighName);


    }


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

    // OnClick Jump to Easy level of game
    public void hardGame(View view) {
        Intent i = new Intent(this, Activity5.class);
        startActivity(i);
    }

}
