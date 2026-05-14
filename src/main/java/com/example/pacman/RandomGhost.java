package com.example.pacman;

import javafx.scene.paint.Color;

import java.util.Random;

public class RandomGhost extends Ghost {

    private final String[] dirs = {"UP", "DOWN", "LEFT", "RIGHT"};
    private final Random random = new Random();

    public RandomGhost(Maze maze, CollisionSystem collision,
                       double x, double y) {

        super(maze, collision, x, y, Color.RED);
    }

    private void chooseRandomDirection() {

        boolean canmove=false;
        while(!canmove) {

            String dir = dirs[random.nextInt(dirs.length)];

            double testX = x;
            double testY = y;

            switch (dir) {
                case "LEFT" -> testX -= maze.TILE_SIZE;
                case "RIGHT" -> testX += maze.TILE_SIZE;
                case "UP" -> testY -= maze.TILE_SIZE;
                case "DOWN" -> testY += maze.TILE_SIZE;
            }

            if (collision.canMove(testX, testY, radius)) {

                currentDir = dir;
                canmove = true;
            }
        }
    }

    @Override
    public void reset(double startX, double startY) {
        this.col = (int)(startX / maze.TILE_SIZE);
        this.row = (int)(startY / maze.TILE_SIZE);

        this.x = pixelX();
        this.y = pixelY();

        currentDir = "LEFT";
    }

    @Override
    public void update() {

        moveSmooth();

        if (isCentered()) {

            if (!move(currentDir)) {
                chooseRandomDirection();
            }
        }
    }
}