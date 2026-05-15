package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGhost extends Ghost {

    private final Random random = new Random();

    private final Image imgLeft;
    private final Image imgRight;

    private Image currentImage;

    public RandomGhost(Maze maze, CollisionSystem collision,
                       double x, double y) {

        super(maze, collision, x, y, Color.RED);

        imgLeft = new Image(getClass().getResource("/ghostredleft.jpg").toExternalForm());
        imgRight = new Image(getClass().getResource("/ghostredright.jpg").toExternalForm());

        currentImage = imgLeft;
    }

    private void updateImage() {
        currentImage = currentDir.equals("RIGHT") ? imgRight : imgLeft;
    }

    private List<String> getValidDirections() {

        List<String> valid = new ArrayList<>();

        double step = maze.TILE_SIZE;

        // LEFT
        if (collision.canMove(x - step, y, radius)) {
            valid.add("LEFT");
        }

        // RIGHT
        if (collision.canMove(x + step, y, radius)) {
            valid.add("RIGHT");
        }

        // UP
        if (collision.canMove(x, y - step, radius)) {
            valid.add("UP");
        }

        // DOWN
        if (collision.canMove(x, y + step, radius)) {
            valid.add("DOWN");
        }

        return valid;
    }

    private void chooseRandomDirection() {

        List<String> options = getValidDirections();

        if (options.isEmpty()) return;

        String dir = options.get(random.nextInt(options.size()));

        currentDir = dir;
        updateImage();
    }

    @Override
    public void reset(double startX, double startY) {

        this.col = (int) (startX / maze.TILE_SIZE);
        this.row = (int) (startY / maze.TILE_SIZE);

        this.x = pixelX();
        this.y = pixelY();

        currentDir = "LEFT";
        updateImage();
    }

    @Override
    public void update() {

        if (!canStartMoving()) return;

        moveSmooth();

        if (isCentered()) {

            List<String> options = getValidDirections();

            // If current direction is blocked OR at junction, pick new direction
            if (!options.contains(currentDir) || options.size() > 2) {
                chooseRandomDirection();
            }

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