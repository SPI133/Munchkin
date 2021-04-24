package Players;

import Deck.ItemCard;

import java.util.ArrayList;
import java.util.List;

class PlayerEquipment {

    private ItemCard headgear;
    private ItemCard armor;
    private List<ItemCard> hands;
    private ItemCard footgear;
    private int amountOfHands;

    PlayerEquipment() {
        this.headgear = null;
        this.armor = null;
        this.amountOfHands = 2;
        this.hands = new ArrayList<>(amountOfHands);
        this.footgear = null;
    }

    PlayerEquipment(final PlayerEquipment equipment) {
        if (equipment != null) {
            this.headgear = new ItemCard(equipment.headgear);
            this.armor = new ItemCard(equipment.armor);
            this.amountOfHands = equipment.amountOfHands;
            this.hands = new ArrayList<>(equipment.amountOfHands);
            for (final ItemCard handEquipment : equipment.hands) {
                this.hands.add(new ItemCard(handEquipment));
            }
            this.footgear = new ItemCard(equipment.footgear);
        }
    }

    ItemCard getHeadgear() {
        return headgear == null ? null : new ItemCard(headgear);
    }

    void setHeadgear(final ItemCard headgear) {
        if (headgear != null) {
            this.headgear = new ItemCard(headgear);
        }
    }

    ItemCard getArmor() {
        return armor == null ? null : new ItemCard(armor);
    }

    void setArmor(final ItemCard armor) {
        if (armor != null) {
            this.armor = new ItemCard(armor);
        }
    }

    List<ItemCard> getHands() {
        final ArrayList<ItemCard> newHands = new ArrayList<>(amountOfHands);
        for (ItemCard handEquipment : hands) {
            final ItemCard item = new ItemCard(handEquipment);
            newHands.add(item);
        }
        return newHands;
    }

    ItemCard getFootgear() {
        return footgear == null ? null : new ItemCard(footgear);
    }

    void setFootgear(final ItemCard footgear) {
        if (footgear != null) {
            this.footgear = new ItemCard(footgear);
        }
    }

    int getAmountOfHands() {
        return amountOfHands;
    }

    void alterAmountOfHands(final int additive) {
        amountOfHands += additive;
        amountOfHands = Math.max(0, amountOfHands);
    }

    void addHandEquipment(final ItemCard hand) {
        switch (hand.getType()) {
            case HAND:
                alterAmountOfHands(-1);
                break;
            case HANDS_2:
                alterAmountOfHands(-2);
                break;
            case HANDS_3:
                alterAmountOfHands(-3);
                break;
        }
        hands.add(new ItemCard(hand));
    }

    void removeHeadgear() {
        headgear = null;
    }

    void removeArmor() {
        armor = null;
    }

    void removeHandEquipment(final ItemCard item) {
        if (item != null) {
            for (final ItemCard handEquipment : hands) {
                if (handEquipment.equals(item)) {
                    hands.remove(handEquipment);
                    break;
                }
            }
            switch (item.getType()) {
                case HAND:
                    amountOfHands++;
                    break;
                case HANDS_2:
                    amountOfHands += 2;
                    break;
                case HANDS_3:
                    amountOfHands += 3;
                    break;
            }
        }
    }

    void removeFootgear() {
        footgear = null;
    }

    boolean noHeadgear() {
        return headgear == null;
    }

    boolean noArmor() {
        return armor == null;
    }

    boolean hasEnoughHands(final int handsRequired) {
        return amountOfHands - handsRequired >= 0;
    }

    boolean noFootgear() {
        return footgear == null;
    }

    boolean isEmpty() {
        return noHeadgear() && noArmor() && hands.isEmpty() && noFootgear();
    }

}
