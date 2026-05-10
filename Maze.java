import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Maze {

    public final int TILE_SIZE = 25;

    private final int[][] maze = {

            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,0,1},
            {1,0,1,0,0,1,0,1,0,0,0,1,0,1,0,0,1,0,1},
            {1,0,1,0,0,1,0,1,1,1,1,1,0,1,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1,0,1},
            {1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1},
            {1,1,1,1,0,1,1,1,0,0,0,1,1,1,0,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
            {1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1},
            {1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1},
            {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,1,1,1,0,1,0,1,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public int[][] getMaze() {
        return maze;
    }

    public int getRows() {
        return maze.length;
    }

    public int getCols() {
        return maze[0].length;
    }

    public void draw(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getCols() * TILE_SIZE, getRows() * TILE_SIZE);

        gc.setFill(Color.BLUE);

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {

                if (maze[r][c] == 1) {
                    gc.fillRect(
                            c * TILE_SIZE,
                            r * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE
                    );
                }
            }
        }
    }
}
