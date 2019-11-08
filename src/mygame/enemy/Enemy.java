package mygame.enemy;

import mygame.Config;
import mygame.Entity;
import mygame.GameField;
import mygame.tile.Road;
import mygame.tile.Spawner;
import mygame.tile.Target;

public class Enemy extends Entity {
    static private int dx[] = {0, 0, -1, 1};
    static private int dy[] = {-1, 1, 0, 0};

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

    public void move() {
        if (super.getX() % Config.TILE_SIZE == 0 && super.getY() % Config.TILE_SIZE == 0) {
            boolean broken = false;

            for (int i = 0; i < 4 && !broken; ++i) {
                if (dx[i] == -getVelocityX() && dy[i] == -getVelocityY())
                    continue;
                for (Entity e : super.getField().getEntities())
                    if (e instanceof Road) {
                        int topLeftX = super.getX() / Config.TILE_SIZE * Config.TILE_SIZE;
                        int topLeftY = super.getY() / Config.TILE_SIZE * Config.TILE_SIZE;
                        if (topLeftX + Config.TILE_SIZE * dx[i] == e.getX()
                                && topLeftY + Config.TILE_SIZE * dy[i] == e.getY()
                        ) {
                            super.setVelocityX(dx[i]);
                            super.setVelocityY(dy[i]);
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
            super.setX(super.getX() + super.getVelocityX());
            super.setY(super.getY() + super.getVelocityY());
        }
    }
}


