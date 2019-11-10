package mygame.tile.tower;

import mygame.Config;

public class NormalTower extends Tower {
    public NormalTower () {}
    public NormalTower (int x, int y) {
        super(x, y);
        setImage(Config.TOWER_NORMAL);
        setAttackSpeed(Config.TOWER_NORMAL_ATTACKSPEED);
        setAttackRange(Config.TOWER_NORMAL_ATTACKRANGE);
        setDamage(Config.TOWER_NORMAL_DAMAGE);
    }
}