package com.example.pacman;

//change made for maximize add this

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Menu {

    public Scene getScene(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root,1000,600, Color.BLACK);

        Image letterm  = new Image(getClass().getResource("/bubble-letter-m-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_m = new ImageView(letterm);
        letter_m.setX(397.0);
        letter_m.setY(80.0);
        letter_m.setFitWidth(120);
        letter_m.setFitHeight(140);

        Image lettere  = new Image(getClass().getResource("/bubble-letter-e-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_e = new ImageView(lettere);
        letter_e.setX(520.0);
        letter_e.setY(100.0);
        letter_e.setFitWidth(100);
        letter_e.setFitHeight(100);

        Image lettern  = new Image(getClass().getResource("/bubble-letter-n-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_n = new ImageView(lettern);
        letter_n.setX(620.0);
        letter_n.setY(100.0);
        letter_n.setFitWidth(100);
        letter_n.setFitHeight(100);

        Image letteru  = new Image(getClass().getResource("/bubble-letter-u-lowercase-orange-icon.png").toExternalForm());
        ImageView letter_u = new ImageView(letteru);
        letter_u.setX(720.0);
        letter_u.setY(100.0);
        letter_u.setFitWidth(100);
        letter_u.setFitHeight(100);

        Button startGameButton = new Button("Start New Game");
        startGameButton.setFont(Font.font("Comic Sans MS",javafx.scene.text.FontWeight.BOLD, 25));
        startGameButton.setLayoutX(470);
        startGameButton.setLayoutY(250);
        startGameButton.setPrefWidth(300);
        startGameButton.setPrefHeight(65);

        startGameButton.setStyle(
                "-fx-background-color: orange;" +
                        "-fx-background-radius: 40;" +
                        "-fx-border-radius: 40;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;"+
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;"
        );

        Button scoresGameButton = new Button("View Scores");
        scoresGameButton.setFont(Font.font("Comic Sans MS",javafx.scene.text.FontWeight.BOLD, 25));
        scoresGameButton.setLayoutX(470);
        scoresGameButton.setLayoutY(320);
        scoresGameButton.setPrefWidth(300);
        scoresGameButton.setPrefHeight(65);

        scoresGameButton.setStyle(
                "-fx-background-color: orange;" +
                        "-fx-background-radius: 40;" +
                        "-fx-border-radius: 40;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;"+
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;"
        );

        Button quitButton = new Button("Quit");
        quitButton.setFont(Font.font("Comic Sans MS",javafx.scene.text.FontWeight.BOLD, 25));
        quitButton.setLayoutX(470);
        quitButton.setLayoutY(390);
        quitButton.setPrefWidth(300);
        quitButton.setPrefHeight(65);

        quitButton.setStyle(
                "-fx-background-color: orange;" +
                        "-fx-background-radius: 40;" +
                        "-fx-border-radius: 40;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;"+
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;"
        );

        startGameButton.setOnAction(e -> {

            Game game = new Game(stage);

            BorderPane gameRoot = new BorderPane();
            gameRoot.setStyle("-fx-background-color: black;");

            StackPane hudContainer = new StackPane(game.getHud());
            hudContainer.setStyle("-fx-background-color: black;");

            gameRoot.setTop(hudContainer);
            gameRoot.setCenter(game.getCanvas());

            Scene gameScene = new Scene(gameRoot);

            stage.setScene(gameScene);
            stage.setMaximized(false);
            stage.setMaximized(true);
            game.start(gameScene);
        });

        root.getChildren().add(quitButton);

        root.getChildren().add(scoresGameButton);

        root.getChildren().add(startGameButton);

        root.getChildren().addAll(
                letter_e,
                letter_u,
                letter_m,
                letter_n
        );

        return scene;
    }

}
