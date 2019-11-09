package mygame;

import javafx.scene.image.Image;

public class Bullet extends Entity {
    private int damage;
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
