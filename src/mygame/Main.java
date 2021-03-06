package mygame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import java.io.FileNotFoundException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle(Config.GAME_NAME);
        Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().addAll(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        //click san sang thi se bat dau man choi moi

        GameController controller  = new GameController(gc);
        GUIBuilder gui = new GUIBuilder(primaryStage, root, controller.getField(), controller.getPlayer());
        controller.setGui(gui);

        gui.showTowerStorage();
        gui.dragAndDrop();
        gui.mouseEnteredTowerStorage();
        gui.showHearts();
        gui.showBalance();
        gui.getStarted(controller);
        gui.showCurrentLevel();

        controller.start();
    }
}
