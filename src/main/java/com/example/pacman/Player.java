package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity {

    private String currentDir = "";
    private String nextDir = "";

    private int row;
    private int col;

    public Player(Maze maze, CollisionSystem collision, double x, double y) {
        super(maze, collision);

        speed = 2;
        radius = maze.TILE_SIZE * 0.35;

        // IMPORTANT: use Entity.x / Entity.y (NOT new variables)
        this.x = x;
        this.y = y;

        this.col = (int)(x / maze.TILE_SIZE);
        this.row = (int)(y / maze.TILE_SIZE);
    }

    private boolean isCentered() {

        double cx = col * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double cy = row * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;

        return Math.abs(x - cx) < 1.5 &&
                Math.abs(y - cy) < 1.5;
    }

    @Override
    public void reset(double startX, double startY) {

        this.x = startX;
        this.y = startY;

        this.col = (int)(startX / maze.TILE_SIZE);
        this.row = (int)(startY / maze.TILE_SIZE);

        currentDir = "";
        nextDir = "";
    }

    @Override
    public void update() {

        // direction buffer
        if (!nextDir.isEmpty() && canMove(nextDir)) {
            currentDir = nextDir;
        }

        double tempX = x;
        double tempY = y;

        // movement
        if (currentDir.equals("LEFT"))  tempX -= speed;
        if (currentDir.equals("RIGHT")) tempX += speed;
        if (currentDir.equals("UP"))    tempY -= speed;
        if (currentDir.equals("DOWN"))  tempY += speed;

        // collision
        if (collision.canMove(tempX, tempY, radius)) {
            x = tempX;
            y = tempY;
        }

        // snap to grid
        if (isCentered()) {

            x = col * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
            y = row * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;

            row = (int)(y / maze.TILE_SIZE);
            col = (int)(x / maze.TILE_SIZE);
        }
    }

    private boolean canMove(String dir) {

        double nx = x;
        double ny = y;

        if (dir.equals("LEFT"))  nx -= maze.TILE_SIZE;
        if (dir.equals("RIGHT")) nx += maze.TILE_SIZE;
        if (dir.equals("UP"))    ny -= maze.TILE_SIZE;
        if (dir.equals("DOWN"))  ny += maze.TILE_SIZE;

        return collision.canMove(nx, ny, radius);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void setDirection(KeyCode code) {

        if (code == KeyCode.LEFT)  nextDir = "LEFT";
        if (code == KeyCode.RIGHT) nextDir = "RIGHT";
        if (code == KeyCode.UP)    nextDir = "UP";
        if (code == KeyCode.DOWN)  nextDir = "DOWN";
    }
}