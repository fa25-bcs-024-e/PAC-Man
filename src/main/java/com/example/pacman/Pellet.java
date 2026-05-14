package com.example.pacman;

//no changes made no need to copy

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class Pellet {

    double x, y;
    boolean eaten = false;

    boolean powerPellet; // NEW

    double radius;

    public Pellet(double x, double y, boolean powerPellet) {

        this.x = x;
        this.y = y;

        this.powerPellet = powerPellet;

        if (powerPellet) {
            radius = 8; // bigger
        } else {
            radius = 3; // normal
        }
    }

    public void draw(GraphicsContext gc) {

        if (!eaten) {

            if (powerPellet) {
                gc.setFill(Color.ORANGE);
            } else {
                gc.setFill(Color.LIGHTPINK);
            }

            gc.fillOval(
                    x - radius,
                    y - radius,
                    radius * 2,
                    radius * 2
            );
        }
    }
}