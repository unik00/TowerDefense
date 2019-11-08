package mygame.enemy;

import mygame.Config;
import mygame.GameField;

public class NormalEnemy extends Enemy {
    public NormalEnemy() {}
    public NormalEnemy(int x, int y, GameField field) {
        super(x, y, field);
        setImage(Config.ENEMY_NORMAL);
    }
}
