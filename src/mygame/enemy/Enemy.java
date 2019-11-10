package mygame.enemy;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import mygame.Bullet;
import mygame.Config;
import mygame.Entity;
import mygame.GameField;
import mygame.tile.Road;
import javafx.scene.image.Image;
import mygame.tile.Spawner;
import mygame.tile.Target;

import java.awt.*;

public class Enemy extends Entity {
    static private int dx[] = {0, 0, -1, 1};
    static private int dy[] = {-1, 1, 0, 0};

    private int velocityX;
    private int velocityY;
    private Image shadow, straightShadow;

    public void setShadow(Image shadow) {
        this.shadow = shadow;
        this.straightShadow = shadow;
    }

    public Image getShadow() {
        return shadow;
    }

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

    private int maximumHitPoint;

    public void setMaximumHitPoint(int maximumHitPoint) {
        this.maximumHitPoint = maximumHitPoint;
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
                    hitPoint -= ((Bullet) e).getDamage();
                    if (hitPoint == 0){
                        super.setAlive(false);
                    }
                    e.setAlive(false);
                    assert hitPoint >= 0;
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
                            setDirection(dx[i], dy[i]);
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

    public void setDirection(int dx, int dy){
        super.setDirection(dx, dy);
        System.out.println("?");
        ImageView iv = new ImageView(straightShadow);
        Rotate rotation = new Rotate();
        rotation.setPivotX(0);
        rotation.setPivotY(0);
        if (dx == 0 && dy == 1) {
            rotation.setAngle(90);
        }
        else if (dx == 0 && dy == -1){
            rotation.setAngle(-90);
        }
        else if (dx == 1 && dy == 0){
            rotation.setAngle(0);
        }
        else if (dx == -1 && dy == 0){
            rotation.setAngle(-180);
        }
        iv.getTransforms().add(rotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.shadow = iv.snapshot(params, null);
    }

    private void drawHPBar(GraphicsContext gc){
        double barX = super.getX()+0.2*Config.TILE_SIZE;
        double barY = super.getY();
        double barW = Config.TILE_SIZE - 0.4*Config.TILE_SIZE;
        double barH = 4;
        gc.setFill(Color.GREY);
        gc.fillRect(barX, barY, barW, barH);
        gc.setFill(Color.ORANGERED);
        double hitPointPercentage = hitPoint*1.0 / maximumHitPoint;
        gc.fillRect(barX, barY, hitPointPercentage*barW, barH);

    }
    public void draw(GraphicsContext gc){
        // draw shadow
        gc.drawImage(shadow, super.getX()-Config.TILE_SIZE/3.0, super.getY()+Config.TILE_SIZE/4.0);

        // draw main
        super.draw(gc);

        // draw HP bar
        drawHPBar(gc);
    }
}


