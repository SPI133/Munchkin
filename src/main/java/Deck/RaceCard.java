package Deck;

public class RaceCard extends RoomCard {

    private Race race;

    public RaceCard(final Race race) {
        this.race = race;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(final Race race) {
        this.race = race;
    }
//TODO:ADD RACES
    public enum Race{
        DWARF,
        ELF,
        ORC
    }
}
