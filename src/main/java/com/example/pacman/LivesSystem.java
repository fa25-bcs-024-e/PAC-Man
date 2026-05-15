package com.example.pacman;

import java.util.List;

public class LivesSystem {

    private boolean paused = false;
    private long pauseStartTime = 0;

    private int lives = 3;
    private boolean gameOver = false;

    private final CollisionSystem collision;
    private final Player player;
    private final List<Ghost> ghosts;

    private final double playerStartX;
    private final double playerStartY;

    private final double[] ghostStartX;
    private final double[] ghostStartY;

    public LivesSystem(CollisionSystem collision,
                       Player player,
                       List<Ghost> ghosts,
                       double playerStartX,
                       double playerStartY,
                       double[] ghostStartX,
                       double[] ghostStartY) {

        this.collision = collision;
        this.player = player;
        this.ghosts = ghosts;

        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.ghostStartX = ghostStartX;
        this.ghostStartY = ghostStartY;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isPaused() {
        return paused;
    }

    public void update() {

        // 1. Hard stop if game already over
        if (gameOver) return;

        // 2. Pause handling (countdown between lives)
        if (paused) {
            if (System.currentTimeMillis() - pauseStartTime >= 2000) {
                paused = false;
                resetPositions();
            }
            return;
        }

        // 3. Collision check
        for (Ghost ghost : ghosts) {
            if (collision.circlesTouch(player, ghost)) {

                lives--;

                if (lives <= 0) {
                    gameOver = true;
                }

                paused = true;
                pauseStartTime = System.currentTimeMillis();
                break;
            }
        }
    }

    private void resetPositions() {

        player.reset(playerStartX, playerStartY);

        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).reset(ghostStartX[i], ghostStartY[i]);
        }
    }
}