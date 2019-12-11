package mygame.tile;

import mygame.Config;
import mygame.Entity;
import mygame.GameField;

public class RandomEntity extends Entity {

    public RandomEntity(GameField field){
        double u = Math.random();
        double v = Math.random();
        u *= Config.TILE_SIZE * Config.TILE_VERTICAL;
        v *= Config.TILE_SIZE * Config.TILE_HORIZONTAL;
        super.setX((int)u);
        super.setY((int)v);

        int randomized = (int)(Math.random() * 7);
        if (randomized == 0){
            setImage(Config.BUSH_IMAGE_1);
        }
        else if (randomized == 1){
            setImage(Config.BUSH_IMAGE_2);
        }
        else if (randomized == 2){
            setImage(Config.STONE_IMAGE_1);
        }
        else if (randomized == 3){
            setImage(Config.DUST_IMAGE_1);
        }
        else if (randomized == 4){
            setImage(Config.DUST_IMAGE_2);
        }
        else {
            setImage(Config.BUSH_IMAGE_1);
        }
    }

}
