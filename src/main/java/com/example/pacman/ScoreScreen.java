package com.example.pacman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ScoreScreen {

    public Scene getScene(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, 1000, 600, Color.BLACK);
        SoundManager soundManager=new SoundManager();

        ArrayList<Integer> scores = new ArrayList<>();

        // ================= READ FILE =================
        try {

            FileReader fr = new FileReader("highscores.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ================= TITLE =================
        Label title = new Label("H I G H  S C O R E S");
        title.setTextFill(Color.ORANGE);
        title.setFont(Font.font("Comic Sans MS", 40));
        title.setLayoutX(450);
        title.setLayoutY(40);

        root.getChildren().add(title);

        // ================= SCORES =================
        int y = 120;

        for (int i = 0; i < scores.size(); i++) {

            Label scoreLabel = new Label(
                    (i + 1) + ".   " + scores.get(i)
            );

            scoreLabel.setTextFill(Color.WHITE);
            scoreLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));

            scoreLabel.setLayoutX(550);
            scoreLabel.setLayoutY(y);

            y += 60;

            root.getChildren().add(scoreLabel);
        }

        // ================= MENU BUTTON =================
        Button menuButton = new Button("Back to Menu");

        menuButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 25));
        menuButton.setLayoutX(500);
        menuButton.setLayoutY(500);
        menuButton.setPrefWidth(250);
        menuButton.setPrefHeight(60);

        menuButton.setStyle(
                "-fx-background-color: orange;" +
                        "-fx-background-radius: 40;" +
                        "-fx-border-radius: 40;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;"
        );

        menuButton.setOnAction(e -> {

            soundManager.ButtonSound();
            Menu menu = new Menu();
            stage.setScene(menu.getScene(stage));

            stage.setMaximized(false);
            stage.setMaximized(true);
        });

        root.getChildren().add(menuButton);

        return scene;
    }
}