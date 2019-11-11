package mygame.enemy;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import mygame.Config;
import mygame.GameField;

public class TankerEnemy extends Enemy {
    private Image straightGunImage, gunImage;
    public TankerEnemy(int x, int y, GameField field) {
        super(x, y, field);
        super.setMaximumHitPoint(20);
        super.setHitPoint(20);
        setImage(Config.ENEMY_TANKER_BASE);
        this.gunImage = this.straightGunImage = Config.ENEMY_TANKER_GUN;

        setReward(20);

    }
    public void setDirection(int dx, int dy){
        super.setDirection(dx, dy);
        ImageView iv = new ImageView(straightGunImage);
        Rotate rotation = new Rotate();
        rotation.setPivotX(0);
        rotation.setPivotY(0);
        if (dx == 0 && dy == 1) {
            rotation.setAngle(90);
        }
        else if (dx == 0 && dy == -1){
            rotation.setAngle(-90);
        }
        else if (dx == 1 && dy == 0){
            rotation.setAngle(0);
        }
        else if (dx == -1 && dy == 0){
            rotation.setAngle(-180);
        }
        iv.getTransforms().add(rotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.gunImage = iv.snapshot(params, null);
    }
    public void draw(GraphicsContext gc){
        super.draw(gc);
        gc.drawImage(gunImage, getX(), getY());
    }
}
