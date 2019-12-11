package mygame.enemy;

import javafx.scene.image.Image;
import mygame.Config;
import mygame.GameField;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy() {}
    public SmallerEnemy(int x, int y, GameField field) {
        super(x, y, field);
        super.setMaximumHitPoint(10);
        super.setHitPoint(10);
        super.setSpeed(4);
        setImage(Config.ENEMY_SMALLER);
        setReward(10);
    }
}
