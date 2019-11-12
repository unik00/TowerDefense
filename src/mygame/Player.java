package mygame;

import mygame.Config;

public class Player {
    private int reward = Config.initialBalance;
    private int remainingHearts = Config.maximumHearts;
    public Player () {}

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getRemainingHearts() {
        return remainingHearts;
    }

    public void setRemainingHearts(int remainingHearts) {
        this.remainingHearts = remainingHearts;
    }
}
