package com.example.pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Cherry {

    double x, y;
    boolean active = false;
    boolean eaten = false;

    long spawnTime;

    private Image image;

    public Cherry(double x, double y) {

        this.x = x;
        this.y = y;

        // load image once
        image = new Image("cherry.png");
    }

    public void spawn() {
        active = true;
        eaten = false;
        spawnTime = System.currentTimeMillis();
    }

    public void update() {

        if (active) {

            long now = System.currentTimeMillis();

            if (now - spawnTime > 9000) {
                active = false;
            }
        }
    }

    public void draw(GraphicsContext gc) {

        if (active && !eaten) {

            gc.drawImage(
                    image,
                    x - 12,
                    y - 12,
                    24,
                    24
            );
        }
    }
}
