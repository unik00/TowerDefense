package mygame.enemy;

import mygame.Config;
import mygame.GameField;

public class NormalEnemy extends Enemy {
    public NormalEnemy() {}
    public NormalEnemy(int x, int y, GameField field) {
        super(x, y, field);
        super.setMaximumHitPoint(20);
        super.setHitPoint(20);
        setImage(Config.ENEMY_NORMAL);
    }
}
