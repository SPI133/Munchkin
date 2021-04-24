package Board;

import Deck.ClassCard;
import Deck.CurseCard;
import Deck.RoomCard;
import Players.Player;
import org.junit.*;

import java.util.EmptyStackException;
import java.util.Stack;

public class BoardTest {

    private Board board;
    private Player player;

    @Before
    public void setBoard() {
        player = new Player("f", Player.Gender.MALE);
        board = new Board(player);
    }

    @Test
    public void testAddRoomCards() {
        final RoomCard card1 = new CurseCard(false, CurseCard.Curse.LOSE_ARMOR);
        final RoomCard card2 = new ClassCard(ClassCard.Class.WARRIOR);
        final Stack<RoomCard> roomCards = new Stack<>();
        roomCards.push(card1);
        roomCards.push(card2);
        board.addRoomCards(roomCards);
        final boolean roomCardsContainCard1 = board.roomCardsContainCard(card1);
        final boolean roomCardsContainCard2 = board.roomCardsContainCard(card2);
        Stack<RoomCard> roomCardsCopy = board.getRoomCards();

        Assert.assertTrue(roomCardsContainCard1);
        Assert.assertTrue(roomCardsContainCard2);
        Assert.assertSame(card1, roomCardsCopy.get(0));
        Assert.assertSame(card2, roomCardsCopy.get(1));
    }

    @Test
    public void testAddUsedRoomCards() {
        final RoomCard card1 = new CurseCard(false, CurseCard.Curse.LOSE_ARMOR);
        final RoomCard card2 = new ClassCard(ClassCard.Class.WARRIOR);
        final Stack<RoomCard> usedRoomCards = new Stack<>();
        usedRoomCards.push(card1);
        usedRoomCards.push(card2);
        board.addUsedRoomCards(usedRoomCards);
        final boolean usedRoomCardsContainCard1 = board.usedRoomCardsContainCard(card1);
        final boolean usedRoomCardsContainCard2 = board.usedRoomCardsContainCard(card2);
        Stack<RoomCard> usedRoomCardsCopy = board.getUsedRoomCards();

        Assert.assertTrue(usedRoomCardsContainCard1);
        Assert.assertTrue(usedRoomCardsContainCard2);
        Assert.assertSame(card1, usedRoomCardsCopy.get(0));
        Assert.assertSame(card2, usedRoomCardsCopy.get(1));
    }

    @Test
    public void testRemoveRoomCard() {
        final RoomCard card1 = new CurseCard(false, CurseCard.Curse.LOSE_ARMOR);
        final RoomCard card2 = new ClassCard(ClassCard.Class.WARRIOR);
        final Stack<RoomCard> roomCards = new Stack<>();
        roomCards.push(card1);
        roomCards.push(card2);
        board.addRoomCards(roomCards);

        final RoomCard removedCard1 = board.removeRoomCard();
        final RoomCard removedCard2 = board.removeRoomCard();
        Stack<RoomCard> roomCardsCopy = board.getRoomCards();

        Assert.assertSame(card1, removedCard2);
        Assert.assertSame(card2, removedCard1);
        Assert.assertTrue(roomCardsCopy.empty());
    }

    @Test
    public void testRemoveRoomCardWithEmptyRoomCards() {
        final RoomCard card1 = new CurseCard(false, CurseCard.Curse.LOSE_ARMOR);
        final RoomCard card2 = new ClassCard(ClassCard.Class.WARRIOR);
        final Stack<RoomCard> usedRoomCards = new Stack<>();
        usedRoomCards.push(card1);
        usedRoomCards.push(card2);
        board.addUsedRoomCards(usedRoomCards);

        final RoomCard removedCard = board.removeRoomCard();
        final boolean cardIsFromUsedRoomCards =
                removedCard == card1 || removedCard == card2;

        Stack<RoomCard> roomCardsCopy = board.getRoomCards();
        Stack<RoomCard> usedRoomCardsCopy = board.getUsedRoomCards();

        final boolean roomCardsContainCardFromUsedRoomCards =
                roomCardsCopy.get(0) == card1 ||
                        roomCardsCopy.get(0) == card2;

        Assert.assertTrue(cardIsFromUsedRoomCards);
        Assert.assertEquals(1, roomCardsCopy.size());
        Assert.assertTrue(usedRoomCardsCopy.empty());
        Assert.assertTrue(roomCardsContainCardFromUsedRoomCards);
    }

    @Test(expected = EmptyStackException.class)
    public void testRemoveRoomCardWithBothRoomStacksEmpty() {
        board.removeRoomCard();
    }

}
