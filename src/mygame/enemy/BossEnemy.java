package mygame.enemy;

import mygame.Config;
import mygame.GameField;

public class BossEnemy extends NormalEnemy {
    public BossEnemy() {}
    public BossEnemy(int x, int y, GameField field) {
        super(x, y, field);
        super.setMaximumHitPoint(50);
        super.setHitPoint(50);
        super.setSpeed(2);
        setShadow(Config.ENEMY_BOSS_SHADOW);
        setImage(Config.ENEMY_BOSS);
        setReward(10);
    }
}
