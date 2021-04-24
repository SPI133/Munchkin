package Deck;

import org.junit.Assert;
import org.junit.Test;

public class ItemCardTest {

    @Test
    public void testSameTypeHeadgear(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HEADGEAR,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HEADGEAR,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeFootgear(){
        ItemCard card1 = new ItemCard(ItemCard.Type.FOOTGEAR,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.FOOTGEAR,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeArmor(){
        ItemCard card1 = new ItemCard(ItemCard.Type.ARMOR,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.ARMOR,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHand(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HAND,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HAND,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHands_2(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HANDS_2,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HANDS_2,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHands_3(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HANDS_3,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HANDS_3,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHandHands_2(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HAND,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HANDS_2,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHandHands_3(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HAND,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HANDS_3,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHands_2Hand(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HANDS_2,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HAND,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHands_2Hands_3(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HANDS_2,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HANDS_3,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHands_3Hand(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HANDS_3,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HAND,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }

    @Test
    public void testSameTypeHands_3Hands_2(){
        ItemCard card1 = new ItemCard(ItemCard.Type.HANDS_3,false);
        ItemCard card2 = new ItemCard(ItemCard.Type.HANDS_2,false);
        boolean sameType = card1.sameType(card2);
        Assert.assertTrue(sameType);
    }
}
