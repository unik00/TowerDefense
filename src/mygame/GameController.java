package mygame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mygame.enemy.Enemy;
import mygame.enemy.NormalEnemy;
import mygame.tile.tower.NormalTower;
import mygame.tile.tower.Tower;

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
        //Entity nt = new NormalTower(0, 0);    nt.draw(gc);
        for (Entity e : field.getEntities()) {
            if (e instanceof Tower) {
                Enemy target = new Enemy();
                for (Entity v : field.getEntities()) {
                    if (v instanceof Enemy && e.distance(v) < e.distance(target)) target = (Enemy) v;
                }
                target.getFired((Tower) e);
            }
        }

        for (Entity e : field.getEntities()) {
            if (e instanceof Enemy && ((Enemy) e).getHitPoint() <= 0) field.removeEntity(e);
        }
    }

    @Override
    public void start() {
        super.start();
    }
}
