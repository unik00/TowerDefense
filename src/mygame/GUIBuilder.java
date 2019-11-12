package mygame;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import mygame.tile.Mountain;
import mygame.tile.tower.MachineGunTower;
import mygame.tile.tower.NormalTower;
import mygame.tile.tower.SniperTower;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GUIBuilder {
    private Group root;
    private Stage stage;
    private Player player;
    private GameField field;
    private List<Rectangle> targets = new ArrayList<Rectangle>();
    private List<ImageView> towerStorage = new ArrayList<ImageView>();
    private ImageView[] hearts = new ImageView[Config.maximumHearts];
    private Text rewardText = new Text(Config.BALANCE_HORIZONTAL_POSITION, Config.BALANCE_VERTICAL_POSITION, "BALANCE: 0$");

    public GUIBuilder(Stage stage, Group root, GameField field, Player player) {
        this.stage = stage;
        this.root = root;
        this.field = field;
        this.player = player;
    }

    public ImageView[] getHearts() {
        return hearts;
    }

    public void setHearts(ImageView[] hearts) {
        this.hearts = hearts;
    }

    public Text getRewardText() {
        return rewardText;
    }

    public void setRewardText(Text rewardText) {
        this.rewardText = rewardText;
    }

    public boolean sameImages(Image img1, Image img2) {
        BufferedImage bImage1 = SwingFXUtils.fromFXImage(img1, null);
        BufferedImage bImage2 = SwingFXUtils.fromFXImage(img2, null);
        for (int y = 0; y < img1.getHeight(); ++y)
            for (int x = 0; x < img1.getWidth(); ++x) {
                if (bImage1.getRGB(x, y) != bImage2.getRGB(x, y))
                    return false;
            }
        return true;
    }

    public void showTowerStorage() {
        Image normalTower = Config.TOWER_NORMAL_IMAGE;
        ImageView ivNormalTower = new ImageView(normalTower);
        ivNormalTower.setX((Config.TILE_HORIZONTAL+1) * Config.TILE_SIZE);
        ivNormalTower.setY(Config.TILE_SIZE);
        root.getChildren().add(ivNormalTower);
        towerStorage.add(ivNormalTower);

        Image sniperTower = Config.TOWER_SNIPER_IMAGE;
        ImageView ivSniperTower = new ImageView(sniperTower);
        ivSniperTower.setX((Config.TILE_HORIZONTAL+2) * Config.TILE_SIZE);
        ivSniperTower.setY(Config.TILE_SIZE);
        root.getChildren().add(ivSniperTower);
        towerStorage.add(ivSniperTower);

        Image machineGunTower = Config.TOWER_MACHINE_GUN_IMAGE;
        ImageView ivMachineGunTower = new ImageView(machineGunTower);
        ivMachineGunTower.setX((Config.TILE_HORIZONTAL+3) * Config.TILE_SIZE);
        ivMachineGunTower.setY(Config.TILE_SIZE);
        root.getChildren().add(ivMachineGunTower);
        towerStorage.add(ivMachineGunTower);
    }

    public void dragAndDrop() {
        for (ImageView iv : towerStorage) {
            iv.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dragboard db = iv.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(iv.getImage());
                    db.setContent(content);
                    event.consume();
                }
            });
        }

        //SET MOUNTAINS CAN BE PUT TOWER ON
        for (Entity e : field.getEntities())
            if (e instanceof Mountain) {
                Rectangle target = new Rectangle(e.getX(), e.getY(), Config.TILE_SIZE, Config.TILE_SIZE);
                target.setFill(Color.TRANSPARENT);
                targets.add(target);
                root.getChildren().add(target);
            }

        for (Rectangle target : targets) {
            target.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    if (event.getGestureSource() != target && event.getDragboard().hasImage())
                        event.acceptTransferModes(TransferMode.ANY);
                    event.consume();
                }
            });

            //DROP
            target.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        success = true;
                        //System.out.println(sameImages(db.getImage(), Config.TOWER_NORMAL_IMAGE));
                        if (sameImages(db.getImage(), Config.TOWER_NORMAL_IMAGE)) {
                            if (player.getReward() >= Config.TOWER_NORMAL_PRICE) {
                                field.getEntities().add(new NormalTower((int) target.getX(), (int) target.getY()));
                                player.setReward(player.getReward() - Config.TOWER_NORMAL_PRICE);
                            }
                        }
                        if (sameImages(db.getImage(), Config.TOWER_MACHINE_GUN_IMAGE)) {
                            if (player.getReward() >= Config.TOWER_MACHINE_GUN_PRICE) {
                                field.getEntities().add(new MachineGunTower((int) target.getX(), (int) target.getY()));
                                player.setReward(player.getReward() - Config.TOWER_MACHINE_GUN_PRICE);
                            }
                        }
                        if (sameImages(db.getImage(), Config.TOWER_SNIPER_IMAGE)) {
                            if (player.getReward() >= Config.TOWER_SNIPER_PRICE) {
                                field.getEntities().add(new SniperTower((int) target.getX(), (int) target.getY()));
                                player.setReward(player.getReward() - Config.TOWER_SNIPER_PRICE);
                            }
                        }
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
        }
    }

    public void mouseEnteredTowerStorage() {
        for (ImageView iv : towerStorage) {
            Popup popup = new Popup();

            if (sameImages(iv.getImage(), Config.TOWER_NORMAL_IMAGE)) {
                Text damageInfo = new Text(Config.TOWER_INFO_DAMAGE_X, Config.TOWER_INFO_DAMAGE_Y, "DAMAGE : " + String.valueOf(Config.TOWER_NORMAL_DAMAGE));
                Text attackspeedInfo = new Text(Config.TOWER_INFO_ATTACK_SPEED_X, Config.TOWER_INFO_ATTACK_SPEED_Y, "ATTACK SPEED : " + String.valueOf(Config.TOWER_NORMAL_ATTACK_SPEED));
                Text attackrangeInfo = new Text(Config.TOWER_INFO_ATTACK_RANGE_X, Config.TOWER_INFO_ATTACK_RANGE_Y,"ATTACK RANGE : " + String.valueOf(Config.TOWER_NORMAL_ATTACK_RANGE));
                Text priceInfo = new Text(Config.TOWER_INFO_PRICE_X, Config.TOWER_INFO_PRICE_Y,"PRICE : " + String.valueOf(Config.TOWER_NORMAL_PRICE) + "$");
                popup.getContent().addAll(damageInfo, attackrangeInfo, attackspeedInfo, priceInfo);
            }

            if (sameImages(iv.getImage(), Config.TOWER_MACHINE_GUN_IMAGE)) {
                Text damageInfo = new Text(Config.TOWER_INFO_DAMAGE_X, Config.TOWER_INFO_DAMAGE_Y, "DAMAGE : " + String.valueOf(Config.TOWER_MACHINE_GUN_DAMAGE));
                Text attackspeedInfo = new Text(Config.TOWER_INFO_ATTACK_SPEED_X, Config.TOWER_INFO_ATTACK_SPEED_Y, "ATTACK SPEED : " + String.valueOf(Config.TOWER_MACHINE_GUN_ATTACK_SPEED));
                Text attackrangeInfo = new Text(Config.TOWER_INFO_ATTACK_RANGE_X, Config.TOWER_INFO_ATTACK_RANGE_Y, "ATTACK RANGE : " + String.valueOf(Config.TOWER_MACHINE_GUN_ATTACK_RANGE));
                Text priceInfo = new Text(Config.TOWER_INFO_PRICE_X, Config.TOWER_INFO_PRICE_Y, "PRICE : " + String.valueOf(Config.TOWER_MACHINE_GUN_PRICE) + "$");
                popup.getContent().addAll(damageInfo, attackrangeInfo, attackspeedInfo, priceInfo);
            }

            if (sameImages(iv.getImage(), Config.TOWER_SNIPER_IMAGE)) {
                Text damageInfo = new Text(Config.TOWER_INFO_DAMAGE_X, Config.TOWER_INFO_DAMAGE_Y, "DAMAGE : " + String.valueOf(Config.TOWER_SNIPER_DAMAGE));
                Text attackspeedInfo = new Text(Config.TOWER_INFO_ATTACK_SPEED_X, Config.TOWER_INFO_ATTACK_SPEED_Y, "ATTACK SPEED : " + String.valueOf(Config.TOWER_SNIPER_ATTACK_SPEED));
                Text attackrangeInfo = new Text(Config.TOWER_INFO_ATTACK_RANGE_X, Config.TOWER_INFO_ATTACK_RANGE_Y, "ATTACK RANGE : " + String.valueOf(Config.TOWER_SNIPER_ATTACK_RANGE));
                Text priceInfo = new Text(Config.TOWER_INFO_PRICE_X, Config.TOWER_INFO_PRICE_Y, "PRICE : " + String.valueOf(Config.TOWER_SNIPER_PRICE) + "$");
                popup.getContent().addAll(damageInfo, attackrangeInfo, attackspeedInfo, priceInfo);
            }

            iv.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popup.show(stage);
                    popup.setX(stage.getX());
                    popup.setY(stage.getY() + 40);   //plus 40 because of the title
                    event.consume();
                }
            });
            iv.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popup.hide();
                    event.consume();
                }
            });
        }
    }

    public void showHearts() {
        for (int i = 0; i < Config.maximumHearts; ++i) {
            ImageView iv = new ImageView(Config.HEART_ALIVE_IMAGE);
            iv.setX(Config.HEART_HORIZONTAL_POSITION + i * Config.TILE_SIZE);
            iv.setY(Config.HEART_VERTICAL_POSITION);
            hearts[i] = iv;
            root.getChildren().add(hearts[i]);
        }
    }

    public void showBalance() {
        root.getChildren().add(rewardText);
    }
}
