package mygame.enemy;

import mygame.Config;

public class NormalEnemy extends Enemy {
    public NormalEnemy() {}
    public NormalEnemy(int x, int y) {
        super(x, y);
        setImage(Config.ENEMY_NORMAL);
    }
}
