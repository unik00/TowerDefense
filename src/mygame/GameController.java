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

    private Entity demoEnemy;

    public GameController(GraphicsContext gc) throws FileNotFoundException {
        this.field = new GameField(GameStage.load("src/stage/demo.txt"));
        this.gc = gc;
        this.demoEnemy = new NormalEnemy(field.getSpawnerX(), field.getSpawnerY());
        this.field.addEntity(demoEnemy);
    }

    @Override
    public void handle(long current) {
    //    System.out.println(current);
        field.getEntities().forEach(e  -> e.draw(gc));
    }

    @Override
    public void start() {
        super.start();
    }
}
