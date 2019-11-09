package mygame.tile.tower;
import javafx.scene.image.Image;
import mygame.tile.Tile;

public class Tower extends Tile {
    private int attackSpeed;
    private int attackRange;
    private int damage;
    private Image gun;

    public Tower() {}
    public Tower(int x, int y) { super(x, y); }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
