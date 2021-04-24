package Players;

import Deck.ItemCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerEquipmentTest {
    private PlayerEquipment equipment;

    @Before
    public void setPlayer() {
        equipment = new PlayerEquipment();
    }

    @Test
    public void testCopyConstructor() {
        final ItemCard gear = new ItemCard(ItemCard.Type.HEADGEAR, false);
        equipment.setHeadgear(gear);
        final ItemCard gear2 = new ItemCard(ItemCard.Type.ARMOR, false);
        equipment.setArmor(gear2);
        final ItemCard gear3 = new ItemCard(ItemCard.Type.FOOTGEAR, false);
        equipment.setFootgear(gear3);
        final ItemCard gear4 = new ItemCard(ItemCard.Type.HAND, false);
        equipment.addHandEquipment(gear4);
        PlayerEquipment equipment2 = new PlayerEquipment(equipment);
        Assert.assertNotSame(equipment, equipment2);
        Assert.assertNotSame(equipment.getHeadgear(), equipment2.getHeadgear());
        Assert.assertNotSame(equipment.getArmor(), equipment2.getArmor());
        Assert.assertNotSame(equipment.getFootgear(), equipment2.getFootgear());
        Assert.assertNotSame(equipment.getHands(), equipment2.getHands());
        Assert.assertEquals(equipment.getAmountOfHands(), equipment2.getAmountOfHands());
        //TODO: write test Assert.assertEquals();
    }

    @Test
    public void testGetNullHeadgear() {
        Assert.assertNull(equipment.getHeadgear());
    }

    @Test
    public void testSetHeadgear() {
        final ItemCard gear = new ItemCard(ItemCard.Type.HEADGEAR, false);
        gear.setId("1");
        equipment.setHeadgear(gear);

        Assert.assertEquals(equipment.getHeadgear(), gear);
        Assert.assertNotSame(equipment.getHeadgear(), gear);
    }

    @Test
    public void testSetNullHeadgear() {
        equipment.setHeadgear(null);

        Assert.assertNull(equipment.getHeadgear());
    }

    @Test
    public void testGetNullArmor() {
        Assert.assertNull(equipment.getArmor());
    }

    @Test
    public void testSetArmor() {
        final ItemCard gear = new ItemCard(ItemCard.Type.ARMOR, false);
        gear.setId("1");
        equipment.setArmor(gear);

        Assert.assertEquals(equipment.getArmor(), gear);
        Assert.assertNotSame(equipment.getArmor(), gear);
    }

    @Test
    public void testGetEmptyHands() {
        final List<ItemCard> newHands = equipment.getHands();

        Assert.assertTrue(newHands.isEmpty());
        Assert.assertNotSame(newHands, equipment.getHands());
    }

    /*@Test
    public void testGetHands1Item(){
        final List<ItemCard> newHands = equipment.getHands();

        Assert.assertTrue(newHands.isEmpty());
        Assert.assertNotSame(newHands, equipment.getHands());
    }*/

    @Test
    public void testGetNullFootgear() {
        Assert.assertNull(equipment.getFootgear());
    }

    @Test
    public void testSetFootgear() {
        final ItemCard gear = new ItemCard(ItemCard.Type.FOOTGEAR, false);
        gear.setId("1");
        equipment.setFootgear(gear);

        Assert.assertEquals(equipment.getFootgear(), gear);
        Assert.assertNotSame(equipment.getFootgear(), gear);
    }

    @Test
    public void testAlterAmountOfHand() {
        final int original = equipment.getAmountOfHands();
        equipment.alterAmountOfHands(1);
        final int alter1 = equipment.getAmountOfHands();
        equipment.alterAmountOfHands(-2);
        final int alter2 = equipment.getAmountOfHands();
        equipment.alterAmountOfHands(0);
        final int alter3 = equipment.getAmountOfHands();

        Assert.assertEquals(2, original);
        Assert.assertEquals(3, alter1);
        Assert.assertEquals(1, alter2);
        Assert.assertEquals(1, alter3);
    }

    @Test
    public void testAlterAmountOfHandNegative(){
        equipment.alterAmountOfHands(-3);
        final int alter = equipment.getAmountOfHands();

        Assert.assertEquals(0, alter);
    }

    @Test
    public void testAddHandEquipment1Hand() {
        final ItemCard hand = new ItemCard(ItemCard.Type.HAND, false);
        hand.setId("1");
        equipment.addHandEquipment(hand);

        Assert.assertEquals(equipment.getHands().get(0),hand);
        Assert.assertEquals(1, equipment.getAmountOfHands());
    }

    @Test
    public void testAddHandEquipment2Hands() {
        final ItemCard hand = new ItemCard(ItemCard.Type.HANDS_2, false);
        hand.setId("1");
        equipment.addHandEquipment(hand);

        Assert.assertEquals(equipment.getHands().get(0),hand);
        Assert.assertEquals(0, equipment.getAmountOfHands());
    }

    @Test
    public void testAddHandEquipment3Hands() {
        final ItemCard hand = new ItemCard(ItemCard.Type.HANDS_3, false);
        hand.setId("1");
        equipment.alterAmountOfHands(1);
        equipment.addHandEquipment(hand);

        Assert.assertEquals(equipment.getHands().get(0),hand);
        Assert.assertEquals(0, equipment.getAmountOfHands());
    }

    @Test
    public void testAddHandEquipment3HandsNoAlter(){
        final ItemCard hand = new ItemCard(ItemCard.Type.HANDS_3, false);
        hand.setId("1");
        equipment.addHandEquipment(hand);

        Assert.assertEquals(equipment.getHands().get(0),hand);
        Assert.assertEquals(0, equipment.getAmountOfHands());
    }

    @Test
    public void testRemoveHeadgear() {
        final ItemCard gear = new ItemCard(ItemCard.Type.HEADGEAR, false);
        equipment.setHeadgear(gear);
        equipment.removeHeadgear();

        Assert.assertNull(equipment.getHeadgear());
    }

    @Test
    public void testRemoveArmor() {
        final ItemCard gear = new ItemCard(ItemCard.Type.ARMOR, false);
        equipment.setArmor(gear);
        equipment.removeArmor();

        Assert.assertNull(equipment.getArmor());
    }

    @Test
    public void testRemoveHandEquipmentNull(){
        equipment.removeHandEquipment(null);
    }

    @Test
    public void testRemoveHandEquipment1Hand() {
        final ItemCard hand = new ItemCard(ItemCard.Type.HAND, false);
        equipment.addHandEquipment(hand);
        equipment.removeHandEquipment(hand);

        Assert.assertNotEquals(equipment.getHands().get(0), hand);
        Assert.assertEquals(2, equipment.getAmountOfHands());
    }

    @Test
    public void testRemoveHandEquipment1Hand2Items() {
        final ItemCard hand = new ItemCard(ItemCard.Type.HAND, false);
        final ItemCard hand2 = new ItemCard(ItemCard.Type.HAND, false);
        equipment.addHandEquipment(hand);
        equipment.addHandEquipment(hand2);
        equipment.removeHandEquipment(hand);
        equipment.removeHandEquipment(hand2);

        Assert.assertFalse(equipment.getHands().contains(hand));
        Assert.assertFalse(equipment.getHands().contains(hand2));
        Assert.assertEquals(2, equipment.getAmountOfHands());
    }

    @Test
    public void testRemoveHandEquipment2Hands() {
        final ItemCard hand = new ItemCard(ItemCard.Type.HANDS_2, false);
        equipment.addHandEquipment(hand);
        equipment.removeHandEquipment(hand);
        Assert.assertFalse(equipment.getHands().contains(hand));
        Assert.assertEquals(2, equipment.getAmountOfHands());
    }

    @Test
    public void testRemoveHandEquipment3Hands() {
        final ItemCard hand = new ItemCard(ItemCard.Type.HANDS_3, false);
        equipment.addHandEquipment(hand);
        equipment.removeHandEquipment(hand);
        Assert.assertFalse(equipment.getHands().contains(hand));
        Assert.assertEquals(2, equipment.getAmountOfHands());
    }

    @Test
    public void testRemoveFootgear() {
        final ItemCard gear = new ItemCard(ItemCard.Type.FOOTGEAR, false);
        equipment.setFootgear(gear);
        equipment.removeFootgear();
        Assert.assertNull(equipment.getFootgear());
    }

    @Test
    public void testHasEnoughHands() {
        Assert.assertTrue(equipment.hasEnoughHands(1));
        Assert.assertTrue(equipment.hasEnoughHands(2));
        Assert.assertFalse(equipment.hasEnoughHands(3));
    }

    @Test
    public void testIsEmpty() {
        final boolean emptyEquipment = equipment.isEmpty();
        Assert.assertTrue(emptyEquipment);
    }
}
