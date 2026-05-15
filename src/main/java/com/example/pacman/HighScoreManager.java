package com.example.pacman;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScoreManager {

    // SAVE TOP 5 SCORES
    public static void saveScore(int newScore) {

        try {

            ArrayList<Integer> scores = new ArrayList<>();

            File file = new File("highscores.txt");

            // ================= READ =================
            if (file.exists()) {

                FileReader fr =
                        new FileReader(file);

                BufferedReader br =
                        new BufferedReader(fr);

                String line;

                while ((line = br.readLine()) != null) {

                    scores.add(Integer.parseInt(line));
                }

                br.close();
            }

            // ADD NEW SCORE
            scores.add(newScore);

            // SORT HIGH TO LOW
            Collections.sort(scores);
            Collections.reverse(scores);

            // KEEP TOP 5
            while (scores.size() > 5) {
                scores.remove(scores.size() - 1);
            }

            // ================= WRITE =================
            FileWriter fw =
                    new FileWriter(file);

            BufferedWriter bw =
                    new BufferedWriter(fw);

            for (int score : scores) {

                bw.write(String.valueOf(score));
                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static int loadHighScore() {

        int highest = 0;

        try {

            File file = new File("highscores.txt");

            if (file.exists()) {

                BufferedReader br =
                        new BufferedReader(new FileReader(file));

                String line;

                while ((line = br.readLine()) != null) {

                    int score = Integer.parseInt(line);

                    if (score > highest) {
                        highest = score;
                    }
                }

                br.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return highest;
    }
}