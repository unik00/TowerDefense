package mygame;

import javafx.scene.image.Image;

public class Config {
    public static final String GAME_NAME = "TowerDefense by Huy & Long";
    public static final int GAME_TPS = 20;                                      //ticks per second
    //public static final int GAME_NSPT = (int) Math.round(1e9 / GAME_TPS);       //nanoseconds per tick

    public static final int TILE_SIZE = 64;
    public static final int TILE_HORIZONTAL = 10;
    public static final int TILE_VERTICAL = 7;

    public static final int SCREEN_WIDTH = TILE_SIZE * (TILE_HORIZONTAL + 5);
    public static final int SCREEN_HEIGHT = TILE_SIZE * TILE_VERTICAL;

    //TILE
    public static final Image ROAD_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile050.png");
    public static final Image MOUNTAIN_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile042.png");
    public static final Image SPAWNER_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile109.png");
    public static final Image TARGET_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile110.png");

    //ENEMY
    public static final Image ENEMY_NORMAL = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile270.png");
    public static final Image ENEMY_NORMAL_SHADOW = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile293.png");

    //TOWER
    public static final Image TOWER_NORMAL_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile226.png");
    public static final double TOWER_NORMAL_ATTACK_SPEED = 0.66;
    public static final int TOWER_NORMAL_ATTACK_RANGE = 3 * TILE_SIZE;
    public static final int TOWER_NORMAL_DAMAGE = 1;
    public static final int TOWER_NORMAL_PRICE = 25;

    public static final Image TOWER_MACHINE_GUN_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile250.png");
    public static final double TOWER_MACHINE_GUN_ATTACK_SPEED = 2;
    public static final int TOWER_MACHINE_GUN_ATTACK_RANGE = 3 * TILE_SIZE;
    public static final int TOWER_MACHINE_GUN_DAMAGE = 1;
    public static final int TOWER_MACHINE_GUN_PRICE = 50;

    public static final Image TOWER_SNIPER_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile249.png");
    public static final double TOWER_SNIPER_ATTACK_SPEED = 0.3;
    public static final int TOWER_SNIPER_ATTACK_RANGE = 5 * TILE_SIZE;
    public static final int TOWER_SNIPER_DAMAGE = 1;
    public static final int TOWER_SNIPER_PRICE = 35;

    //BULLET
    public static final Image BULLET_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile272.png");

    //GUI -- TOWER INFO
    public static final int TOWER_INFO_DAMAGE_X = 10 * TILE_SIZE + 25;
    public static final int TOWER_INFO_DAMAGE_Y = 0 * TILE_SIZE + 15;
    public static final int TOWER_INFO_PRICE_X = 10 * TILE_SIZE + 25;
    public static final int TOWER_INFO_PRICE_Y = 0 * TILE_SIZE + 40;
    public static final int TOWER_INFO_ATTACK_SPEED_X = 12 * TILE_SIZE + 25;
    public static final int TOWER_INFO_ATTACK_SPEED_Y = 0 * TILE_SIZE + 15;
    public static final int TOWER_INFO_ATTACK_RANGE_X = 12 * TILE_SIZE + 25;
    public static final int TOWER_INFO_ATTACK_RANGE_Y = 0 * TILE_SIZE + 40;

    //HEART
    public static final Image HEART_ALIVE_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Retina/towerDefense_tile300.png");
    public static final Image HEART_DEAD_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Retina/towerDefense_tile301.png");
}

