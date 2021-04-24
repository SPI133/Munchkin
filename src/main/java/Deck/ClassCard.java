package Deck;

public class ClassCard extends RoomCard{

    private Class theClass;

    public ClassCard(final Class theClass) {
        this.theClass = theClass;
    }

    public Class getTheClass() {
        return theClass;
    }

    public void setTheClass(final Class theClass) {
        this.theClass = theClass;
    }
    //TODO:ADD CLASSES
    public enum Class{
        WARRIOR,
        WIZARD
    }
}
