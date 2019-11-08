package mygame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mygame.enemy.Enemy;
import mygame.enemy.NormalEnemy;

import java.io.FileNotFoundException;

public class GameController extends AnimationTimer {
    private GameField field;
    private GameStage stage;
    private GraphicsContext gc;
    private final long startNanoTime = System.nanoTime();
    private long lastEnemyGenerationTime = 0;
    public GameController(GraphicsContext gc) throws FileNotFoundException {
        this.field = new GameField(GameStage.load("src/stage/demo.txt"));
        this.gc = gc;
    }

    @Override
    public void handle(long currentNanoTime) {
        System.out.println(currentNanoTime - lastEnemyGenerationTime);
        System.out.println("--");
        if (lastEnemyGenerationTime == 0 || (currentNanoTime - lastEnemyGenerationTime) >= (long)1e9){
            field.addEntity(new NormalEnemy(field.getSpawnerX(), field.getSpawnerY()));
            lastEnemyGenerationTime = currentNanoTime;
        }
        for(Entity e : field.getEntities()){
            if (e instanceof Enemy)
                e.setX(e.getX() + 1);
        }
        field.getEntities().forEach(e  -> e.draw(gc));
    }

    @Override
    public void start() {
        super.start();
    }
}
