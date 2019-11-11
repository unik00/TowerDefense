package mygame;

import javafx.application.Application;
import javafx.collections.transformation.TransformationList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import mygame.tile.Mountain;
import mygame.tile.tower.MachineGunTower;
import mygame.tile.tower.NormalTower;
import mygame.tile.tower.SniperTower;
import mygame.tile.tower.Tower;

import javax.management.StringValueExp;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    List<Rectangle> targets = new ArrayList<Rectangle>();
    List<ImageView> towerStorage = new ArrayList<ImageView>();

    public static void main(String[] args) {
        launch(args);
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

    public void createTowerStorage(Group root) {
        Image normalTower = Config.TOWER_NORMAL_IMAGE;
        ImageView ivNormalTower = new ImageView(normalTower);
        ivNormalTower.setX(11 * Config.TILE_SIZE);
        ivNormalTower.setY(1 * Config.TILE_SIZE);
        root.getChildren().add(ivNormalTower);
        towerStorage.add(ivNormalTower);

        Image machineGunTower = Config.TOWER_MACHINE_GUN_IMAGE;
        ImageView ivMachineGunTower = new ImageView(machineGunTower);
        ivMachineGunTower.setX(13 * Config.TILE_SIZE);
        ivMachineGunTower.setY(1 * Config.TILE_SIZE);
        root.getChildren().add(ivMachineGunTower);
        towerStorage.add(ivMachineGunTower);

        Image sniperTower = Config.TOWER_SNIPER_IMAGE;
        ImageView ivSniperTower = new ImageView(sniperTower);
        ivSniperTower.setX(12 * Config.TILE_SIZE);
        ivSniperTower.setY(1 * Config.TILE_SIZE);
        root.getChildren().add(ivSniperTower);
        towerStorage.add(ivSniperTower);
    }

    public void dragAndDrop(Group root, GameController controller) {
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
        for (Entity e : controller.getField().getEntities())
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
                    boolean sucess = false;
                    if (db.hasImage()) {
                        sucess = true;
                        //System.out.println(sameImages(db.getImage(), Config.TOWER_NORMAL_IMAGE));
                        if (sameImages(db.getImage(), Config.TOWER_NORMAL_IMAGE)) {
                            if (controller.getReward() >= Config.TOWER_NORMAL_PRICE) {
                                controller.getField().getEntities().add(new NormalTower((int) target.getX(), (int) target.getY()));
                                controller.setReward(controller.getReward() - Config.TOWER_NORMAL_PRICE);
                            }
                        }
                        if (sameImages(db.getImage(), Config.TOWER_MACHINE_GUN_IMAGE)) {
                            if (controller.getReward() >= Config.TOWER_MACHINE_GUN_PRICE) {
                                controller.getField().getEntities().add(new MachineGunTower((int) target.getX(), (int) target.getY()));
                                controller.setReward(controller.getReward() - Config.TOWER_MACHINE_GUN_PRICE);
                            }
                        }
                        if (sameImages(db.getImage(), Config.TOWER_SNIPER_IMAGE)) {
                            if (controller.getReward() >= Config.TOWER_SNIPER_PRICE) {
                                controller.getField().getEntities().add(new SniperTower((int) target.getX(), (int) target.getY()));
                                controller.setReward(controller.getReward() - Config.TOWER_SNIPER_PRICE);
                            }
                        }
                    }
                    event.setDropCompleted(sucess);
                    event.consume();
                }
            });
        }
    }

    public void mouseEnteredTowerStorage(Stage stage) {
        for (ImageView iv : towerStorage) {
            Popup popup = new Popup(); //popup.setX(9 * Config.TILE_SIZE + 40); popup.setY(20);

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

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle(Config.GAME_NAME);
        Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().addAll(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        //click san sang thi se bat dau man choi moi

        GameController controller  = new GameController(gc, root);
        createTowerStorage(root);
        dragAndDrop(root, controller);
        mouseEnteredTowerStorage(primaryStage);

        controller.start();
    }
}
