
package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Player extends Entity {

    private String currentDir = "";
    private String nextDir = "";

    private int row;
    private int col;

    // mouth animation
    private double mouthAngle = 60;
    private boolean closing = true;

    public Player(Maze maze, CollisionSystem collision, double x, double y) {

        super(maze, collision);

        speed = 2;
        radius = maze.TILE_SIZE * 0.35;

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

        mouthAngle = 65;
        closing = true;
    }

    @Override
    public void update() {

        // buffered direction
        if (!nextDir.isEmpty() && canMove(nextDir)) {
            currentDir = nextDir;
        }

        double tempX = x;
        double tempY = y;

        // movement
        switch (currentDir) {

            case "LEFT":
                tempX -= speed;
                break;

            case "RIGHT":
                tempX += speed;
                break;

            case "UP":
                tempY -= speed;
                break;

            case "DOWN":
                tempY += speed;
                break;
        }

        // collision
        if (collision.canMove(tempX, tempY, radius)) {

            x = tempX;
            y = tempY;

        } else {

            // stop when hitting wall
            currentDir = "";
        }

        // update row/col continuously
        row = (int)(y / maze.TILE_SIZE);
        col = (int)(x / maze.TILE_SIZE);

        // snap to center
        if (isCentered()) {

            x = col * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
            y = row * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        }

        // mouth animation
        if (!currentDir.isEmpty()) {

            if (closing) {

                mouthAngle -= 2;

                if (mouthAngle <= 10) {
                    closing = false;
                }

            } else {

                mouthAngle += 2;

                if (mouthAngle >= 40) {
                    closing = true;
                }
            }
        }
    }

    private boolean canMove(String dir) {

        double nx = x;
        double ny = y;

        switch (dir) {

            case "LEFT":
                nx -= maze.TILE_SIZE;
                break;

            case "RIGHT":
                nx += maze.TILE_SIZE;
                break;

            case "UP":
                ny -= maze.TILE_SIZE;
                break;

            case "DOWN":
                ny += maze.TILE_SIZE;
                break;
        }

        return collision.canMove(nx, ny, radius);
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(Color.YELLOW);

        // resting = full circle
        if (currentDir.isEmpty()) {

            gc.fillOval(
                    x - radius,
                    y - radius,
                    radius * 2,
                    radius * 2
            );

            return;
        }

        double startAngle = 0;
        double arcLength = 360 - (mouthAngle * 2);

        // rotate mouth according to direction
        switch (currentDir) {

            case "RIGHT":
                startAngle = mouthAngle;
                break;

            case "LEFT":
                startAngle = 180 + mouthAngle;
                break;

            case "UP":
                startAngle = 90 + mouthAngle;
                break;

            case "DOWN":
                startAngle = 270 + mouthAngle;
                break;
        }

        gc.fillArc(
                x - radius,
                y - radius,
                radius * 2,
                radius * 2,
                startAngle,
                arcLength,
                ArcType.ROUND
        );
    }

    public void setDirection(KeyCode code) {

        if (code == KeyCode.LEFT) {
            nextDir = "LEFT";
        }

        if (code == KeyCode.RIGHT) {
            nextDir = "RIGHT";
        }

        if (code == KeyCode.UP) {
            nextDir = "UP";
        }

        if (code == KeyCode.DOWN) {
            nextDir = "DOWN";
        }
    }
}