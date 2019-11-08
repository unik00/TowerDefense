package mygame;

import javafx.scene.image.Image;

public class Config {
    public static final String GAME_NAME = "TowerDefense by Huy & Long";
    public static final int GAME_TPS = 20;                                      //ticks per second
    public static final int GAME_NSPT = (int) Math.round(1e9 / GAME_TPS);       //nanoseconds per tick

    public static final int TILE_SIZE = 64;
    public static final int TILE_HORIZONTAL = 10;
    public static final int TILE_VERTICAL = 7;

    public static final int SCREEN_WIDTH = TILE_SIZE * (TILE_HORIZONTAL + 5);
    public static final int SCREEN_HEIGHT = TILE_SIZE * TILE_VERTICAL;

    public static final Image ROAD_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile050.png");
    public static final Image MOUNTAIN_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile042.png");

    public static final Image ENENY_NORMAL = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile270.png");
}

