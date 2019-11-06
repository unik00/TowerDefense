package mygame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController extends AnimationTimer {
    private GameField field;
    private GameStage stage;

    public GameController(GraphicsContext gc) {
        this.field = new GameField(GameStage.load("src/stage/demo.txt"));
    }

    @Override
    public void handle(long l) {

    }

    @Override
    public void start() {


    }
    //gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile" + MAP_SPRITES[i][j] + ".png"), j * 64, i * 64);
}
