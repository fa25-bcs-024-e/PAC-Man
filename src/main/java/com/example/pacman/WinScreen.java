package com.example.pacman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WinScreen {


    public Scene getScene(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root,1000,600, Color.BLACK);

        stage.setMaximized(true);



        Image letterw = new Image(getClass().getResource("/bubble-letter-w-orange-icon.png").toExternalForm());
        ImageView letter_w = new ImageView(letterw);
        letter_w.setX(460);
        letter_w.setY(200);
        letter_w.setFitWidth(140);
        letter_w.setFitHeight(142);


        Image letteri = new Image(getClass().getResource("/bubble-letter-i-orange-icon.png").toExternalForm());
        ImageView letter_i = new ImageView(letteri);
        letter_i.setX(570);
        letter_i.setY(200);
        letter_i.setFitWidth(140);
        letter_i.setFitHeight(142);

        Image lettern = new Image(getClass().getResource("/bubble-letter-n-orange-icon.png").toExternalForm());
        ImageView letter_n = new ImageView(lettern);
        letter_n.setX(680);
        letter_n.setY(200);
        letter_n.setFitWidth(140);
        letter_n.setFitHeight(142);

        root.getChildren().addAll(letter_w, letter_i, letter_n);











        return scene;
    }
}

