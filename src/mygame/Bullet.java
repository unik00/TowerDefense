package mygame;

import javafx.scene.image.Image;

public class Bullet extends Entity {
    private int damage;
    private int speed;
    private double firedAngle;
    private long firedTime;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getFiredAngle() {
        return firedAngle;
    }

    public void setFiredAngle(int firedAngle) {
        this.firedAngle = firedAngle;
    }

    public long getFiredTime() {
        return firedTime;
    }
    public void setFiredTime(long firedTime) {
        this.firedTime = firedTime;
    }
    public Bullet () {}
    public Bullet (int x, int y, int damage) {
        super(x, y);
        this.damage = damage;
        setImage(Config.BULLET_IMAGE);
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
