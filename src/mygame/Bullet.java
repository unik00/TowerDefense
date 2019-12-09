package mygame;

import javafx.scene.image.Image;
import javafx.util.Pair;
import mygame.enemy.Enemy;
import mygame.tile.tower.Tower;

public class Bullet extends Entity {
    private int damage;
    private int speed;
    private double targetX, targetY;
    private double sourceX, sourceY;
    private double directionX, directionY;
    private long firedTime;
    private final double BULLET_SPEED_UNIT = 100;
    private double degree;
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public long getFiredTime() {
        return firedTime;
    }
    public void setFiredTime(long firedTime) {
        this.firedTime = firedTime;
    }
    public Bullet () {}

    public double getDirectionX() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public Bullet (int x, int y, Tower source, Enemy target, long firedTime) {
        super(x, y);
        this.damage = source.getDamage();
        this.speed = 5;
        this.firedTime = firedTime;
        this.targetX = target.getX();// + Config.TILE_SIZE / 2.0;
        this.targetY = target.getY();// + Config.TILE_SIZE / 2.0; // aim the center of enemy
        this.sourceX = source.getX();
        this.sourceY = source.getY();
        this.directionX = this.targetX - this.sourceX;
        this.directionY = this.targetY - this.sourceY;

/*
        System.out.print("target X: ");
        System.out.println(this.targetX);
        System.out.print("target Y: ");
        System.out.println(this.targetY);
        System.out.print("source X: ");
        System.out.println(this.sourceX);
        System.out.print("source Y: ");
        System.out.println(this.sourceY);
*/

        if (directionX == 0) {
            if (directionY < 0) degree = -90;
            else degree = 90;
        }
        else {
            double k = directionY / directionX;
            if (k >= 0) {
                degree = Math.atan(k) * 180 / Math.PI;
                if (directionX < 0) degree -= 180;
            } else if (k < 0) {
                degree = 180 - Math.atan(-k) * 180 / Math.PI;
                if (directionX > 0) degree -= 180;
            }
        }
        double normalisingConstant = Math.sqrt(directionY*directionY+directionX*directionX);
        directionX /= normalisingConstant;
        directionY /= normalisingConstant;
        setImage(Config.BULLET_IMAGE);
    }

    public double getDegree() {
        return degree;
    }

    public double calculateCurrentPositionX(long currentTime){
        long elapsedTime = currentTime - firedTime;
        return (sourceX + directionX * elapsedTime * (speed * BULLET_SPEED_UNIT * 1e-9));
    }
    public double calculateCurrentPositionY(long currentTime){
        long elapsedTime = currentTime - firedTime;
        return (sourceY + directionY * elapsedTime * (speed * BULLET_SPEED_UNIT * 1e-9));
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean goesOutOfBound(){
        double cx = getX() + Config.TILE_SIZE/2.0, cy=getY()+Config.TILE_SIZE/2.0;
        return cx<0 || cx>Config.TILE_SIZE*Config.TILE_HORIZONTAL-7.5
                ||cy<0||cy>Config.TILE_SIZE*Config.TILE_VERTICAL-7.5;
    }
}
