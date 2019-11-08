package mygame.enemy;

import mygame.Entity;

public class Enemy extends Entity {
    private int hitPoint;
    private int speed;
    private int armor;
    private int reward;

    public Enemy() {}
    public Enemy(int x, int y) {
        super(x, y);
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
