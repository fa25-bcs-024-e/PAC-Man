package com.example.pacman;

import javafx.scene.media.AudioClip;

public class SoundManager {

    private static final AudioClip ghostEaten =
            new AudioClip(SoundManager.class.getResource("/freesound_community-pixel-death-66829.mp3").toExternalForm());

    private static final AudioClip pelletEaten =
            new AudioClip(SoundManager.class.getResource("/pacmaneat.mp3").toExternalForm());

    private static final AudioClip playerDie =
            new AudioClip(SoundManager.class.getResource("/pacmandeath.mp3").toExternalForm());

    private static final AudioClip clickSound =
            new AudioClip(SoundManager.class.getResource("/mixkit-camera-shutter-click-1133.wav").toExternalForm());

    private static final AudioClip startSound =
            new AudioClip(SoundManager.class.getResource("/Pacman Start Sound Effect.mp3").toExternalForm());

    public static void GhostDieSound() {
        ghostEaten.play();
    }

    public static void PelletSound() {
        pelletEaten.play();
    }

    public static void PlayerDieSound() {
        playerDie.play();
    }

    public static void ButtonSound() {
        clickSound.play();
    }

    public static void StartSound() {
        startSound.play();
    }
}