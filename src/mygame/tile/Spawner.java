package mygame.tile;

import mygame.Config;

public class Spawner extends Road {
    public Spawner(int x, int y) {
        super(x, y);
        setImage(Config.SPAWNER_IMAGE);
    }
}
