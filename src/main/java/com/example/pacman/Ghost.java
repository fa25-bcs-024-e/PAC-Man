package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Ghost extends Entity {

    protected final Color color;
    protected String currentDir = "LEFT";

    protected int row;
    protected int col;

    protected double speed = 1.5;
    protected double radius;

    protected double startX;
    protected double startY;


    protected long spawnTime;
    protected long moveDelay = 1000; // 1 second

    public Ghost(Maze maze, CollisionSystem collision,
                 double startX, double startY, Color color) {

        super(maze, collision);

        this.color = color;
        this.startX = startX;
        this.startY = startY;

        this.row = (int)(startY / maze.TILE_SIZE);
        this.col = (int)(startX / maze.TILE_SIZE);

        this.x = pixelX();
        this.y = pixelY();

        this.radius = maze.TILE_SIZE * 0.35;

        this.spawnTime = System.currentTimeMillis();
    }


    protected double pixelX() {
        return col * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
    }

    protected double pixelY() {
        return row * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
    }


    protected boolean canStartMoving() {
        return System.currentTimeMillis() - spawnTime >= moveDelay;
    }

    protected boolean isCentered() {
        double cx = pixelX();
        double cy = pixelY();

        return Math.abs(x - cx) < 1.5 &&
                Math.abs(y - cy) < 1.5;
    }

    protected boolean move(String dir) {

        int newRow = row;
        int newCol = col;

        switch (dir) {
            case "LEFT" -> newCol--;
            case "RIGHT" -> newCol++;
            case "UP" -> newRow--;
            case "DOWN" -> newRow++;
        }

        if (maze.getMaze()[newRow][newCol] == 0) {
            row = newRow;
            col = newCol;
            currentDir = dir;
            return true;
        }

        return false;
    }

    // ---- SMOOTH PIXEL MOVEMENT ----
    protected void moveSmooth() {

        double tx = pixelX();
        double ty = pixelY();

        if (Math.abs(x - tx) > 0.5) {
            x += Math.signum(tx - x) * speed;
        }

        if (Math.abs(y - ty) > 0.5) {
            y += Math.signum(ty - y) * speed;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

            gc.setFill(color);

        gc.fillRoundRect(
                x - radius,
                y - radius,
                radius * 2,
                radius * 2.2,
                15, 15
        );
    }
    public void returnToSpawn() {

        this.col = (int)(startX / maze.TILE_SIZE);
        this.row = (int)(startY / maze.TILE_SIZE);

        this.x = pixelX();
        this.y = pixelY();

        currentDir = "LEFT";
    }
}