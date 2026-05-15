package com.example.pacman;

//maximized but is a bit glitchy

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // ================= MENU SCENE =================
        Group menuRoot = new Group();
        Scene scene = new Scene(menuRoot,1000,600,Color.BLACK);

        // --- Your Pac-Man letter images ---
        Image letterp = new Image(getClass().getResource("/bubble-letter-p-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_p = new ImageView(letterp);
        letter_p.setX(130);
        letter_p.setY(140);
        letter_p.setFitWidth(140);
        letter_p.setFitHeight(142);

        Image lettera = new Image(getClass().getResource("/bubble-letter-a-orange-icon.png").toExternalForm());
        ImageView letter_a = new ImageView(lettera);
        letter_a.setX(240);
        letter_a.setY(140);
        letter_a.setFitWidth(130);
        letter_a.setFitHeight(140);

        Image letterc = new Image(getClass().getResource("/bubble-letter-c-orange-icon.png").toExternalForm());
        ImageView letter_c = new ImageView(letterc);
        letter_c.setX(355);
        letter_c.setY(140);
        letter_c.setFitWidth(127);
        letter_c.setFitHeight(140);

        Image letterm = new Image(getClass().getResource("/bubble-letter-m-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_m = new ImageView(letterm);
        letter_m.setX(510);
        letter_m.setY(120);
        letter_m.setFitWidth(170);
        letter_m.setFitHeight(190);

        ImageView letter_a2 = new ImageView(lettera);
        letter_a2.setX(670);
        letter_a2.setY(150);
        letter_a2.setFitWidth(130);
        letter_a2.setFitHeight(130);

        Image lettern = new Image(getClass().getResource("/bubble-letter-n-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_n = new ImageView(lettern);
        letter_n.setX(790);
        letter_n.setY(150);
        letter_n.setFitWidth(130);
        letter_n.setFitHeight(130);

        // --- Start Button ---
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("Comic Sans MS", javafx.scene.text.FontWeight.BOLD, 25));
        startButton.setLayoutX(445);
        startButton.setLayoutY(400);
        startButton.setPrefWidth(120);
        startButton.setPrefHeight(65);

        startButton.setStyle(
                "-fx-background-color: orange;" +
                        "-fx-background-radius: 40;" +
                        "-fx-border-radius: 40;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;"
        );

        // ================= GAME SCENE =================
        Game game = new Game(stage);

        BorderPane gameRoot = new BorderPane();
        gameRoot.setStyle("-fx-background-color: black;");

        StackPane hudContainer = new StackPane(game.getHud());
        hudContainer.setAlignment(Pos.CENTER);

        gameRoot.setTop(hudContainer);
        gameRoot.setCenter(game.getCanvas());

        startButton.setOnAction(e -> {
            Menu menu = new Menu();
            stage.setScene(menu.getScene(stage));
            stage.setMaximized(false);
            stage.setMaximized(true);
        });

        // ================= ADD MENU ELEMENTS =================
        menuRoot.getChildren().addAll(
                letter_p,
                letter_a,
                letter_c,
                letter_m,
                letter_a2,
                letter_n,
                startButton
        );

        // ================= SHOW MENU FIRST =================
        stage.setScene(scene);
        stage.setTitle("Pac-Man");
        stage.setMaximized(true);
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(com.example.pacman.Main.class, args);
    }
//<<<<<<< HEAD
//
//=======
//>>>>>>> c31e235 (added lives and smwhat fixed maximize)
}