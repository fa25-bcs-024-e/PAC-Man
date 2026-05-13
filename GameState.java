public class GameState {

    private int score = 0;
    private int highScore = 0;

    private boolean powerMode = false;
    private long powerStartTime = 0;

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

    public void setPowerMode(boolean state) {
        powerMode = state;

        if (state) {
            powerStartTime = System.currentTimeMillis();
        }
    }

    public boolean isPowerMode() {
        return powerMode;
    }

    public void updatePowerMode() {

        if (powerMode) {

            long now = System.currentTimeMillis();

            if (now - powerStartTime > 8000) { // 8 sec
                powerMode = false;
            }
        }
    }

    public void reset() {
        score = 0;
    }
}
