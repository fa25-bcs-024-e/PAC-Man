import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game {

    private Maze maze;
    private CollisionSystem collision;
    private Player player;

    private Canvas canvas;
    private GraphicsContext gc;

    public Game() {

        maze = new Maze();
        collision = new CollisionSystem(maze);
        player = new Player(maze, collision);

        canvas = new Canvas(
                maze.getCols() * maze.TILE_SIZE,
                maze.getRows() * maze.TILE_SIZE
        );

        gc = canvas.getGraphicsContext2D();
    }

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

    private void update() {
        player.update();
    }

    private void render() {
        maze.draw(gc);
        player.draw(gc);
    }
}