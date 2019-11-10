package mygame;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public abstract class Entity {
    private int x;
    private int y;

    public double getCenterX(){
        return x + Config.TILE_SIZE / 2.0;
    }
    public double getCenterY(){
        return y + Config.TILE_SIZE / 2.0;
    }

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
        double width = image.getWidth(), height = image.getHeight();
        double dx = (width - Config.TILE_SIZE) / 2.0;
        double dy = (height - Config.TILE_SIZE) / 2.0;

        gc.drawImage(image, x - dx, y - dy);
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
        iv.getTransforms().add(rotation);//Add the Rotate to the ImageView
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = iv.snapshot(params, null);
    }
    public void setDirection(double degree){
        ImageView iv = new ImageView(this.straightImage);

        Rotate rotation = new Rotate();
        rotation.setPivotX(getX() + Config.TILE_SIZE/2.0);
        rotation.setPivotY(getY() + Config.TILE_SIZE/2.0);
        rotation.setAngle(degree);
        Translate translate = new Translate();

        iv.getTransforms().add(rotation);//Add the Rotate to the ImageView
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.image = iv.snapshot(params, null);
    }

    public int distance(Entity e) {
        return (int) Math.sqrt((x - e.getX()) * (x - e.getX()) + (y - e.getY()) * (y - e.getY()));
    }
}
