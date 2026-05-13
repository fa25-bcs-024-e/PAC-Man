import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;


public class Player extends Entity {

    private String currentDir = "";
    private String nextDir = "";
    private int row;
    private int col;

    public Player(Maze maze, CollisionSystem collision) {
        super(maze, collision);

        speed = 2;
        radius = maze.TILE_SIZE * 0.35;

        this.row = 1;
        this.col = 1;

        x = col * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        y = row * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
    }

    private boolean isCentered() {

        double cx = col * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        double cy = row * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;

        return Math.abs(x - cx) < 1.5 &&
                Math.abs(y - cy) < 1.5;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void update() {

        // 1. direction buffer
        if (!nextDir.isEmpty() && canMove(nextDir)) {
            currentDir = nextDir;
        }

        double tempX = x;
        double tempY = y;

        // 2. movement
        if (currentDir.equals("LEFT"))  tempX -= speed;
        if (currentDir.equals("RIGHT")) tempX += speed;

        if (currentDir.equals("UP"))    tempY -= speed;
        if (currentDir.equals("DOWN"))  tempY += speed;

        // 3. collision
        if (collision.canMove(tempX, tempY, radius)) {

            x = tempX;
            y = tempY;

        }

        // 4. snap to grid when close
        if (isCentered()) {

            double centerX =
                    ((int)(x / maze.TILE_SIZE)) * maze.TILE_SIZE
                            + maze.TILE_SIZE / 2.0;

            double centerY =
                    ((int)(y / maze.TILE_SIZE)) * maze.TILE_SIZE
                            + maze.TILE_SIZE / 2.0;

            x = centerX;
            y = centerY;

            row = (int)(y / maze.TILE_SIZE);
            col = (int)(x / maze.TILE_SIZE);
        }
    }

    private boolean canMove(String dir) {

        double nx = x;
        double ny = y;

        if (dir.equals("LEFT"))  nx -= maze.TILE_SIZE;
        if (dir.equals("RIGHT")) nx += maze.TILE_SIZE;
        if (dir.equals("UP"))    ny -= maze.TILE_SIZE;
        if (dir.equals("DOWN"))  ny += maze.TILE_SIZE;

        return collision.canMove(nx, ny, radius);
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(Color.YELLOW);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void setDirection(KeyCode code) {

        if (code == KeyCode.LEFT)  nextDir = "LEFT";
        if (code == KeyCode.RIGHT) nextDir = "RIGHT";
        if (code == KeyCode.UP)    nextDir = "UP";
        if (code == KeyCode.DOWN)  nextDir = "DOWN";
    }
}