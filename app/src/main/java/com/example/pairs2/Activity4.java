package com.example.pairs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activity4 extends AppCompatActivity {
//  Activity4 = Medium mode with 16 tiles (numbered 0-15)


    public static final String SHARED_PREFS = "sharedPrefs";
    //    GLOBAL VARIABLES
//    tileNumber array = to store random number
    List<Integer> tileNumber = new ArrayList<>();
    //    Assign pictures to image array
    Integer[] image = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8};
    //    tileCount is 1=first tile, 2=second tile
    int tileCount = 1;
    //    pairsCount = how many pairs successfully matched
    int pairsCount = 0;
    int maxPairsCount = 8; // max numbers of pairs
    //    tagStore = stores number of first tile turned
    int tagStore = 0;
    String tagString = "initial string";
    int tagValue = 0;
    int score = 0;
    String getName = ""; // stores name of person getting highscore

    //    START OF onCreate CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);


//        generate random tile locations for each number
//        and store them in the arrayList 'tileNumber'
        for (int i = 0; i <= 15; i++) {
            tileNumber.add(i);
        }
        Collections.shuffle(tileNumber);

//        display numbers in LogCat
        for (int index = 0; index < tileNumber.size(); index++) {
//            Log.v("MainActivity", index + " loop: " + tileNumber.get(index));
        }


    }
//    END OF onCreate CODE


    //    Method for flipping a tile
    public void flipTile(View v) {
//        Log.v("MainActivity", " enter flipTile ");
//        Get the tag info from the button pressed
        String tag = v.getTag().toString();

//        Turn the tag from a string to an int
        tagValue = Integer.parseInt(tag);

//        Log.v("MainActivity", " tag to int ");

//        IF FIRST TILE
//        store tag info
        if (tileCount == 1) {
            tagStore = tagValue;
//            Log.v("MainActivity", " first tile ");
            tagString = tag; // copy tag string into tagString

//        find button from tag in button view - look through all buttons in allButtonsView (top level view)
            LinearLayout layout = (LinearLayout) findViewById(R.id.allButtonsView16);
            Button button1 = (Button) layout.findViewWithTag(tag);

//        flip tile to corresponding picture
            button1.setBackgroundResource(image[tileNumber.get(tagValue)]);

//        increment tile count and jump out of method
            tileCount = 2;
            return;
        }


//        IF SECOND TILE
        if (tileCount == 2) {

//        find button from tag in button view - look through all buttons in allButtonsView (top level view)
            LinearLayout layout = (LinearLayout) findViewById(R.id.allButtonsView16);
            final Button button2 = (Button) layout.findViewWithTag(tag); //final as accessed within inner class

            // find first tile from saved tag (tagString)
            final Button button1 = (Button) layout.findViewWithTag(tagString);//final as accessed within inner class

//        flip tile to corresponding picture
            button2.setBackgroundResource(image[tileNumber.get(tagValue)]);

//            CHECK IF MATCH
//            Log.v("MainActivity", " tagStorepic= " + tileNumber.get(tagStore) + " tagValuepic = " + tileNumber.get(tagValue));
//          note : If these numbers are exactly 8 (half number of tiles) apart there should be a MATCH
            int total1 = tileNumber.get(tagStore) - tileNumber.get(tagValue);
            int total2 = tileNumber.get(tagValue) - tileNumber.get(tagStore);
            boolean match = (total1 == 8 || total2 == 8);

//            Log.v("MainActivity", " total1= " + total1 + " total2 = " + total2);
//            Log.v("MainActivity", " Match = " + match);

            if (match) {
                // display a toast message
                Toast.makeText(this, "You got a pair!!", Toast.LENGTH_SHORT).show();

                // short delay of 1000ms then turn both tiles to stars
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button1.setBackgroundResource(R.drawable.star);
                        button2.setBackgroundResource(R.drawable.star);
                    }
                }, 1000);

                score = score + 5;   // match so increase score by 5
                updateScore(score);  //calls updateScore method

                pairsCount = pairsCount + 1; // update total pairs matched
                if (pairsCount == maxPairsCount) {
                    endGame(); // finish game
                }

                //        reset tile count and jump out of method
                tileCount = 1;
                return;
            }
            // else not a match, do the following
            else {

                // short delay of 1000ms then turn both tiles back to default
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button1.setBackgroundResource(android.R.drawable.btn_default);
                        button2.setBackgroundResource(android.R.drawable.btn_default);
                    }
                }, 1000);

                score = score - 1;  //not matched so score goes down
                updateScore(score); //calls updateScore method

                //        reset tile count and jump out of method
                tileCount = 1;
                return;
            }

        }

    }


    //     This method updates the score.
    public void updateScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_view);
        scoreView.setText(String.valueOf(score));
    }

    //     This method runs when at the end of the game.
    public void endGame() {
        TextView scoreView = (TextView) findViewById(R.id.finish);
        scoreView.setText("Game Over");

        // check for highscore
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int easyHighScore = sharedPreferences.getInt("keyname4", 99); //"" = default value if none
        if (score > easyHighScore) {          // if a new high score is achieved

            // call set high score method
            highScoreRoutine();
        }
    }

    //    This method restarts the game
    public void backToStart(View view) {
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }

    // method to save data
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("keyname3", getName);
        editor.putInt("keyname4", score);
        editor.apply();

        Toast.makeText(this, "high score saved", Toast.LENGTH_SHORT).show();
    }


    //    Call this method when a HighScore is achieved
    public void highScoreRoutine() {


        Toast.makeText(this, "HIGH SCORE !! Enter your name", Toast.LENGTH_SHORT).show();

        // set the EditText view
        final EditText yourEditText = (EditText) findViewById(R.id.get_name);

        //get keyboard input routine
        yourEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(yourEditText, InputMethodManager.SHOW_IMPLICIT);

        // check for ENTER key pressed before assigning high score name
        yourEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // when Enter is pressed do the following

                    // assign keyboard input text to getName
                    getName = yourEditText.getText().toString();

                    // truncate name to no more than 10 characters
                    if (getName.length() > 10) {
                        getName = getName.substring(0, 10);
                    }

                    //save highscore and name
                    saveData();
                }
                return false;
            }
        });


    }


}
