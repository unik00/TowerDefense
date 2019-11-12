package mygame;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import mygame.enemy.Enemy;
import mygame.enemy.NormalEnemy;
import mygame.enemy.TankerEnemy;
import mygame.tile.tower.Tower;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController extends AnimationTimer {
    private GameField field;
    private GraphicsContext gc;
    private Player player = new Player();
    private GUIBuilder gui;
    private final long startNanoTime = System.nanoTime();
    private long lastEnemyGenerationTime = 0;
    private boolean started = false;
    private boolean gameOver = false;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameController(GraphicsContext gc) throws FileNotFoundException {
        this.field = new GameField(GameStage.load("src/stage/demo.txt"));
        this.gc = gc;
    }

    public GUIBuilder getGui() {
        return gui;
    }

    public void setGui(GUIBuilder gui) {
        this.gui = gui;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameField getField() {
        return field;
    }

    public void setField(GameField field) {
        this.field = field;
    }

    private void removeNullEntities(){
        Iterator itr = field.getEntities().iterator();
        while (itr.hasNext()) {
            Entity e = (Entity)(itr.next());
            if (!e.isAlive()) {
                itr.remove();
            }
            else
                e.draw(gc);
        }

    }

    private void checkGameOverAndStop(){
        for (Entity e : field.getEntities())
            if (e instanceof Enemy && !e.isAlive()) {
                if (((Enemy) e).getHitPoint() <= 0)
                    player.setReward(player.getReward() + ((Enemy) e).getReward());
                else {
                    if (player.getRemainingHearts() > 0) {
                        ImageView[] newHeartsStatus = gui.getHearts();
                        newHeartsStatus[player.getRemainingHearts() - 1].setImage(Config.HEART_DEAD_IMAGE);
                        gui.setHearts(newHeartsStatus);
                        player.setRemainingHearts(player.getRemainingHearts() - 1);
                    }
                    if (player.getRemainingHearts() == 0) {
                        gui.gameOver();
                        setGameOver(true);
                    }
                }
            }
    }
    private void handleEnemiesMoving(long currentNanoTime){
        if (!started) return;
        //ENEMY MOVING
        if (lastEnemyGenerationTime == 0 || (currentNanoTime - lastEnemyGenerationTime) >= (long)2e9){
            Enemy spawned;
            if (currentNanoTime % 2 == 0){
                spawned = new TankerEnemy(field.getSpawnerX(), field.getSpawnerY(), field);
            }
            else spawned = new NormalEnemy(field.getSpawnerX(), field.getSpawnerY(), field);
            field.addEntity(spawned);
            lastEnemyGenerationTime = currentNanoTime;
        }
        for(Entity e : field.getEntities( )){
            if (e instanceof Enemy)
                ((Enemy) e).move();
        }
    }

    private void handleTowersShooting(long currentNanoTime){
        //TOWER MOVING
        List<Bullet> createdBullet = new ArrayList<Bullet>();
        for (Entity t : field.getEntities()) {
            if (t instanceof Tower)
                //if it's time to fire
                if ( currentNanoTime - ((Tower) t).getLastBulletGenerationTime() > (long) 5e8 / ((Tower) t).getAttackSpeed()) {
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
    }

    private  void handleEnemiesGettingHit(long currentNanoTime){
        for(Entity e : field.getEntities( )) {
            if (e.isAlive()) {
                if (e instanceof Enemy) {
                    ((Enemy) e).checkHitByBulletAndRemove(currentNanoTime);
                }
                if (e instanceof Bullet) {
                    e.setX((int) ((Bullet) e).calculateCurrentPositionX(currentNanoTime));
                    e.setY((int) ((Bullet) e).calculateCurrentPositionY(currentNanoTime));
                    if (((Bullet) e).goesOutOfBound())
                        e.setAlive(false);
                }
            }
        }
    }
    private void handleRewardAnnouncement(){
        gui.getRewardAnnouncement().setText("BALANCE: " + String.valueOf(player.getReward()) + "$");
    }

    @Override
    public void handle(long currentNanoTime) {
        if (gameOver) this.stop();
        this.checkGameOverAndStop();
        this.removeNullEntities();
        this.handleEnemiesMoving(currentNanoTime);
        this.handleTowersShooting(currentNanoTime);
        this.handleEnemiesGettingHit(currentNanoTime);
        this.handleRewardAnnouncement();
    }

    @Override
    public void start() {
        super.start();
    }
}
