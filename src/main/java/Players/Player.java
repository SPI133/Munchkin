package Players;

import Deck.*;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Player {//TODO:code

    private int superMunchkin;
    private int halfBreed;
    private int level;
    private String name;
    private Gender gender;
    private int bigItems;
    private boolean isDwarf;
    private int cheatCards;
    private List<Card> playerHand;
    private PlayerEquipment equipment;
    private List<Card> extraEquipment;
    private List<Card> races;
    private List<Card> classes;
    private List<Card> curses;

    public Player(final String name, final Gender gender) {
        this.superMunchkin = 0;
        this.halfBreed = 0;
        this.level = 1;
        this.name = name;
        this.gender = gender;
        this.bigItems = 0;
        this.isDwarf = false;
        this.cheatCards = 0;
        this.playerHand = new ArrayList<>();
        this.equipment = new PlayerEquipment();
        this.extraEquipment = new ArrayList<>();
        this.races = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.curses = new ArrayList<>();
    }

    public int getSuperMunchkin() {
        return superMunchkin;
    }

    public void setSuperMunchkin(final int superMunchkin) {
        this.superMunchkin = superMunchkin;
    }

    public void incrSuperMunchkin() {
        this.superMunchkin++;
    }

    //public void decreaseSuperMunchkin(){}

    public int getHalfBreed() {
        return halfBreed;
    }

    public void setHalfBreed(final int halfBreed) {
        this.halfBreed = halfBreed;
    }

    public void incrHalfBreed() {
        this.halfBreed++;
    }

    public int getLevel() {
        return level;
    }

    public void alterLevel(final int additive){
        level+=additive;
        level = Math.max(level,1);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public int getBigItems() {
        return bigItems;
    }

    public void setBigItems(final int bigItems) {
        this.bigItems = bigItems;
    }

    public boolean isDwarf() {
        return isDwarf;
    }

    public void setDwarf(final boolean dwarf) {
        isDwarf = dwarf;
    }

    public int getCheatCards() {
        return cheatCards;
    }


    public void setCheatCards(final int cheatCards) {
        this.cheatCards = cheatCards;
    }

    public void incrCheatCards() {
        this.cheatCards++;
    }

    public PlayerEquipment getEquipment() {
        return equipment == null ? null : new PlayerEquipment(equipment);
    }

    public void setEquipment(PlayerEquipment equipment) {
        this.equipment = equipment;
    }

    public List<Card> getExtraEquipment() {
        return extraEquipment;
    }

    public void setExtraEquipment(final List<Card> extraEquipment) {
        this.extraEquipment = extraEquipment;
    }

    public List<Card> getRaces() {
        return races;
    }

    public void setRaces(final List<Card> races) {
        this.races = races;
    }

    public List<Card> getClasses() {
        return classes;
    }

    public void setClasses(final List<Card> classes) {
        this.classes = classes;
    }

    public List<Card> getCurses() {
        return curses;
    }

    public void setCurses(final List<Card> curses) {
        this.curses = curses;
    }

    public boolean checkEquipment(final ItemCard item) {
        final boolean bigItemRules = item.isBig() && bigItems > 0 && !isDwarf;

        if (bigItemRules) {
            return false;
        }

        if (equipment.isEmpty()) {
            return true;
        } else {
            switch (item.getType()) {
                case HEADGEAR:
                    return equipment.noHeadgear();
                case ARMOR:
                    return equipment.noArmor();
                case HAND:
                    return equipment.hasEnoughHands(1);
                case HANDS_2:
                    return equipment.hasEnoughHands(2);
                case HANDS_3:
                    return equipment.hasEnoughHands(3);
                case FOOTGEAR:
                    return equipment.noFootgear();
            }
        }
        return true;
    }

    public void addEquipment(@NotNull final ItemCard item) {
        switch (item.getType()) {
            case HEADGEAR:
                equipment.setHeadgear(new ItemCard(item));
                break;
            case ARMOR:
                equipment.setArmor(item);
                break;
            case HAND:
                equipment.alterAmountOfHands(-1);
                equipment.addHandEquipment(item);
                break;
            case HANDS_2:
                equipment.alterAmountOfHands(-2);
                equipment.addHandEquipment(item);
                break;
            case HANDS_3:
                equipment.alterAmountOfHands(-3);
                equipment.addHandEquipment(item);
                break;
            case FOOTGEAR:
                equipment.setFootgear(item);
        }
    }

    public void addToHand(final Card card) {


    }

    public boolean addRace(final RaceCard race) {
        if (halfBreed < races.size()) {
            return false;
        }
        if (race.getRace() == RaceCard.Race.DWARF) {
            isDwarf = true;
        }
        races.add(race);
        return true;
    }

    public boolean addClass(final ClassCard theClass) {
        if (superMunchkin < classes.size()) {
            return false;
        }
        for (final Card card : classes) {
            if (card instanceof ClassCard) {
                final ClassCard classCard = (ClassCard) card;
                if (classCard.getTheClass() == theClass.getTheClass()) {
                    return false;
                }
            }
        }
        classes.add(theClass);
        return true;
    }

    public void addCurse(final CurseCard curse) {
        curses.add(curse);
    }

    public void removeEquipment(final ItemCard item) {
        switch (item.getType()) {
            case HEADGEAR:
                equipment.removeHeadgear();
                break;
            case ARMOR:
                equipment.removeArmor();
                break;
            case HAND:
                equipment.alterAmountOfHands(1);
                equipment.removeHandEquipment(item);
                break;
            case HANDS_2:
                equipment.alterAmountOfHands(2);
                equipment.removeHandEquipment(item);
                break;
            case HANDS_3:
                equipment.alterAmountOfHands(3);
                equipment.removeHandEquipment(item);
                break;
            case FOOTGEAR:
                equipment.removeFootgear();
        }
    }

    public boolean removeRace(final RaceCard.Race race) {
        for (Card playerCard : races) {
            RaceCard playerClass = (RaceCard) playerCard;
            if (playerClass.getRace() == race) {
                if (race == RaceCard.Race.DWARF) {
                    isDwarf = false;
                }
                return races.remove(playerCard);
            }
        }
        return false;
    }

    public boolean removeClass(final ClassCard.Class theClass) {
        for (Card playerCard : classes) {
            ClassCard playerClass = (ClassCard) playerCard;
            if (playerClass.getTheClass() == theClass) {
                return classes.remove(playerCard);
            }
        }
        return false;
    }

    public void removeCurse(final CurseCard curse) {
        curses.remove(curse);
    }

    public void arrangeCards() {
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}
