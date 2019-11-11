package mygame.tile.tower;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import mygame.Config;

public class SniperTower extends Tower {
    public SniperTower (int x, int y) {
        super(x, y);
        setImage(Config.TOWER_SNIPER_IMAGE);
        setDamage(Config.TOWER_SNIPER_DAMAGE);
        setAttackRange(Config.TOWER_SNIPER_ATTACK_RANGE);
        setAttackSpeed(Config.TOWER_SNIPER_ATTACK_SPEED);
        setPrice(Config.TOWER_SNIPER_PRICE);

        ImageView iv = new ImageView(super.getStraightImage());
        iv.setRotate(90);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        super.setImage(iv.snapshot(params, null));


    }
}
