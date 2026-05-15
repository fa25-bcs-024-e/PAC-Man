
package com.example.pacman;

public class GameState {

    private int score = 0;
    private int highScore = 0;

    // POWER MODE
    private boolean powerMode = false;

    private long powerStartTime;

    public void activatePowerMode() {

        powerMode = true;

        powerStartTime = System.currentTimeMillis();
    }

    public boolean isPowerMode() {

        if (powerMode) {

            long elapsed =
                    System.currentTimeMillis() - powerStartTime;

            if (elapsed >= 6000) {
                powerMode = false;
            }
        }

        return powerMode;
    }

    // SCORE
    public void addScore(int points) {

        score += points;

        if (score > highScore) {
            highScore = score;
        }
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void reset() {

        score = 0;
        powerMode = false;
    }
}
