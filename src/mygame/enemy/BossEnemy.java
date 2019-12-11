package mygame.enemy;

import mygame.Config;
import mygame.GameField;

public class BossEnemy extends NormalEnemy {
    public BossEnemy() {}
    public BossEnemy(int x, int y, GameField field) {
        super(x, y, field);
        super.setMaximumHitPoint(100);
        super.setHitPoint(100);
        super.setSpeed(1);
        setShadow(Config.ENEMY_BOSS_SHADOW);
        setImage(Config.ENEMY_BOSS);
        setReward(10);
    }
}
