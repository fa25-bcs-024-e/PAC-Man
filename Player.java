import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity {

    private String currentDir = "";
    private String nextDir = "";

    public Player(Maze maze, CollisionSystem collision) {
        super(maze, collision);

        speed = 2;
        radius = maze.TILE_SIZE * 0.35;

        int spawnRow = 1;
        int spawnCol = 1;

        x = spawnCol * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
        y = spawnRow * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
    }

    @Override
    public void update() {

        // 1. direction buffer
        if (canMove(nextDir)) {
            currentDir = nextDir;
        }

        // 2. axis-based movement (smooth sliding)

        double tempX = x;
        double tempY = y;

        // X movement
        if (currentDir.equals("LEFT"))  tempX -= speed;
        if (currentDir.equals("RIGHT")) tempX += speed;

        if (collision.canMove(tempX, y, radius)) {
            x = tempX;
        }

        // Y movement
        if (currentDir.equals("UP"))    tempY -= speed;
        if (currentDir.equals("DOWN"))  tempY += speed;

        if (collision.canMove(x, tempY, radius)) {
            y = tempY;
        }
    }

    private boolean canMove(String dir) {

        double nx = x;
        double ny = y;

        if (dir.equals("LEFT"))  nx -= speed;
        if (dir.equals("RIGHT")) nx += speed;
        if (dir.equals("UP"))    ny -= speed;
        if (dir.equals("DOWN"))  ny += speed;

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