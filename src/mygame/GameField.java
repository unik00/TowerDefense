package mygame;

import mygame.tile.Spawner;
import mygame.tile.Target;
import java.util.List;

//Field quan li tat ca cac Entity cua Stage
public class GameField {
    private int spawnerX, spawnerY, targetX, targetY;
    private List<Entity> entities;

    public int getSpawnerX() {
        return spawnerX;
    }

    public void setSpawnerX(int spawnerX) {
        this.spawnerX = spawnerX;
    }

    public int getSpawnerY() {
        return spawnerY;
    }

    public void setSpawnerY(int spawnerY) {
        this.spawnerY = spawnerY;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }


    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity e){
        this.entities.add(e);
    }

    public GameField(GameStage stage) {
        this.entities = stage.getEntities();

        for(Entity E : this.entities){
            E.setField(this);
            if (E instanceof Spawner){
                System.out.println("FOUND SPAWNER!");
                this.setSpawnerX(E.getX());
                this.setSpawnerY(E.getY());
            }
            if (E instanceof Target){
                System.out.println("FOUND TARGET!");
                this.setTargetX(E.getX());
                this.setTargetY(E.getY());
            }
        }
    }
}
