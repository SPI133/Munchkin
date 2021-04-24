package Players;

import Deck.ClassCard;
import Deck.ItemCard;
import Deck.RaceCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;

    @Before
    public void setPlayer() {
        player = new Player("Name", Player.Gender.MALE);
    }

    @Test
    public void testAlterLevel() {
        final int originalLevel = player.getLevel();
        player.alterLevel(0);
        final int alter1 = player.getLevel();
        player.alterLevel(2);
        final int alter2 = player.getLevel();
        player.alterLevel(-3);
        final int alter3 = player.getLevel();

        Assert.assertEquals(originalLevel,1);
        Assert.assertEquals(alter1,1);
        Assert.assertEquals(alter2,3);
        Assert.assertEquals(alter3,1);
    }

    /**
     * RACE TESTS
     */
    @Test
    public void testAddRace1Race() {
        RaceCard race = new RaceCard(RaceCard.Race.ORC);
        boolean raceAdded = player.addRace(race);
        Assert.assertTrue(player.getRaces().contains(race));
        Assert.assertTrue(raceAdded);
    }

    @Test
    public void testAddRace2RaceNoHalfBreed() {
        RaceCard race1 = new RaceCard(RaceCard.Race.ORC);
        boolean race1Added = player.addRace(race1);
        RaceCard race2 = new RaceCard(RaceCard.Race.ORC);
        boolean race2Added = player.addRace(race2);
        Assert.assertTrue(player.getRaces().contains(race1));
        Assert.assertTrue(race1Added);
        Assert.assertFalse(player.getRaces().contains(race2));
        Assert.assertFalse(race2Added);
    }

    @Test
    public void testAddRace2RaceWithHalfBreed() {
        RaceCard race1 = new RaceCard(RaceCard.Race.ORC);
        boolean race1Added = player.addRace(race1);
        player.setHalfBreed(1);
        RaceCard race2 = new RaceCard(RaceCard.Race.ORC);
        boolean race2Added = player.addRace(race2);
        Assert.assertTrue(player.getRaces().contains(race1));
        Assert.assertTrue(race1Added);
        Assert.assertTrue(player.getRaces().contains(race2));
        Assert.assertTrue(race2Added);
    }

    @Test
    public void testRemoveRace1Race() {
        RaceCard race = new RaceCard(RaceCard.Race.ORC);
        player.addRace(race);
        boolean raceRemoved = player.removeRace(RaceCard.Race.ORC);
        Assert.assertFalse(player.getRaces().contains(race));
        Assert.assertTrue(raceRemoved);
    }

    /**
     * CLASSES TESTS
     */
    @Test
    public void testAddClass1Class() {
        ClassCard theClass = new ClassCard(ClassCard.Class.WARRIOR);
        boolean classAdded = player.addClass(theClass);
        Assert.assertTrue(player.getClasses().contains(theClass));
        Assert.assertTrue(classAdded);
    }

    @Test
    public void testAddClass2ClassNoSuperMunchkin() {
        ClassCard class1 = new ClassCard(ClassCard.Class.WARRIOR);
        boolean class1Added = player.addClass(class1);
        ClassCard class2 = new ClassCard(ClassCard.Class.WIZARD);
        boolean class2Added = player.addClass(class2);
        Assert.assertTrue(player.getClasses().contains(class1));
        Assert.assertTrue(class1Added);
        Assert.assertFalse(player.getClasses().contains(class2));
        Assert.assertFalse(class2Added);
    }

    @Test
    public void testAddClass2ClassWithSuperMunchkin() {
        ClassCard class1 = new ClassCard(ClassCard.Class.WARRIOR);
        boolean class1Added = player.addClass(class1);
        player.setSuperMunchkin(1);
        ClassCard class2 = new ClassCard(ClassCard.Class.WIZARD);
        boolean class2Added = player.addClass(class2);
        Assert.assertTrue(player.getClasses().contains(class1));
        Assert.assertTrue(class1Added);
        Assert.assertTrue(player.getClasses().contains(class2));
        Assert.assertTrue(class2Added);
    }

    @Test
    public void testAddClass2ClassWithSuperMunchkinSameClass() {
        ClassCard class1 = new ClassCard(ClassCard.Class.WARRIOR);
        boolean class1Added = player.addClass(class1);
        player.setSuperMunchkin(1);
        ClassCard class2 = new ClassCard(ClassCard.Class.WARRIOR);
        boolean class2Added = player.addClass(class2);
        Assert.assertTrue(player.getClasses().contains(class1));
        Assert.assertTrue(class1Added);
        Assert.assertFalse(player.getClasses().contains(class2));
        Assert.assertFalse(class2Added);
    }

    @Test
    public void testRemoveClass1Class(){
        ClassCard theClass = new ClassCard(ClassCard.Class.WARRIOR);
        player.addClass(theClass);
        boolean classRemoved = player.removeClass(ClassCard.Class.WARRIOR);
        Assert.assertFalse(player.getClasses().contains(theClass));
        Assert.assertTrue(classRemoved);
    }


    /**
     * CHECK EQUIPMENT TESTS
     */

    @Test
    public void testCheckEquipmentEmpty(){
        final ItemCard item = new ItemCard(ItemCard.Type.HEADGEAR, false);
        final boolean itemCanBeAdded = player.checkEquipment(item);
        Assert.assertTrue(itemCanBeAdded);
    }

    @Test
    public void testCheckEquipmentWithHeadgear(){
        final ItemCard item1 = new ItemCard(ItemCard.Type.HEADGEAR, false);
        final boolean item1CanBeAdded = player.checkEquipment(item1);


        final ItemCard item2 = new ItemCard(ItemCard.Type.HEADGEAR, false);
        final boolean item2CanBeAdded = player.checkEquipment(item2);

        Assert.assertTrue(item1CanBeAdded);
        Assert.assertFalse(item2CanBeAdded);
    }

    /**
     * ADD EQUIPMENT TESTS
     */

    @Test
    public void testAddEquipmentHeadgear() {
        final ItemCard item = new ItemCard(ItemCard.Type.HEADGEAR, false);
        player.addEquipment(item);
        //TODO:final boolean

        Assert.assertTrue(player.getExtraEquipment().contains(item));
    }

    @Test
    public void testAddEquipment2ItemsSameHeadgear() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HEADGEAR, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HEADGEAR, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsSameArmor() {
        ItemCard item1 = new ItemCard(ItemCard.Type.ARMOR, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.ARMOR, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsSameFootgear() {
        ItemCard item1 = new ItemCard(ItemCard.Type.FOOTGEAR, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.FOOTGEAR, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsSameHands() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertTrue(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment3ItemsSameHands() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item2);
        ItemCard item3 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item3);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertTrue(player.getExtraEquipment().contains(item2));
        Assert.assertFalse(player.getExtraEquipment().contains(item3));
    }

    @Test
    public void testAddEquipment2ItemsHands_2Hand() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HANDS_2, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsHandHands_2() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HANDS_2, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsHands_3Hand() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HANDS_3, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item2);
        Assert.assertFalse(player.getExtraEquipment().contains(item1));
        Assert.assertTrue(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsHandHands_3() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HAND, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HANDS_3, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsHands_2Hands_3() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HANDS_2, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HANDS_3, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsHands_3Hands_2() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HANDS_3, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HANDS_2, false);
        player.addEquipment(item2);
        Assert.assertFalse(player.getExtraEquipment().contains(item1));
        Assert.assertTrue(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsSameHands_2() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HANDS_2, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HANDS_2, false);
        player.addEquipment(item2);
        Assert.assertTrue(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    @Test
    public void testAddEquipment2ItemsSameHands_3() {
        ItemCard item1 = new ItemCard(ItemCard.Type.HANDS_3, false);
        player.addEquipment(item1);
        ItemCard item2 = new ItemCard(ItemCard.Type.HANDS_3, false);
        player.addEquipment(item2);
        Assert.assertFalse(player.getExtraEquipment().contains(item1));
        Assert.assertFalse(player.getExtraEquipment().contains(item2));
    }

    //TODO: more tests on equipment
}
