package com.example.pacman;

//updated lives system

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game {

    private Maze maze;
    private CollisionSystem collision;

    private Player player;
    private RandomGhost randomGhost;

    private GameState gameState;
    private PelletSystem pelletSystem;
    private LivesSystem livesSystem;

    private Canvas canvas;
    private GraphicsContext gc;

    private Label hud;
    protected Stage stage;
    private boolean gameOverHandled = false;



    public Game(Stage stage) {

        this.stage = stage;

        maze = new Maze();
        collision = new CollisionSystem(maze);

        // Player
        double playerStartX = 1 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double playerStartY = 1 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        player = new Player(maze, collision, playerStartX, playerStartY);

        // Ghost
        double ghostStartX = 8 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double ghostStartY = 8 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        randomGhost = new RandomGhost(maze, collision, ghostStartX, ghostStartY);

        // Systems
        gameState = new GameState();
        pelletSystem = new PelletSystem(maze);
        livesSystem = new LivesSystem(collision, player, randomGhost,
                playerStartX, playerStartY,
                ghostStartX, ghostStartY);

        // Canvas
        canvas = new Canvas(
                maze.getCols() * maze.TILE_SIZE,
                maze.getRows() * maze.TILE_SIZE
        );

        gc = canvas.getGraphicsContext2D();

        // HUD
        hud = new Label();
        hud.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 24px;" +
                        "-fx-padding: 10px;"
        );
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Label getHud() {
        return hud;
    }

    public void start(Scene scene) {

        InputManager input = new InputManager(player);
        input.attach(scene);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        }.start();
    }

    private void update() {

        // pause logic (lives system)
        livesSystem.update();
        if (livesSystem.isGameOver() && !gameOverHandled) {

            gameOverHandled = true;

            LossScreen lossScreen = new LossScreen();
            stage.setScene(lossScreen.getScene(stage));
            stage.setMaximized(false);
            stage.setMaximized(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> {

                Menu menu = new Menu();
                stage.setScene(menu.getScene(stage));
                stage.setMaximized(false);
                stage.setMaximized(true);
            });

            delay.play();

            return;
        }

        if (livesSystem.isPaused()) {
            return;
        }

        player.update();
        randomGhost.update();

        pelletSystem.update(gameState);
        pelletSystem.checkCollision(player, gameState);

        hud.setText(
                " SCORE: " + gameState.getScore()
                        + "          HIGH SCORE: "
                        + gameState.getHighScore()
                        + "\nLIVES: " + livesSystem.getLives()
        );
    }

    private void render() {

        maze.draw(gc);
        pelletSystem.draw(gc);
        player.draw(gc);
        randomGhost.draw(gc);
    }

}