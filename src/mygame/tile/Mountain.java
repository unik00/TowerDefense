package mygame.tile;

import mygame.Config;

public class Mountain extends Tile {
    int putOnCounter = 0;

    public Mountain() {}

    public Mountain(int x, int y) {
        super(x,y);
        setImage(Config.MOUNTAIN_IMAGE);
    }

    public int getPutOnCounter() {
        return putOnCounter;
    }

    public void increasePutOnCounter() {
        this.putOnCounter += 1;
    }
}
