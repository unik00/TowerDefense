package mygame;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import mygame.enemy.Enemy;
import mygame.enemy.NormalEnemy;
import mygame.tile.Mountain;
import mygame.tile.tower.Tower;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;

public class GameController extends AnimationTimer {
    private GameField field;
    private GameStage stage;
    private GraphicsContext gc;
    private List<Tower> sampleTower = new ArrayList<Tower>();
    private final long startNanoTime = System.nanoTime();
    private long lastEnemyGenerationTime = 0;

    public GameController(GraphicsContext gc) throws FileNotFoundException {
        this.field = new GameField(GameStage.load("src/stage/demo.txt"));
        this.gc = gc;
    }

    public GameField getField() {
        return field;
    }

    public void setField(GameField field) {
        this.field = field;
    }

    public GameStage getStage() {
        return stage;
    }

    public void setStage(GameStage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(long currentNanoTime) {
        for (Entity e : field.getEntities()) if (e.isAlive()){
            e.draw(gc);
        }

        //ENEMY MOVING
        if (lastEnemyGenerationTime == 0 || (currentNanoTime - lastEnemyGenerationTime) >= (long)1e6){
            field.addEntity(new NormalEnemy(field.getSpawnerX(), field.getSpawnerY(), field));
            lastEnemyGenerationTime = currentNanoTime;
        }
        for(Entity e : field.getEntities( )){
            if (e instanceof Enemy)
                ((Enemy) e).move();
        }

        //TOWER MOVING
        List<Bullet> createdBullet = new ArrayList<Bullet>();
        for (Entity t : field.getEntities()) {
            if (t instanceof Tower)
                //if it's time to fire
                if ( currentNanoTime - ((Tower) t).getLastBulletGenerationTime() > (long) 1e8 / ((Tower) t).getAttackSpeed()) {
                    ((Tower) t).setLastBulletGenerationTime(currentNanoTime);

                    //find nearest enemy
                    Enemy nearestEnemy = null;      //non-exist Enemy
                    for (Entity e : field.getEntities()) {
                        if (e instanceof Enemy && ((Enemy)e).isAlive())
                            if (nearestEnemy == null ||
                                    (t.distance(e) <= ((Tower) t).getAttackRange() && t.distance(e) < t.distance(nearestEnemy)))
                                nearestEnemy = (Enemy) e;
                    }
                    if (nearestEnemy == null) continue;
                    if (t.distance(nearestEnemy) > ((Tower) t).getAttackRange()) continue;

                    //create a bullet
                    Bullet tmp = ((Tower)t).fire(nearestEnemy, currentNanoTime);
                    createdBullet.add(tmp);
                }
        }

        for (Bullet b : createdBullet) {
            field.getEntities().add(b);
        }
        // check for removing destroyed object
        for(Entity e : field.getEntities( )) if (e.isAlive()){
            if (e instanceof Enemy){
                ((Enemy) e).checkHitByBulletAndRemove(currentNanoTime);
            }
            if (e instanceof Bullet){
                e.setX((int)((Bullet) e).calculateCurrentPositionX(currentNanoTime));
                e.setY((int)((Bullet) e).calculateCurrentPositionY(currentNanoTime));
                if (((Bullet)e).goesOutOfBound())
                    e.setAlive(false);
            }
        }

        //DRAG && DROP PROCESSING

        for (Entity e : field.getEntities())
            if (e instanceof Mountain) {

            }

    }

    @Override
    public void start() {
        super.start();
    }
}
