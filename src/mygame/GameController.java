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
import java.util.ArrayList;
import java.util.List;

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
        for(Entity e : field.getEntities( )){
            if (e instanceof Bullet){
                e.setX((int)((Bullet) e).calculateCurrentPositionX(currentNanoTime));
                e.setY((int)((Bullet) e).calculateCurrentPositionY(currentNanoTime));
              //  System.out.print(e.getX());
           //     System.out.print(" ");
            //    System.out.println(e.getY());
            }
            e.draw(gc);
        }

        //ENEMY PART
        if (lastEnemyGenerationTime == 0 || (currentNanoTime - lastEnemyGenerationTime) >= (long)10e9){
            field.addEntity(new NormalEnemy(field.getSpawnerX(), field.getSpawnerY(), field));
            lastEnemyGenerationTime = currentNanoTime;
        }
        for(Entity e : field.getEntities( )){
            if (e instanceof Enemy)
                ((Enemy) e).move();
            e.draw(gc);
        }

        //TOWER PART
        List<Bullet> createdBullet = new ArrayList<Bullet>();
        for (Entity t : field.getEntities()) {
            if (t instanceof Tower)
                //if it's time to fire
                if ( currentNanoTime - ((Tower) t).getLastBulletGenerationTime() > (long) 1e9 / ((Tower) t).getAttackSpeed()) {
                    ((Tower) t).setLastBulletGenerationTime(currentNanoTime);

                    //find nearest enemy
                    Enemy nearestEnemy = null;      //non-exist Enemy
                    for (Entity e : field.getEntities()) {
                        if (e instanceof Enemy)
                            if (nearestEnemy == null ||
                                    (t.distance(e) <= ((Tower) t).getAttackRange() && t.distance(e) < t.distance(nearestEnemy)))
                                nearestEnemy = (Enemy) e;
                    }
                    if (nearestEnemy == null) continue;
                    if (t.distance(nearestEnemy) > ((Tower) t).getAttackRange()) continue;

                    //create a bullet
                    /*
                    System.out.print("Fired a bullet at time: ");
                    System.out.println(currentNanoTime);
                    System.out.println(nearestEnemy.getX());
                    System.out.println(nearestEnemy.getY());
                    */
                    Bullet tmp = new Bullet(t.getX(), t.getY(), (Tower) t, nearestEnemy, currentNanoTime);

                    createdBullet.add(tmp);
                }
        }
        for (Bullet b : createdBullet) {
            field.getEntities().add(b);
        }
    }

    @Override
    public void start() {
        super.start();
    }
}
