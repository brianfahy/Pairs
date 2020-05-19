package com.example.pairs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //    setContentView(R.layout.activity_2); Start on Activity2


    }


    public void startGame(View view) {
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }

}
