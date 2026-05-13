import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class PelletSystem {

    private final ArrayList<Pellet> pellets = new ArrayList<>();

    private final Cherry cherry;

    private int totalEaten = 0;

    public PelletSystem(Maze maze) {

        int[][] grid = maze.getMaze();

        // 240 pellets approx
        for (int r = 0; r < maze.getRows(); r++) {
            for (int c = 0; c < maze.getCols(); c++) {

                if (grid[r][c] == 0) {
                    if (r == 1 && c == 1) {
                        continue;
                    }




                    double x = c * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;
                    double y = r * maze.TILE_SIZE + maze.TILE_SIZE / 2.0;

                    pellets.add(new Pellet(x, y));
                }
            }
        }

        cherry = new Cherry(
                9.5 * maze.TILE_SIZE,
                11.4 * maze.TILE_SIZE
        );
    }

    public void update(GameState state) {

        cherry.update();

        // spawn cherry at score milestones
        if (state.getScore() == 70 || state.getScore() == 200) {
            cherry.spawn();
        }
    }

    public void checkCollision(Player player, GameState state) {

        for (Pellet p : pellets) {

            if (!p.eaten) {

                double dx = player.getX() - p.x;
                double dy = player.getY() - p.y;

                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < player.getRadius() + p.radius) {

                    p.eaten = true;

                    state.addScore(10);
                    totalEaten++;
                }
            }
        }

        // cherry collision
        if (cherry.active && !cherry.eaten) {

            double dx = player.getX() - cherry.x;
            double dy = player.getY() - cherry.y;

            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < 10) {

                cherry.eaten = true;
                state.addScore(100);
            }
        }
    }

    public void draw(GraphicsContext gc) {

        for (Pellet p : pellets) {
            p.draw(gc);
        }

        cherry.draw(gc);
    }

    public boolean isWin() {

        for (Pellet p : pellets) {
            if (!p.eaten) return false;
        }

        return true;
    }
}
