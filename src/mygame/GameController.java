package mygame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mygame.enemy.NormalEnemy;

import java.io.FileNotFoundException;

public class GameController extends AnimationTimer {
    private GameField field;
    private GameStage stage;
    private GraphicsContext gc;

    private Entity me = new NormalEnemy(0 * 64, 1 * 64);

    public GameController(GraphicsContext gc) throws FileNotFoundException {
        this.field = new GameField(GameStage.load("src/stage/demo.txt"));
        this.gc = gc;
    }

    @Override
    public void handle(long current) {
        field.getEntities().forEach(e  -> e.draw(gc));
    }

    @Override
    public void start() {
        super.start();
    }
}
