package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    private Button buttonGamemode;
    private Button exit;
    private MediaPlayer mySong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonGamemode = findViewById(R.id.buttonToGamemode);
        buttonGamemode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGuessDifficulty();
            }
        });
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        mySong = MediaPlayer.create(Home.this, R.raw.song2);
        mySong.setVolume(100, 100);
        mySong.start();
    }
    public void openGuessDifficulty() {
        Intent intent = new Intent(this, GuessDifficulty.class);
        startActivity(intent);
    }
    protected void onPause(){
        super.onPause();
        mySong.release();
        finish();
    }
}