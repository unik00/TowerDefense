package mygame.tile;

import mygame.Config;

public class Road extends Tile {
    public Road() {}
    public Road(int x, int y) {
        super(x,y);
        setImage(Config.ROAD_IMAGE);
    }

}
