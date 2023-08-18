import enums.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterTest {
    Gear<FootType> sacredHoverboard;
    Gear<HandType> cursedAxes;
    Gear<HandType> cursedGauntlet;
    Gear<HandType> darkGauntlet;
    Gear<HandType> mysteriousShield;
    Gear<HandType> scurryingGloves;
    Gear<HandType> ancientDagger;
    Gear<HeadType> infernalVisors;
    Gear<HeadType> glacialBandanna;

    @Before
    public void GearTest() {
        sacredHoverboard = new FootGear(Adjective.SACRED, FootType.HOVERBOARD, 5, 3);
        cursedAxes = new HandGear(Adjective.CURSED, HandType.AXE, 10);
        cursedGauntlet = new HandGear(Adjective.CURSED, HandType.GAUNTLET, 10);
        darkGauntlet = new HandGear(Adjective.DARK, HandType.GAUNTLET, 5);
        mysteriousShield = new HandGear(Adjective.MYSTERIOUS, HandType.SHIELD, 1, 3);
        scurryingGloves = new HandGear(Adjective.SCURRYING, HandType.GLOVES, 5, 2);
        ancientDagger = new HandGear(Adjective.ANCIENT, HandType.DAGGER, 8);
        infernalVisors = new HeadGear(Adjective.INFERNAL, HeadType.VISORS, 5);

        // user may define his own adjective
        glacialBandanna = new HeadGear(Adjective.single("Glacial"), HeadType.BANDANNA, 3);

        // negative attack or defense throws exception
        Assert.assertThrows(IllegalArgumentException.class, ()-> new FootGear(Adjective.CURSED, FootType.BOOTS, -3, -5));
        Assert.assertThrows(IllegalArgumentException.class, ()-> new HeadGear(Adjective.DARK, HeadType.BEANIE, -2));
        Assert.assertThrows(IllegalArgumentException.class, ()-> new HandGear(Adjective.SACRED, HandType.HAMMER, -2));

        // check getters
        Assert.assertEquals(3, sacredHoverboard.getDefense());
        Assert.assertEquals(5, sacredHoverboard.getAttack());
        Assert.assertEquals("Sacred Hoverboard", sacredHoverboard.getName());
        Assert.assertEquals(Adjective.SACRED, sacredHoverboard.getAdjective());
        Assert.assertEquals(FootType.HOVERBOARD, sacredHoverboard.getGearType());

        // handgear with 3 variables should have 0 defense
        Assert.assertEquals(0, cursedAxes.getDefense());

        //  headgear should have 0 attack
        Assert.assertEquals(0, infernalVisors.getAttack());

        // Assert.(IllegalArgumentException.class, () -> sacredHoverboard.combineGear(cursedAxes));
    }

    @Test
    public void Character() {
        Character c1 = new Character("Leo");
        Character c2 = new Character();
        Character c3 = new Character("Murphy", 1, 1);

        // initial attack and defense 0
        Assert.assertEquals("Leo", c1.getName());
        Assert.assertEquals(0, c1.getPlayerAttack());
        Assert.assertEquals(0, c1.getPlayerDefense());

        // empty constructor gives default name
        Assert.assertEquals("DefaultCharacter", c2.getName());
        c2.setName("NewName");
        Assert.assertEquals("NewName", c2.getName());

        // check assigned initial attack and defense
        Assert.assertEquals(1, c3.getPlayerAttack());
        Assert.assertEquals(1, c3.getPlayerDefense());

        c1.pickupGear(sacredHoverboard);
        c1.pickupGear(infernalVisors);
        c1.pickupGear(cursedAxes);
        c1.pickupGear(darkGauntlet);
        Assert.assertEquals("""
                Name = Leo
                Attack = 20
                Defense = 8
                HeadGear = Infernal Visors -- defense strength: 5
                FootGears = [Sacred Hoverboard -- attack strength: 5, defense strength: 3]
                HandGears = [Cursed Axe -- attack strength: 10, Dark Gauntlet -- attack strength: 5]
                """, c1.toString());

        // gear will be combined with Cursed Axe
        c1.pickupGear(mysteriousShield);
        Assert.assertEquals("""
                Name = Leo
                Attack = 21
                Defense = 11
                HeadGear = Infernal Visors -- defense strength: 5
                FootGears = [Sacred Hoverboard -- attack strength: 5, defense strength: 3]
                HandGears = [Cursed, Mysterious Shield -- attack strength: 11, defense strength: 3, Dark Gauntlet -- attack strength: 5]
                """, c1.toString());

        // gear will be combined with Dark Gauntlet because of fewer adjectives
        c1.pickupGear(scurryingGloves);
        Assert.assertEquals("""
               Name = Leo
               Attack = 26
               Defense = 13
               HeadGear = Infernal Visors -- defense strength: 5
               FootGears = [Sacred Hoverboard -- attack strength: 5, defense strength: 3]
               HandGears = [Dark, Scurrying Gloves -- attack strength: 10, defense strength: 2, Cursed, Mysterious Shield -- attack strength: 11, defense strength: 3]
               """, c1.toString());

        // gear will be combined with Cursed, Mysterious Shield (alphabetically in front)
        c1.pickupGear(ancientDagger);
        Assert.assertEquals("""
                Name = Leo
                Attack = 34
                Defense = 13
                HeadGear = Infernal Visors -- defense strength: 5
                FootGears = [Sacred Hoverboard -- attack strength: 5, defense strength: 3]
                HandGears = [Cursed, Mysterious, Ancient Dagger -- attack strength: 19, defense strength: 3, Dark, Scurrying Gloves -- attack strength: 10, defense strength: 2]
                """, c1.toString());


        Assert.assertEquals("Infernal Visors -- defense strength: 5", c1.getHeadGear().toString());
        Assert.assertEquals("[Cursed, Mysterious, Ancient Dagger -- attack strength: 19, defense strength: 3, " +
                "Dark, Scurrying Gloves -- attack strength: 10, defense strength: 2]", c1.getHandGears().toString());
        Assert.assertEquals("[Sacred Hoverboard -- attack strength: 5, defense strength: 3]", c1.getFootGears().toString());

    }

    @Test
    public void SortingTest() {
        List<Gear<HandType>> gearList = new ArrayList<>();
        gearList.add(cursedAxes);
        gearList.add(cursedGauntlet);
        gearList.add(darkGauntlet);
        gearList.add(mysteriousShield);
        gearList.add(scurryingGloves);
        gearList.add(ancientDagger);
        Collections.sort(gearList);

        StringBuilder sb = new StringBuilder();
        for (Gear<HandType> gear : gearList) {
            sb.append(gear.toString()).append("\n");
        }

        Assert.assertEquals("""
                Ancient Dagger -- attack strength: 8
                Cursed Axe -- attack strength: 10
                Cursed Gauntlet -- attack strength: 10
                Dark Gauntlet -- attack strength: 5
                Mysterious Shield -- attack strength: 1, defense strength: 3
                Scurrying Gloves -- attack strength: 5, defense strength: 2
                """, sb.toString());
    }
}