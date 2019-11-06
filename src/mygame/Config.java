package mygame;

public class Config {
    public static final String GAME_NAME = "TowerDefense by Huy & Long";
    public static final long GAME_TPS = 20;                                      //ticks per second
    public static final long GAME_NSPT = Math.round(1e9 / GAME_TPS);       //nanoseconds per tick

    public static final long SCREEN_WIDTH = 64 * 10;
    public static final long SCREEN_HEIGHT = 64 * 7;
}

