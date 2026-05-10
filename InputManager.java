import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputManager {

    private Player player;

    public InputManager(Player player) {
        this.player = player;
    }

    public void attach(Scene scene) {

        scene.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            player.setDirection(code);
        });
    }
}
