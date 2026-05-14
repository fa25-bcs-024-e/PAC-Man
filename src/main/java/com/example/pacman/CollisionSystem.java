package com.example.pacman;

public class CollisionSystem {

    private Maze maze;

    public CollisionSystem(Maze maze) {
        this.maze = maze;
    }

    public boolean canMove(double x, double y, double radius) {

        int[][] map = maze.getMaze();
        int TILE = maze.TILE_SIZE;

        double left = x - radius;
        double right = x + radius;
        double top = y - radius;
        double bottom = y + radius;

        int leftCol = (int)(left / TILE);
        int rightCol = (int)(right / TILE);
        int topRow = (int)(top / TILE);
        int bottomRow = (int)(bottom / TILE);

        if (topRow < 0 || bottomRow >= map.length ||
                leftCol < 0 || rightCol >= map[0].length) {
            return false;
        }

        if (map[topRow][leftCol] == 1) return false;
        if (map[topRow][rightCol] == 1) return false;
        if (map[bottomRow][leftCol] == 1) return false;
        if (map[bottomRow][rightCol] == 1) return false;

        return true;
    }

    public boolean circlesTouch(Entity a, Entity b) {

        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();

        double radiusSum = a.getRadius() + b.getRadius();

        return dx * dx + dy * dy <= radiusSum * radiusSum;
    }
}
