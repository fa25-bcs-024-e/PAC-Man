package com.example.pacman;

public class LivesSystem {

    private boolean paused = false;
    private long pauseStartTime = 0;

    private final CollisionSystem collision;
    private final Player player;
    private final Ghost ghost;

    private final double playerStartX;
    private final double playerStartY;
    private final double ghostStartX;
    private final double ghostStartY;

    public LivesSystem(CollisionSystem collision, Player player, Ghost ghost, double playerStartX, double playerStartY, double ghostStartX, double ghostStartY) {

        this.collision = collision;
        this.player = player;
        this.ghost = ghost;

        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.ghostStartX = ghostStartX;
        this.ghostStartY = ghostStartY;
    }

    public void update() {

        if (paused) {

            if (System.currentTimeMillis() - pauseStartTime >= 2000) {
                paused = false;
                resetPositions();
            }

            return;
        }

        if (collision.circlesTouch(player, ghost)) {
            paused = true;
            pauseStartTime = System.currentTimeMillis();
        }
    }

    private void resetPositions() {
        player.reset(playerStartX, playerStartY);
        ghost.reset(ghostStartX, ghostStartY);
    }

    public boolean isPaused() {
        return paused;
    }
}