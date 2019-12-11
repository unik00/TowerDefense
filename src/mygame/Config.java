package mygame;

import javafx.scene.image.Image;

public class Config {
    public static final String GAME_NAME = "TowerDefense by Huy & Long";

    public static final int TILE_SIZE = 64;
    public static final int TILE_HORIZONTAL = 11;
    public static final int TILE_VERTICAL = 10;

    public static final int SCREEN_WIDTH = TILE_SIZE * (TILE_HORIZONTAL + 5);
    public static final int SCREEN_HEIGHT = TILE_SIZE * TILE_VERTICAL;

    //TILE
    public static final Image ROAD_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile172.png");
    public static final Image MOUNTAIN_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile231.png");
    public static final Image SPAWNER_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile172.png");
    public static final Image TARGET_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile059.png");
    public static final Image TOWER_BASE_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
    public static final Image ENEMY_NORMAL = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile270.png");
    public static final Image ENEMY_NORMAL_SHADOW = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile293.png");
    public static final Image ENEMY_BOSS = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile271.png");
    public static final Image ENEMY_BOSS_SHADOW = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile294.png");

    public static final int RANDOM_ENTITIES_NUM = 30;
    public static final Image BUSH_IMAGE_1 = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile131.png");
    public static final Image BUSH_IMAGE_2 = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile132.png");
    public static final Image STONE_IMAGE_1 = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile135.png");
    public static final Image STONE_IMAGE_2 = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile136.png");
    public static final Image DUST_IMAGE_1 = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile019.png");
    public static final Image DUST_IMAGE_2 = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile020.png");

    public static final Image ENEMY_TANKER_GUN = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile292.png");
    public static final Image ENEMY_TANKER_BASE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile268.png");

    public static final Image ENEMY_SMALLER = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile245.png");

    public static final Image TOWER_NORMAL_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile226.png");
    public static final double TOWER_NORMAL_ATTACK_SPEED = 0.66;
    public static final int TOWER_NORMAL_ATTACK_RANGE = 3 * TILE_SIZE;
    public static final int TOWER_NORMAL_DAMAGE = 2;
    public static final int TOWER_NORMAL_PRICE = 25;

    public static final Image TOWER_MACHINE_GUN_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile250.png");
    public static final double TOWER_MACHINE_GUN_ATTACK_SPEED = 2;
    public static final int TOWER_MACHINE_GUN_ATTACK_RANGE = 2 * TILE_SIZE;
    public static final int TOWER_MACHINE_GUN_DAMAGE = 1;
    public static final int TOWER_MACHINE_GUN_PRICE = 50;

    public static final Image TOWER_SNIPER_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile249.png");
    public static final double TOWER_SNIPER_ATTACK_SPEED = 0.15;
    public static final int TOWER_SNIPER_ATTACK_RANGE = 5 * TILE_SIZE;
    public static final int TOWER_SNIPER_DAMAGE = 5;
    public static final int TOWER_SNIPER_PRICE = 35;

    //BULLET
    public static final Image BULLET_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile272.png");

    //GUI -- TOWER INFO
    public static final int TOWER_INFO_DAMAGE_X = TILE_HORIZONTAL * TILE_SIZE + 25;
    public static final int TOWER_INFO_DAMAGE_Y = 0 * TILE_SIZE + 15;
    public static final int TOWER_INFO_PRICE_X = TILE_HORIZONTAL * TILE_SIZE + 25;
    public static final int TOWER_INFO_PRICE_Y = 0 * TILE_SIZE + 40;
    public static final int TOWER_INFO_ATTACK_SPEED_X = (TILE_HORIZONTAL + 2) * TILE_SIZE + 25;
    public static final int TOWER_INFO_ATTACK_SPEED_Y = 0 * TILE_SIZE + 15;
    public static final int TOWER_INFO_ATTACK_RANGE_X = (TILE_HORIZONTAL + 2) * TILE_SIZE + 25;
    public static final int TOWER_INFO_ATTACK_RANGE_Y = 0 * TILE_SIZE + 40;

    //HEART
    public static final Image HEART_ALIVE_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Retina/towerDefense_tile300.png");
    public static final Image HEART_DEAD_IMAGE = new Image("file:src/resources/AssetsKit_2/PNG/Retina/towerDefense_tile301.png");
    public static final int HEART_VERTICAL_POSITION = 4 * TILE_SIZE + 16;
    public static final int HEART_HORIZONTAL_POSITION = TILE_HORIZONTAL * TILE_SIZE + 16;

    //BALANCE
    public static final int BALANCE_HORIZONTAL_POSITION = (TILE_HORIZONTAL + 1) * TILE_SIZE + 32;
    public static final int BALANCE_VERTICAL_POSITION = 3 * TILE_SIZE;

    //LEVEL
    public static final int LEVEL_HORIZONTAL_POSITION = (TILE_HORIZONTAL + 1) * TILE_SIZE + 48;
    public static final int LEVEL_VERTICAL_POSITION = 6 * TILE_SIZE;
    public static final int maximumLevels = 4;

    //PLAYER
    public static final int maximumHearts = 5;
    public static final int initialBalance = 100;

    //BUTTON
    public static final int START_BUTTON_LAYOUT_X = (TILE_HORIZONTAL + 2) * TILE_SIZE;
    public static final int START_BUTTON_LAYOUT_Y = (TILE_VERTICAL - 1) * TILE_SIZE;

    //ELAPSED TIME BETWEEN ENEMY IN EACH LEVEL
    public static final long elapsedTimeBetweenEnemy[] = {(long)4e9, (long)3e9, (long)2e9, (long)15e8, (long)1e9, (long)5e8};
}

