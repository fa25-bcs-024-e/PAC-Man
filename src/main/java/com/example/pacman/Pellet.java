package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pellet {

    double x, y;
    boolean eaten = false;

    double radius = 3;

    public Pellet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext gc) {

        if (!eaten) {
            gc.setFill(Color.LIGHTPINK);

            gc.fillOval(
                    x - radius,
                    y - radius,
                    radius * 2,
                    radius * 2
            );
        }
    }
}
