package Deck;

public class CurseCard extends RoomCard {

    private boolean instantCurse;
    private Curse curse;

    public CurseCard(final boolean instantCurse, Curse curse){

        this.instantCurse = instantCurse;
        this.curse = curse;
    }

    public boolean isInstantCurse() {
        return instantCurse;
    }

    public void setInstantCurse(boolean instantCurse) {
        this.instantCurse = instantCurse;
    }

    public Curse getCurse() {
        return curse;
    }

    public void setCurse(Curse curse) {
        this.curse = curse;
    }

    public enum Curse{//TODO: add curses
        LOSE_ARMOR,
    }
}
