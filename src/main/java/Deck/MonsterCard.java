package Deck;

import java.util.List;

public class MonsterCard extends RoomCard {//TODO:code

    private int level;
    private int rewardLevels;
    private int rewardTreasures;
    //private List<Effect> effects;
    private List<BadStaff> badStaff;


    public MonsterCard(final int level,
                       final int rewardLevels,
                       final int rewardTreasures) {
        this.level = level;
        this.rewardLevels = rewardLevels;
        this.rewardTreasures = rewardTreasures;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRewardLevels() {
        return rewardLevels;
    }

    public void setRewardLevels(int rewardLevels) {
        this.rewardLevels = rewardLevels;
    }

    public int getRewardTreasures() {
        return rewardTreasures;
    }

    public void setRewardTreasures(int rewardTreasures) {
        this.rewardTreasures = rewardTreasures;
    }

    private enum BadStaff{
        LOSE_A_LEVEL,
        DEATH
    }
}
