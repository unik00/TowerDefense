package mygame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import mygame.tile.Mountain;
import mygame.tile.tower.NormalTower;
import mygame.tile.tower.Tower;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    List<Rectangle> targets = new ArrayList<Rectangle>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle(Config.GAME_NAME);
        Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //click san sang thi se bat dau man choi moi

        Image normalTower = Config.TOWER_NORMAL;
        ImageView ivNormalTower = new ImageView(normalTower);
        ivNormalTower.setX(11 * Config.TILE_SIZE);
        ivNormalTower.setY(1 * Config.TILE_SIZE);
        //gc.drawImage(normalTower, ivNormalTower.getX(), ivNormalTower.getY());
        root.getChildren().add(ivNormalTower);

        GameController controller  = new GameController(gc);

        //DRAG && DROP
        //click
        ivNormalTower.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = ivNormalTower.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(ivNormalTower.getImage());
                db.setContent(content);
                event.consume();
            }
        });

        //set available targets

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

            //drop
            target.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean sucess = false;
                    if (db.hasImage()) {
                        sucess = true;
                        controller.getField().getEntities().add(new NormalTower((int) target.getX(), (int) target.getY()));
                    }
                    event.setDropCompleted(sucess);
                    event.consume();
                }
            });
        }

        controller.start();
    }
}
