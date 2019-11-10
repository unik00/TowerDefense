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
    public static final Image SPAWNER_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile109.png");
    public static final Image TARGET_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile110.png");

    public static final Image ENEMY_NORMAL = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile270.png");
    public static final Image ENEMY_NORMAL_SHADOW =
            new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile293.png");


    public static final Image TOWER_NORMAL_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile226.png");
    public static final double TOWER_NORMAL_ATTACKSPEED = 0.66;
    public static final int TOWER_NORMAL_ATTACKRANGE = 3 * TILE_SIZE;
    public static final int TOWER_NORMAL_DAMAGE = 1;

    public static final Image TOWER_MACHINE_GUN_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile250.png");
    public static final double TOWER_MACHINE_GUN_ATTACKSPEED = 2;
    public static final int TOWER_MACHINE_GUN_ATTACKRANGE = 3 * TILE_SIZE;
    public static final int TOWER_MACHINE_GUN_DAMAGE = 1;

    public static final Image TOWER_SNIPER_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile249.png");
    public static final double TOWER_SNIPER_ATTACKSPEED = 0.3;
    public static final int TOWER_SNIPER_ATTACKRANGE = 5 * TILE_SIZE;
    public static final int TOWER_SNIPER_DAMAGE = 1;

    public static final Image BULLET_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile272.png");
}

