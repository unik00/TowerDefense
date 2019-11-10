package mygame.tile.tower;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import mygame.Config;

public class SniperTower extends Tower {
    public SniperTower (int x, int y) {
        super(x, y);
        setImage(Config.TOWER_SNIPER_IMAGE);

        ImageView iv = new ImageView(super.getStraightImage());
        iv.setRotate(90);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        super.setImage(iv.snapshot(params, null));

        setDamage(Config.TOWER_SNIPER_DAMAGE);
        setAttackRange(Config.TOWER_SNIPER_ATTACKRANGE);
        setAttackSpeed(Config.TOWER_SNIPER_ATTACKSPEED);
    }
}
