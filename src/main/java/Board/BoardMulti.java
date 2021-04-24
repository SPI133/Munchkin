package Board;

import Deck.*;
import Players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BoardMulti implements BoardInterface{
    private int playerTurn;
    private int amountOfPlayers;
    private Player currentPlayer;
    private List<Player> players;
    private List<RoomCard> roomCards;
    private List<TreasureCard> treasureCards;
    private Stack<RoomCard> usedRoomCards;
    private Stack<TreasureCard> usedTreasureCards;

    public BoardMulti(final int amountOfPlayers) {
        this.playerTurn = 0;
        this.amountOfPlayers = amountOfPlayers;
        init();
    }

    private void init() {
        initPlayers();
        initCards();
    }

    private void initPlayers() {
        players = new ArrayList<>(amountOfPlayers);
        for (int i = 0; i < amountOfPlayers; i++) {
            final String name = "Player" + i;
            Player player = new Player(name, Player.Gender.MALE);
            players.add(player);
        }
    }

    private void initCards() {
        roomCards = new ArrayList<>();
        treasureCards = new ArrayList<>();
        usedRoomCards = new Stack<>();
        usedTreasureCards = new Stack<>();
        //TODO: read cards from file and add them
        Collections.shuffle(roomCards);
        Collections.shuffle(treasureCards);
    }

    @Override
    public void start() {
        shareCards();
        while (!gameOver()) {
            currentPlayer = players.get(playerTurn);
            currentPlayer.arrangeCards();
            kickOpenTheDoor();
            charity();
            nextTurn();
        }
    }

    private void shareCards() {
        for (Player player : players) {
            shareCards(player);
        }
    }

    private void shareCards(final Player player) {
        final ArrayList<Card> playerCards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            playerCards.add(removeRoomCard());
        }
        for (int i = 0; i < 4; i++) {
            playerCards.add(removeTreasureCard());
        }

        //player.setHand(playerCards);
    }

    //TODO:
    private void kickOpenTheDoor() {
        final RoomCard card = removeRoomCard();
        if (card instanceof MonsterCard) {
            combat((MonsterCard) card);
            return;
        } else if (card instanceof CurseCard) {
            cursed();
        }
        lookForTroubleOrLootTheRoom();
    }


    //TODO:
    private void combat(final MonsterCard monsterCard) {
        final List<Card> playerRaces = currentPlayer.getRaces();
        final List<Card> playerClasses = currentPlayer.getClasses();
        final List<Card> playerEquipment = currentPlayer.getExtraEquipment();
        final List<Card> playerCurses = currentPlayer.getCurses();
        final Player.Gender playerGender = currentPlayer.getGender();
        final int playerLevel = currentPlayer.getLevel();

        final int monsterLevel = monsterCard.getLevel();

        playersInterfere();

        if (monsterLevel < playerLevel) {
            //playersInterfere();
            victory(monsterCard);

        } else if (monsterLevel == playerLevel) {
            for (Card card : playerClasses) {
                ClassCard classCard = (ClassCard) card;
                if (classCard.getTheClass() == ClassCard.Class.WARRIOR) {
                    victory(monsterCard);
                }
            }
        }
    }

    private void victory(final MonsterCard monsterCard) {
        final int monsterRewardLevels = monsterCard.getRewardLevels();
        currentPlayer.alterLevel(monsterRewardLevels);

        final int monsterRewardTreasures = monsterCard.getRewardTreasures();
        List<TreasureCard> treasures = new ArrayList<>(monsterRewardTreasures);
        for (int i = 0; i < monsterRewardTreasures; i++) {
            treasures.add(removeTreasureCard());
        }

        //currentPlayer.addToHand(treasures);
    }

    private void playersInterfere() {
    }

    //TODO:
    private void cursed() {
    }

    //TODO:
    private void lookForTroubleOrLootTheRoom() {

    }

    //TODO:
    private void charity() {

    }

    private void nextTurn() {
        playerTurn = ++playerTurn % amountOfPlayers;
    }

    private RoomCard removeRoomCard() {
        if (roomCards.isEmpty()) {
            roomCards.addAll(usedRoomCards);
            Collections.shuffle(roomCards);
        }
        return roomCards.remove(0);
    }


    private TreasureCard removeTreasureCard() {
        if (treasureCards.isEmpty()) {
            treasureCards.addAll(usedTreasureCards);
            Collections.shuffle(treasureCards);
        }
        return treasureCards.remove(0);
    }

    private boolean gameOver() {
        for (Player p : players) {
            if (p.getLevel() == 10) {
                return true;
            }
        }
        //TODO:write Code for Seals of Apocalypse new BoardApocalypse class
        return false;
    }
}
