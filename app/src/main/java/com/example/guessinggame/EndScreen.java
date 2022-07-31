package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {
    private Button playAgain;
    private Button exit;
    private MediaPlayer mySong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        //add stuff to set appropriate points
        Intent intent = getIntent();
        int points = intent.getIntExtra("points", 0);
        String p = String.format("Points: %d", points);
        ((TextView) findViewById(R.id.GameOverPoints)).setText(p);
        playAgain = findViewById(R.id.buttonToPlayAgain);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGuessDifficulty();
            }
        });
        exit = findViewById(R.id.TimerGameExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });
        mySong = MediaPlayer.create(EndScreen.this, R.raw.song2);
        mySong.setVolume(100, 100);
        mySong.start();
    }
    private void openGuessDifficulty() {
        Intent intent = new Intent(this, GuessDifficulty.class);
        startActivity(intent);
    }
    private void goHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    protected void onPause(){
        super.onPause();
        mySong.release();
        finish();
    }
}