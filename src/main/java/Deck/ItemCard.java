package Deck;

public class ItemCard extends TreasureCard {

    private Type type;
    private boolean isBig;

    public ItemCard(final Type type, final boolean isBig) {
        this.type = type;
        this.isBig = isBig;
    }

    public ItemCard(final ItemCard item) {
        if (item != null) {
            this.type = item.type;
            this.isBig = item.isBig;
            setId(item.getId());
        } else {
            this.type = Type.UNKNOWN;
            this.isBig = false;
            setId("");
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public boolean isBig() {
        return isBig;
    }

    public void setBig(final boolean big) {
        isBig = big;
    }

    public boolean sameType(final ItemCard item) {
        if (type == Type.HAND) {
            if (item.getType() == Type.HANDS_2) {
                return true;
            }
            if (item.getType() == Type.HANDS_3) {
                return true;
            }
        }

        if (type == Type.HANDS_2) {
            if (item.getType() == Type.HAND) {
                return true;
            }
            if (item.getType() == Type.HANDS_3) {
                return true;
            }
        }

        if (type == Type.HANDS_3) {
            if (item.getType() == Type.HAND) {
                return true;
            }
            if (item.getType() == Type.HANDS_2) {
                return true;
            }
        }
        return this.type == item.getType();
    }

    /**
     * HAND: item requires one hand
     * HANDS_2: item requires two hands
     * HANDS_3: item requires three hands
     * HEADGEAR: item is put on head
     * FOOTGEAR: item is worn on feet
     * ARMOR: item is worn on chest
     */
    public enum Type {
        UNKNOWN,
        HAND,
        HANDS_2,
        HANDS_3,
        HEADGEAR,
        FOOTGEAR,
        ARMOR
    }
}
