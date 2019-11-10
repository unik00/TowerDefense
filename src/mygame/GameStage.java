package mygame;

import mygame.tile.Mountain;
import mygame.tile.Road;
import mygame.tile.Spawner;
import mygame.tile.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Stage dinh nghia trang thai bat dau cua 1 Field
public class GameStage {
    private List<Entity> entities = new ArrayList<Entity>();
    // fixed height = 7;
    // fixed width = 10;

    public List<Entity> getEntities() {
        return entities;
    }

    public GameStage(List<Entity> entities) {
        this.entities = entities;
    }

    public static GameStage load(String stageDir) throws FileNotFoundException {         //tai sao cho nay phai la static?
        List<Entity> entities;
        entities = new ArrayList<Entity>();
        Scanner scanner = new Scanner(new File(stageDir));
        for (int i = 0; i < Config.TILE_VERTICAL; ++i) {
            for (int j = 0; j < Config.TILE_HORIZONTAL; ++j) {
                int type = scanner.nextInt();
                if (type == 0) {
                    entities.add(new Road(j * Config.TILE_SIZE, i * Config.TILE_SIZE));
                }
                else if (type == 1){
                    entities.add(new Mountain(j * Config.TILE_SIZE, i * Config.TILE_SIZE));
                }
                else if (type == 2){
                    entities.add(new Spawner(j * Config.TILE_SIZE, i * Config.TILE_SIZE));
                }
                else if (type == 3){
                    entities.add(new Target(j * Config.TILE_SIZE, i * Config.TILE_SIZE));
                }
            }
        }
        return new GameStage(entities);
    }
}
