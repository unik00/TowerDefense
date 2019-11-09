package mygame.tile.tower;

import mygame.Config;

public class NormalTower extends Tower {
    public NormalTower () {}
    public NormalTower (int x, int y) {
        super(x, y);
        setImage(Config.TOWER_NORMAL);
    }
}
