package mygame.tile;

import mygame.Config;

public class Mountain extends Tile {
    public Mountain() {}
    public Mountain(int x, int y) {
        super(x,y);
        setImage(Config.MOUNTAIN_IMAGE);
    }

}
