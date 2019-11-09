package mygame;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mygame.enemy.Enemy;

public class Entity {
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;
    private Image image;

    private GameField field;

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Entity () {}
    public Entity (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
    public void update() {

    }
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Config.TILE_SIZE, Config.TILE_SIZE);
    }
    public boolean intersects(Entity e) {
        return e.getBoundary().intersects(this.getBoundary());
    }

    public int distance(Entity e) {
        return (x - e.getX()) * (x - e.getX()) + (y - e.getY()) * (y - e.getY());
    }
}
