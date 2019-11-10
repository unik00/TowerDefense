package mygame.enemy;

import mygame.Config;
import mygame.GameField;

public class NormalEnemy extends Enemy {
    public NormalEnemy() {}
    public NormalEnemy(int x, int y, GameField field) {
        super(x, y, field);
        super.setMaximumHitPoint(10);
        super.setHitPoint(10);
        super.setShadow(Config.ENEMY_NORMAL_SHADOW);
        setImage(Config.ENEMY_NORMAL);
    }
}
