import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {

    protected double x, y;
    protected double speed;
    protected double radius;

    protected Maze maze;
    protected CollisionSystem collision;

    public Entity(Maze maze, CollisionSystem collision) {
        this.maze = maze;
        this.collision = collision;
    }

    public abstract void update();
    public abstract void draw(GraphicsContext gc);
}
