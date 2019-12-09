package mygame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
    private int id;
    private int numberOfNormalEnemy;
    private int numberOfTankerEnemy;
    private int numberOfSmallerEnemy;
    private int numberOfBossEnemy;
    private int reward;
    private boolean finished = false;

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfNormalEnemy() {
        return numberOfNormalEnemy;
    }

    public void setNumberOfNormalEnemy(int numberOfNormalEnemy) {
        this.numberOfNormalEnemy = numberOfNormalEnemy;
    }

    public int getNumberOfTankerEnemy() {
        return numberOfTankerEnemy;
    }

    public void setNumberOfTankerEnemy(int numberOfTankerEnemy) {
        this.numberOfTankerEnemy = numberOfTankerEnemy;
    }

    public int getNumberOfSmallerEnemy() {
        return numberOfSmallerEnemy;
    }

    public void setNumberOfSmallerEnemy(int numberOfSmallerEnemy) {
        this.numberOfSmallerEnemy = numberOfSmallerEnemy;
    }

    public int getNumberOfBossEnemy() {
        return numberOfBossEnemy;
    }

    public void setNumberOfBossEnemy(int numberOfBossEnemy) {
        this.numberOfBossEnemy = numberOfBossEnemy;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void loadNextLevel() throws FileNotFoundException {
        setId(getId() + 1);
        finished = false;
        Scanner scanner = new Scanner(new File("src/stage/stage" + String.valueOf(getId()) + ".txt"));
        numberOfNormalEnemy = scanner.nextInt();
        numberOfTankerEnemy = scanner.nextInt();
        numberOfSmallerEnemy = scanner.nextInt();
        numberOfBossEnemy = scanner.nextInt();
    }

    public boolean isFinished() {
        return numberOfBossEnemy == 0 && numberOfNormalEnemy == 0 && numberOfSmallerEnemy == 0 && numberOfTankerEnemy == 0;
    }
}
