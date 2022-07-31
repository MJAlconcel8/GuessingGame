package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GuessingModeEasy extends AppCompatActivity {
    private Button endScreen;
    private Button guess;
    private MediaPlayer mySong;
    private int points;
    private boolean onVisit;
    private GuessingGameModel[] storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_mode_easy);
        endScreen = findViewById(R.id.buttonToQuitEasy);
        guess = findViewById(R.id.SubmitEasy);
        mySong = MediaPlayer.create(GuessingModeEasy.this, R.raw.song2);
        mySong.setVolume(100, 100);
        mySong.start();
        storage = new GuessingGameModel[1];
        onVisit = true;
        points = 0;
        updatePoints();
        endScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEnd();
            }
        });
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitGuess();
            }
        });
    }
    private void openEnd() {
        Intent intent = new Intent(this, EndScreen.class);
        intent.putExtra("points", points);
        startActivity(intent);
    }
    private void submitGuess(){//removed View v
        EditText input = (EditText) findViewById(R.id.inputEasy);
        if(onVisit) {//on landing/very first guess of a round
            GuessingGameModel obj = new GuessingGameModel(1);
            storage[0] = obj;
            onVisit = false;
            storage[0].setGuess(input.getText().toString());
            input.getText().clear();
            boolean isCorrect = storage[0].userGuessEvaluate();
            if(isCorrect){
                input.getText().clear();
                ((TextView) findViewById((R.id.GuessesLeftEasy))).setText("You guessed it");
                points += 1;
                updatePoints();
                ((TextView) findViewById(R.id.HintEasy)).setText("Pick a new number between 1 and 50");
                ((TextView) findViewById(R.id.OutputEasy)).setText("New round Started");
                storage = new GuessingGameModel[1];
                onVisit = true;
            }
            else{//incorrect guess
                storage[0].setNumGuesses(storage[0].getNumGuesses() - 1);
                ((TextView) findViewById((R.id.GuessesLeftEasy))).setText(String.format("%d guesses left", storage[0].getNumGuesses()));
                ((TextView) findViewById((R.id.OutputEasy))).setText("Incorrect");
                hints();
            }
        }
        else if(storage[0].getNumGuesses() >= 1){
            storage[0].setGuess(input.getText().toString());
            input.getText().clear();
            boolean isCorrect = storage[0].userGuessEvaluate();
            if(isCorrect){ //just for testing for now
                input.getText().clear();
                ((TextView) findViewById((R.id.GuessesLeftEasy))).setText("You guessed it");
                points += 1;
                updatePoints();
                ((TextView) findViewById(R.id.HintEasy)).setText("Pick a new number between 1 and 50");
                ((TextView) findViewById(R.id.OutputEasy)).setText("New round Started");
                storage = new GuessingGameModel[1];
                onVisit = true;
            }
            else{//incorrect guess
                storage[0].setNumGuesses(storage[0].getNumGuesses() - 1);
                if(storage[0].getNumGuesses() == 0){
                    input.getText().clear();
                    ((TextView) findViewById(R.id.GuessesLeftEasy)).setText("You Lost");
                    ((TextView) findViewById(R.id.HintEasy)).setText("Guess a new number between 1 and 50");
                    ((TextView) findViewById(R.id.OutputEasy)).setText(String.format("Secret number was %d\nNew Game Started", storage[0].getRandNum()));
                    points = 0;
                    updatePoints();
                    storage = new GuessingGameModel[1];
                    onVisit = true;
                }
                else {
                    ((TextView) findViewById((R.id.OutputEasy))).setText("Incorrect");
                    ((TextView) findViewById((R.id.GuessesLeftEasy))).setText(String.format("%d guesses left", storage[0].getNumGuesses()));
                    hints();
                }
            }
        }
    }

    private void hints(){
        ((TextView) findViewById(R.id.HintEasy)).setText(storage[0].hint());
    }

    private void updatePoints(){
        ((TextView) findViewById(R.id.pointsEasy)).setText(String.format("Points: %d", this.points));
    }

    protected void onPause(){
        super.onPause();
        mySong.release();
        finish();
    }
}