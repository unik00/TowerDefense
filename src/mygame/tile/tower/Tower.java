package mygame.tile.tower;
import javafx.scene.image.Image;
import mygame.Bullet;
import mygame.enemy.Enemy;
import mygame.tile.Tile;

public class Tower extends Tile {
    private double attackSpeed;
    private int attackRange;
    private int damage = 1;
    private long lastBulletGenerationTime;

    public Tower() {}
    public Tower(int x, int y) {
        super(x, y);
        lastBulletGenerationTime = 0;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public long getLastBulletGenerationTime() {
        return lastBulletGenerationTime;
    }

    public void setLastBulletGenerationTime(long lastBulletGenerationTime) {
        this.lastBulletGenerationTime = lastBulletGenerationTime;
    }

//    Bullet tmp = new Bullet(t.getX(), t.getY(), (Tower) t, nearestEnemy, currentNanoTime);
    public Bullet fire(Enemy target, long currentNanoTime){
        Bullet shot = new Bullet(super.getX(),super.getY(),this,target,currentNanoTime);
        System.out.println(shot.getDegree());
        super.setDirection(shot.getDegree());
        return shot;
    }
}
