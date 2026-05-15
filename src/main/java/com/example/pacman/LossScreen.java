package com.example.pacman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LossScreen {

    public Scene getScene(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root,1000,600, Color.BLACK);

        stage.setMaximized(true);

        Image letterg = new Image(getClass().getResource("/bubble-letter-g-orange-icon.png").toExternalForm());
        ImageView letter_g = new ImageView(letterg);
        letter_g.setX(380);
        letter_g.setY(140);
        letter_g.setFitWidth(140);
        letter_g.setFitHeight(142);

        Image lettera = new Image(getClass().getResource("/bubble-letter-a-orange-icon.png").toExternalForm());
        ImageView letter_a = new ImageView(lettera);
        letter_a.setX(500);
        letter_a.setY(140);
        letter_a.setFitWidth(130);
        letter_a.setFitHeight(140);

        Image letterm = new Image(getClass().getResource("/bubble-letter-m-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_m = new ImageView(letterm);
        letter_m.setX(620);
        letter_m.setY(100);
        letter_m.setFitWidth(180);
        letter_m.setFitHeight(220);

        Image lettere = new Image(getClass().getResource("/bubble-letter-e-orange-icon.png").toExternalForm());
        ImageView letter_e = new ImageView(lettere);
        letter_e.setX(785);
        letter_e.setY(130);
        letter_e.setFitWidth(130);
        letter_e.setFitHeight(160);

        Image lettero = new Image(getClass().getResource("/bubble-number-0-orange-icon.png").toExternalForm());
        ImageView letter_o = new ImageView(lettero);
        letter_o.setX(380);
        letter_o.setY(290);
        letter_o.setFitWidth(150);
        letter_o.setFitHeight(150);

        Image letterv = new Image(getClass().getResource("/bubble-letter-v-orange-icon.png").toExternalForm());
        ImageView letter_v = new ImageView(letterv);
        letter_v.setX(510);
        letter_v.setY(290);
        letter_v.setFitWidth(150);
        letter_v.setFitHeight(150);

        ImageView letter_e2 = new ImageView(lettere);
        letter_e2.setX(630);
        letter_e2.setY(290);
        letter_e2.setFitWidth(150);
        letter_e2.setFitHeight(150);

        Image letterr = new Image(getClass().getResource("/bubble-letter-r-orange-icon.png").toExternalForm());
        ImageView letter_r = new ImageView(letterr);
        letter_r.setX(750);
        letter_r.setY(290);
        letter_r.setFitWidth(150);
        letter_r.setFitHeight(150);

        Label returningText = new Label("RETURNING TO MENU ...");

        returningText.setTextFill(Color.WHITE);
        returningText.setFont(Font.font("Comic Sans MS", 34));

        returningText.setLayoutX(450);
        returningText.setLayoutY(500);

        root.getChildren().addAll(letter_g,letter_a,letter_m,letter_e,
                letter_o,letter_v,letter_e2,letter_r,returningText);


        return scene;
    }
}
