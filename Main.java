import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;


public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Game game = new Game();

//        Scene scene = new Scene(new StackPane(game.getCanvas()));
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");



        StackPane hudContainer = new StackPane(game.getHud());
        hudContainer.setStyle("-fx-background-color: black;");
        hudContainer.setAlignment(Pos.CENTER);

        root.setTop(hudContainer);
        root.setCenter(game.getCanvas());



//        root.setTop(game.getHud());


        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();


        stage.setScene(scene);
        stage.setTitle("Pac-Man Game");
        stage.show();

        game.start(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
} end
