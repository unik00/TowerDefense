package mygame.tile;

import mygame.Config;

public class Target extends Road {
    public Target(int x, int y) {
        super(x, y);
        setImage(Config.TARGET_IMAGE);
    }
}
