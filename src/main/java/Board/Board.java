package Board;

import Deck.*;
import Players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * author: SPI133
 * Munchkin Singleplayer Board
 */
public class Board implements BoardInterface {

    private static final int TARGET_LEVEL = 10;

    private Player currentPlayer;
    private Stack<RoomCard> roomCards;
    private Stack<TreasureCard> treasureCards;
    private Stack<RoomCard> usedRoomCards;
    private Stack<TreasureCard> usedTreasureCards;

    Board(Player player) {
        currentPlayer = player;
        init();
    }

    private void init() {
        initCards();
    }

    private void initCards() {
        roomCards = new Stack<>();
        treasureCards = new Stack<>();
        usedRoomCards = new Stack<>();
        usedTreasureCards = new Stack<>();
        Collections.shuffle(roomCards);
        Collections.shuffle(treasureCards);
    }

    void readCards(){
        //TODO: read cards from file and add them
    }

    void addRoomCards(final Stack<RoomCard> cards){
        roomCards.addAll(cards);
    }

    void addUsedRoomCards(final Stack<RoomCard> cards){
        usedRoomCards.addAll(cards);
    }

    @Override
    public void start() {
        shareCards();
        while (!gameOver()) {
            kickOpenTheDoor();
            charity();
        }
    }

    private void shareCards() {
        shareCards(currentPlayer);
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
            cursed((CurseCard) card);
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

        if (monsterLevel < playerLevel) {
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
        for (int i = 0; i < monsterRewardTreasures; i++) {
            currentPlayer.addToHand(removeTreasureCard());
        }
    }

    //TODO:
    private void cursed(final CurseCard curseCard) {
        if (curseCard.isInstantCurse()){
            currentPlayer.addCurse(curseCard);
        } else{
            switch(curseCard.getCurse()){

            }
        }
    }

    //TODO:
    private void lookForTroubleOrLootTheRoom() {

    }

    //TODO:
    private void charity() {
    }

    RoomCard removeRoomCard() {
        if (roomCards.isEmpty()) {
            roomCards.addAll(usedRoomCards);
            usedRoomCards.clear();
            Collections.shuffle(roomCards);
        }
        return roomCards.pop();
    }


    private TreasureCard removeTreasureCard() {
        if (treasureCards.isEmpty()) {
            treasureCards.addAll(usedTreasureCards);
            usedTreasureCards.clear();
            Collections.shuffle(treasureCards);
        }
        return treasureCards.pop();
    }

    private boolean gameOver() {
        return currentPlayer.getLevel() >= TARGET_LEVEL;
    }

    boolean roomCardsContainCard(final RoomCard card){
        return roomCards.contains(card);
    }

    boolean usedRoomCardsContainCard(final RoomCard card){
        return usedRoomCards.contains(card);
    }

    Stack<RoomCard> getRoomCards(){
        Stack<RoomCard> newStack = new Stack<>();
        newStack.addAll(roomCards);
        return newStack;
    }

    Stack<RoomCard> getUsedRoomCards(){
        Stack<RoomCard> newStack = new Stack<>();
        newStack.addAll(usedRoomCards);
        return newStack;
    }
}
