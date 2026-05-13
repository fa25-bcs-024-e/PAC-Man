import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;


public class Game {

    private Maze maze;
    private CollisionSystem collision;
    private Player player;
    private GameState gameState;
    private PelletSystem pelletSystem;
    private Label hud;

    private Canvas canvas;
    private GraphicsContext gc;

    public Game() {

        maze = new Maze();
        collision = new CollisionSystem(maze);
        player = new Player(maze, collision);
        gameState = new GameState();
        pelletSystem= new PelletSystem(maze);

        canvas = new Canvas(
                maze.getCols() * maze.TILE_SIZE,
                maze.getRows() * maze.TILE_SIZE
        );


        hud = new Label();

        hud.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 24px;" +
                        "-fx-padding: 10px;"
        );

        gc = canvas.getGraphicsContext2D();
    }
    public Label getHud() {
        return hud;
    }

//    private void drawHUD() {
//
//        gc.fillText("Score: " + gameState.getScore(), 20, 20);
//
//        gc.fillText("High Score: " + gameState.getHighScore(), 150, 20);
//    }

    public Canvas getCanvas() {
        return canvas;
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

//    private void update() {
//        player.update();
//    }

    private void update() {

        player.update();

        // pellets logic (eat + score)
        pelletSystem.update(gameState);
        pelletSystem.checkCollision(player, gameState);

        hud.setText(
                " SCORE: " + gameState.getScore()
                        + "          HIGH SCORE: "
                        + gameState.getHighScore()
        );
    }
    private void render() {

        maze.draw(gc);

        pelletSystem.draw(gc);

        player.draw(gc);

//        drawHUD();
    }

//      private void render() {
//          maze.draw(gc);
//      player.draw(gc);
//  }
}