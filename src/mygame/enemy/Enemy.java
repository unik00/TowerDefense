package mygame.enemy;

import mygame.Bullet;
import mygame.Config;
import mygame.Entity;
import mygame.GameField;
import mygame.tile.Road;
import mygame.tile.Spawner;
import mygame.tile.Target;

public class Enemy extends Entity {
    static private int dx[] = {0, 0, -1, 1};
    static private int dy[] = {-1, 1, 0, 0};

    private int velocityX;
    private int velocityY;


    public int getVelocityX() {
        return velocityX;
    }
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    public int getVelocityY() {
        return velocityY;
    }
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    private int hitPoint;
    private int speed;
    private int armor;
    private int reward;
    public Enemy() {
    }

    public Enemy(int x, int y) {
        super(x, y);
    }

    public Enemy(int x, int y, GameField field) {
        super(x, y, field);
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

    public boolean checkCollisionWithBullet(Bullet b, long currentNanoSecond){
        double x = b.calculateCurrentPositionX(currentNanoSecond);
        double y = b.calculateCurrentPositionY(currentNanoSecond);
        int topLeftX = super.getX() / Config.TILE_SIZE * Config.TILE_SIZE;
        int topLeftY = super.getY() / Config.TILE_SIZE * Config.TILE_SIZE;
        if (x >= topLeftX && x < topLeftX+Config.TILE_SIZE && y>=topLeftY&&y<topLeftY+Config.TILE_SIZE){
            return true;
        }
        return false;
    }
    public boolean checkHitByBulletAndRemove(long currentNanoSecond){
        for(Entity e : super.getField().getEntities()){
            if (e instanceof Bullet && e.isAlive()){
                if (checkCollisionWithBullet((Bullet)e, currentNanoSecond)){
                    super.setAlive(false);
                    e.setAlive(false);
                    return true;
                }
            }
        }
        return false;
    }
    public void move() {
        if (super.getX()==getField().getTargetX() && super.getY()==getField().getTargetY()){
            super.setAlive(false);
        }
        // TODO: add speed
        if (super.getX() % Config.TILE_SIZE == 0 && super.getY() % Config.TILE_SIZE == 0) {
            boolean broken = false;
            int topLeftX = super.getX() / Config.TILE_SIZE * Config.TILE_SIZE;
            int topLeftY = super.getY() / Config.TILE_SIZE * Config.TILE_SIZE;

            for (int i = 0; i < 4 && !broken; ++i) {
                if (dx[i] == -getVelocityX() && dy[i] == -getVelocityY())
                    continue;
                for (Entity e : super.getField().getEntities())
                    if (e instanceof Road) {
                        if (topLeftX + Config.TILE_SIZE * dx[i] == e.getX()
                                && topLeftY + Config.TILE_SIZE * dy[i] == e.getY()
                        ) {
                            super.setDirection(dx[i], dy[i]);

                            setVelocityX(dx[i]);
                            setVelocityY(dy[i]);
                            super.setX(super.getX() + dx[i]);
                            super.setY(super.getY() + dy[i]);
                            broken = true;
                            break;
                        }
                    }
            }
        }
        else {
            // if not in the intersection of grid, continue going
            super.setX(super.getX() + getVelocityX());
            super.setY(super.getY() + getVelocityY());
        }
    }
}


