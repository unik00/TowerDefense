package mygame.enemy;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import mygame.Config;
import mygame.GameField;

public class NormalEnemy extends Enemy {
    private Image shadow, straightShadow;

    public void setShadow(Image shadow) {
        this.shadow = shadow;
        this.straightShadow = shadow;
    }

    public Image getShadow() {
        return shadow;
    }
    public NormalEnemy() {}
    public NormalEnemy(int x, int y, GameField field) {
        super(x, y, field);
        super.setMaximumHitPoint(10);
        super.setHitPoint(10);
        super.setSpeed(2);
        setShadow(Config.ENEMY_NORMAL_SHADOW);
        setImage(Config.ENEMY_NORMAL);
        setReward(10);
    }

    public void setDirection(int dx, int dy){
        super.setDirection(dx, dy);
        //System.out.println("?");
        ImageView iv = new ImageView(straightShadow);
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
        this.shadow = iv.snapshot(params, null);
    }
    public void draw(GraphicsContext gc){
        // draw shadow
        gc.drawImage(shadow, super.getX()-Config.TILE_SIZE/3.0, super.getY()+Config.TILE_SIZE/2.0);

        // draw main
        super.draw(gc);
    }
}
