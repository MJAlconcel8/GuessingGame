package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuessDifficulty extends AppCompatActivity {
    private Button easy;
    private Button medium;
    private Button hard;
    private Button back;
    private MediaPlayer mySong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_difficulty);
        easy = findViewById(R.id.easyGuess);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEasy();
            }
        });
        medium = findViewById(R.id.mediumGuess);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMedium();
            }
        });
        hard = findViewById(R.id.hardGuess);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHard();
            }
        });
        back = findViewById(R.id.GuessBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        mySong = MediaPlayer.create(GuessDifficulty.this, R.raw.song2);
        mySong.setVolume(100, 100);
        mySong.start();
    }
    private void openEasy() {
        Intent intent = new Intent(this, GuessingModeEasy.class);
        startActivity(intent);
    }
    private void openMedium(){
        Intent intent = new Intent(this, GuessingModeMedium.class);
        startActivity(intent);
    }

    private void openHard(){
        Intent intent = new Intent(this, GuessingModeHard.class);
        startActivity(intent);
    }

    private void goBack(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    protected void onPause(){
        super.onPause();
        mySong.release();
        finish();
    }

}