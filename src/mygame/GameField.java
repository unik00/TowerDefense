package mygame;

import mygame.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

//Field quan li tat ca cac Entity cua Stage
public class GameField {
    private List<Entity> entities = new ArrayList<Entity>();

    public List<Entity> getEntities() {
        return entities;
    }

    public GameField(GameStage stage) {
        this.entities = List.copyOf(stage.getEntities());
    }
}
