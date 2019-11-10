package mygame;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public abstract class Entity {
    private int x;
    private int y;
    private boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private Image image, straightImage;
    private GameField field;

    public GameField getField() {
        return field;
    }

    public void setField(GameField field) {
        this.field = field;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.straightImage = image;
        this.image = image;
    }

    public Image getStraightImage() {
        return straightImage;
    }

    public Entity () {
    }
    public Entity (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Entity (int x, int y, GameField field) {
        this.x = x;
        this.y = y;
        this.field = field;
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
    public void setDirection(int dx, int dy){
        ImageView iv = new ImageView(this.straightImage);
        if (dx == 0 && dy == 1) {
            iv.setRotate(90);
        }
        else if (dx == 0 && dy == -1){
            iv.setRotate(-90);
        }
        else if (dx == 1 && dy == 0){
            iv.setRotate(0);
        }
        else if (dx == -1 && dy == 0){
            iv.setRotate(-180);

        }
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = iv.snapshot(params, null);
    }
    public void setDirection(double degree){
        ImageView iv = new ImageView(this.straightImage);
        iv.setRotate(degree);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = iv.snapshot(params, null);
    }

    public int distance(Entity e) {
        return (int) Math.sqrt((x - e.getX()) * (x - e.getX()) + (y - e.getY()) * (y - e.getY()));
    }
}
