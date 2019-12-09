package mygame;

public class Level {
    private int id;
    private int numberOfNormalEnemy;
    private int numberOfTankerEnemy;
    private int numberOfSmallerEnemy;
    private int numberOfBossEnemy;
    private int reward;

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

    public void loadNextLevel() {
        setId(getId() + 1);
        String dm = "src/stage/stage" + String.valueOf(getId()) + ".txt";
        Scanner scanner = new Scanner(new File(dm));
    }

    public boolean isFinished() {
        return numberOfBossEnemy == 0 && numberOfNormalEnemy == 0 && numberOfSmallerEnemy == 0 && numberOfTankerEnemy == 0;
    }
}
