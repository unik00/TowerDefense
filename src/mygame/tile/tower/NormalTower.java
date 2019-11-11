package mygame.tile.tower;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import mygame.Config;

public class NormalTower extends Tower {
    public NormalTower () {}
    public NormalTower (int x, int y) {
        super(x, y);
        setImage(Config.TOWER_NORMAL_IMAGE);
        setAttackSpeed(Config.TOWER_NORMAL_ATTACK_SPEED);
        setAttackRange(Config.TOWER_NORMAL_ATTACK_RANGE);
        setDamage(Config.TOWER_NORMAL_DAMAGE);
        setPrice(Config.TOWER_NORMAL_PRICE);

        ImageView iv = new ImageView(super.getStraightImage());
        iv.setRotate(90);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        super.setImage(iv.snapshot(params, null));
    }
}