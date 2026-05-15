package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaosGhost extends Ghost{

    private final Image imgLeft;
    private final Image imgRight;
    private Image currentImage;

    private final Random random = new Random();

    private Mode currentMode;

    private final Player player;

    private final int scatterRow;
    private final int scatterCol;

    private long lastModeSwitch;
    private long modeDuration = 4000;

    public ChaosGhost(Maze maze, CollisionSystem collision,
                    double startX, double startY,
                    Player player){
        super(maze, collision, startX, startY, Color.PINK);

        this.player = player;

        imgLeft = new Image(getClass().getResource("/ghostpinkleft.jpg").toExternalForm());
        imgRight = new Image(getClass().getResource("/ghostpinkright.jpg").toExternalForm());

        currentImage = imgLeft;

        scatterRow = maze.getRows() - 2;
        scatterCol = 1;

        currentMode = Mode.values()[
                (int)(Math.random() * Mode.values().length)
                ];
        lastModeSwitch = System.currentTimeMillis();
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

    private void chooseRandomMode() {

        Mode[] modes = Mode.values();

        currentMode = modes[
                (int)(Math.random() * modes.length)
                ];
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

    private void chooseRandomDirection() {

        List<String> options = getValidDirections();

        if (options.isEmpty()) return;

        String dir = options.get(random.nextInt(options.size()));

        currentDir = dir;
        updateImage();
    }

    private String chooseScatterDirection() {

        List<String> options = getValidDirections();

        if (options.isEmpty()) return currentDir;

        String bestDir = currentDir;
        double bestDist = Double.MAX_VALUE;

        for (String dir : options) {

            int testRow = row;
            int testCol = col;

            switch (dir) {
                case "LEFT" -> testCol--;
                case "RIGHT" -> testCol++;
                case "UP" -> testRow--;
                case "DOWN" -> testRow++;
            }

            double dx = scatterCol - testCol;
            double dy = scatterRow - testRow;

            double dist = dx * dx + dy * dy;

            if (dist < bestDist) {
                bestDist = dist;
                bestDir = dir;
            }
        }

        return bestDir;
    }

    private boolean reachedScatterCorner() {
        return row == scatterRow && col == scatterCol;
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

    private enum Mode {
        CHASE,
        RANDOM,
        SCATTER
    }

    @Override
    public void update() {

        moveSmooth();

        if (isCentered()) {

            if (System.currentTimeMillis() - lastModeSwitch > modeDuration) {

                chooseRandomMode();
                lastModeSwitch = System.currentTimeMillis();
            }

            switch (currentMode) {

                case CHASE -> currentDir = chooseChaseDirection();

                case RANDOM ->{
                    List < String > options = getValidDirections();
                    if (!options.contains(currentDir) || options.size() > 2) {
                        chooseRandomDirection();
                }
            }

                case SCATTER -> {

                    currentDir = chooseScatterDirection();

                    if (reachedScatterCorner()) {
                        chooseRandomMode();
                    }
                }
            }

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
