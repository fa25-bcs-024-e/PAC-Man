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

import java.util.List;

public class Game {

    private Maze maze;
    private CollisionSystem collision;

    private Player player;
    private RandomGhost randomGhost;
    private ChaseGhost chaseGhost;
    private ShyGhost shyGhost;
    private ChaosGhost chaosGhost;

    public static GameState gameState;
    private PelletSystem pelletSystem;
    private LivesSystem livesSystem;

    private Canvas canvas;
    private GraphicsContext gc;

    private Label hud;
    protected Stage stage;
    private boolean gameOverHandled = false;
    private boolean winHandled = false;



    public Game(Stage stage) {

        this.stage = stage;

        maze = new Maze();
        collision = new CollisionSystem(maze);
        gameState =new GameState();
        // Player
        double playerStartX = 1 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double playerStartY = 1 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        player = new Player(maze, collision, playerStartX, playerStartY);

        // Ghost
        double randghostStartX = 8 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double randghostStartY = 8 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        randomGhost = new RandomGhost(maze, collision, randghostStartX, randghostStartY);

        double chaseghostStartX = 9 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double chaseghostStartY = 8 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        chaseGhost = new ChaseGhost(maze, collision, chaseghostStartX, chaseghostStartY, player);

        double shyghostStartX = 10 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double shyghostStartY = 8 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        shyGhost = new ShyGhost(maze, collision, shyghostStartX, shyghostStartY, player);

        double chaosghostStartX = 9 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double chaosghostStartY = 7 * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        chaosGhost = new ChaosGhost(maze, collision, chaosghostStartX, chaosghostStartY, player);

        // Systems
        pelletSystem = new PelletSystem(maze);
        List<Ghost> ghosts = List.of(randomGhost, chaseGhost, shyGhost, chaosGhost);

        livesSystem = new LivesSystem(
                collision,
                player,
                ghosts,
                playerStartX,
                playerStartY,
                new double[]{randghostStartX, chaseghostStartX, shyghostStartX, chaosghostStartX},
                new double[]{randghostStartY, chaseghostStartY, shyghostStartY,chaosghostStartY}
        );

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

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };

        gameLoop.start();
    }

    private void update() {

        // pause logic (lives system)
        livesSystem.update();

        // ================= GAME OVER =================
        if (livesSystem.isGameOver() && !gameOverHandled) {

            gameOverHandled = true;
            HighScoreManager.saveScore(gameState.getScore());

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

        // ================= PAUSE =================
        if (livesSystem.isPaused()) {
            return;
        }

        // ================= GAME UPDATE =================
        player.update();
        randomGhost.update();
        chaseGhost.update();
        shyGhost.update();
        chaosGhost.update();


        List<Ghost> ghosts = List.of(randomGhost, chaseGhost, shyGhost, chaosGhost);

        for (Ghost ghost : ghosts) {

            if (collision.circlesTouch(player, ghost)) {

                if (Game.gameState.isPowerMode()) {

                    ghost.returnToSpawn();
                    gameState.addScore(200);

                }
            }
        }


        pelletSystem.update(gameState);
        pelletSystem.checkCollision(player, gameState);


        if (pelletSystem.isWin() && !winHandled) {

            winHandled = true;

            WinScreen winScreen = new WinScreen();
            stage.setScene(winScreen.getScene(stage));

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
        chaseGhost.draw(gc);
        shyGhost.draw(gc);
        chaosGhost.draw(gc);
    }

}