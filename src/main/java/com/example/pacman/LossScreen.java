package com.example.pacman;

//add this

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LossScreen {

    public Scene getScene(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root,1000,600, Color.BLACK);

        stage.setMaximized(true);
        // later add images of GAME OVER


        return scene;
    }
}
