package com.example.guessinggame;

public class GuessingGameModel {
    private int randNum;
    private int numGuesses;
    private String guess;
    public GuessingGameModel(int difficulty){
        randNum = 1 + (int)(50 * Math.random());
        guess = "";
        switch(difficulty){
            case 1:
                numGuesses = 12;
                break;
            case 2:
                numGuesses = 10;
                break;
            case 3:
                numGuesses = 5;
                break;
        }
    }
    public int getRandNum(){return randNum;}
    public int getNumGuesses(){return numGuesses;}
    public void setNumGuesses(int numGuesses){this.numGuesses = numGuesses;}
    public void setGuess(String guess){this.guess = guess;}
    //*************************************************************************************** */

    /**
     * Returns true if guess is an integer (valid). Returns false if guess is not an integer (invalid)
     * Method won't be used outside this file
     *
     * @return
     */
    private boolean isValidGuess(){
        boolean isValid = true;
        try{
            Integer num = Integer.parseInt(guess);
        }
        catch(Exception e){
            isValid = false;
        }
        return isValid;
    }

    /**
     * Returns true if the user has correctly guessed the number. Returns false if user has guessed incorectly
     * @param  - the user guess as an integer
     * @return
     */
    public boolean userGuessEvaluate(){
        boolean isCorrect = false;
        if(isValidGuess()){
            int guessInt = Integer.parseInt(guess);
            if(guessInt == randNum){
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    public String hint(){
        String output = "";
        if(!isValidGuess()){
            output = "Make sure your guess is an integer from 1 - 50!";
        }
        else{
            int guessInt = Integer.parseInt(guess);
            if(guessInt < randNum){
                output = String.format("Your guess (%s) is too low", guess);
            }
            else if(guessInt > randNum){
                output = String.format("Your guess (%s) is too high", guess);
            }
        }

        return output;
    }
}
