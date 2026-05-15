package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ChaseGhost extends Ghost {

    private final Image imgLeft;
    private final Image imgRight;
    private Image currentImage;

    private final Player player;

    public ChaseGhost(Maze maze, CollisionSystem collision,
                      double startX, double startY,
                      Player player) {

        super(maze, collision, startX, startY, Color.BLUE);

        this.player = player;

        imgLeft = new Image(getClass().getResource("/ghostblueleft.jpg").toExternalForm());
        imgRight = new Image(getClass().getResource("/ghostblueright.jpg").toExternalForm());

        currentImage = imgLeft;
    }

    private void updateImage() {
        currentImage = currentDir.equals("RIGHT") ? imgRight : imgLeft;
    }

    private List<String> getValidDirections() {

        List<String> options = new ArrayList<>();

        double step = maze.TILE_SIZE;

        if (collision.canMove(x - step, y, radius)) options.add("LEFT");
        if (collision.canMove(x + step, y, radius)) options.add("RIGHT");
        if (collision.canMove(x, y - step, radius)) options.add("UP");
        if (collision.canMove(x, y + step, radius)) options.add("DOWN");

        return options;
    }

    private String chooseChaseDirection() {

        List<String> options = getValidDirections();

        if (options.isEmpty()) return currentDir;

        String bestDir = currentDir;
        double bestDist = Double.MAX_VALUE;

        for (String dir : options) {

            double testX = x;
            double testY = y;

            switch (dir) {
                case "LEFT" -> testX -= maze.TILE_SIZE;
                case "RIGHT" -> testX += maze.TILE_SIZE;
                case "UP" -> testY -= maze.TILE_SIZE;
                case "DOWN" -> testY += maze.TILE_SIZE;
            }

            double dx = player.getX() - testX;
            double dy = player.getY() - testY;

            double dist = dx * dx + dy * dy;

            if (dist < bestDist) {
                bestDist = dist;
                bestDir = dir;
            }
        }

        return bestDir;
    }

    @Override
    public void reset(double startX, double startY) {

        this.col = (int)(startX / maze.TILE_SIZE);
        this.row = (int)(startY / maze.TILE_SIZE);

        this.x = pixelX();
        this.y = pixelY();

        currentDir = "LEFT";
        updateImage();
    }

    @Override
    public void update() {

        moveSmooth();

        if (isCentered()) {

            currentDir = chooseChaseDirection();
            updateImage();

            move(currentDir);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.drawImage(
                currentImage,
                x - radius,
                y - radius,
                radius * 2,
                radius * 2
        );
        if (Game.gameState.isPowerMode()) {

            gc.setGlobalAlpha(0.5);
            gc.setFill(Color.BLUE);

            gc.fillRoundRect(x - radius, y - radius,
                    radius * 2, radius * 2,
                    13, 13);

            gc.setGlobalAlpha(1.0);
        }
    }
}