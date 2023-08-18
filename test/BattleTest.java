import enums.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleTest {
    // generate one random gear using one random object
    public static Gear<?> generateRandomGear(Random random) {
        Adjective randomAdjective = Adjective.getRandomAdjective(random);
        int randomAttack = random.nextInt(15) + 1;
        int randomDefense = random.nextInt(15) + 1;

        return switch (random.nextInt(3)) {
            case 0 -> new HeadGear(randomAdjective, HeadType.getRandomHeadType(random), randomDefense);
            case 1 -> new FootGear(randomAdjective, FootType.getRandomFootType(random), randomAttack, randomDefense);
            case 2 -> new HandGear(randomAdjective, HandType.getRandomHandType(random), randomAttack);
            default -> null;
        };
    }

    // generate the random list of 20 items
    public static List<Gear<?>> generateRandomItemList(Random random) {
        List<Gear<?>> itemList = new ArrayList<>(20);

        for (int i = 0; i < 20; i++) {
            itemList.add(generateRandomGear(random));
        }

        return itemList;
    }

    public static void main(String[] args) {
        Character c1 = new Character("Zoe");
        Character c2 = new Character("Murphy");
        Character c3 = new Character("Leo");
        Character c4 = new Character("Tom");

        long seed1 = 54321L;
        long seed2 = 55555L;

        Random random1 = new Random(seed1);
        Random random2 = new Random(seed2);

        List<Gear<?>> itemList1 = generateRandomItemList(random1);
        List<Gear<?>> itemList2 = generateRandomItemList(random2);

        Battle newBattle1 = new Battle(c1, c2, itemList1);
        Battle newBattle2 = new Battle(c3, c4, itemList2);
        Assert.assertEquals("""
                Battle Information:
                Character 1 Name: Zoe
                Character 2 Name: Murphy
                List of Items:
                  -  Cursed Tiara -- defense strength: 3
                  -  Dark Hoverwheels -- attack strength: 8, defense strength: 10
                  -  Infernal Hammer -- attack strength: 12
                  -  Ancient Hoverwheels -- attack strength: 13, defense strength: 9
                  -  Eternal Scooter -- attack strength: 15, defense strength: 15
                  -  Cursed Tiara -- defense strength: 12
                  -  Scurrying Slippers -- attack strength: 13, defense strength: 3
                  -  Sacred Axe -- attack strength: 10
                  -  Cursed Knife -- attack strength: 6
                  -  Eternal Axe -- attack strength: 2
                  -  Scurrying Visors -- defense strength: 7
                  -  Scurrying Hat -- defense strength: 14
                  -  Scurrying Sneakers -- attack strength: 7, defense strength: 8
                  -  Mysterious Shield -- attack strength: 6
                  -  Ancient Hammer -- attack strength: 8
                  -  Cursed Gauntlet -- attack strength: 14
                  -  Happy Waders -- attack strength: 9, defense strength: 9
                  -  Infernal Axe -- attack strength: 8
                  -  Sacred Hoverboard -- attack strength: 7, defense strength: 5
                  -  Blessed Visors -- defense strength: 13
                """, newBattle1.toString());

        Assert.assertEquals("""
                Battle Information:
                Character 1 Name: Leo
                Character 2 Name: Tom
                List of Items:
                  -  Infernal Dagger -- attack strength: 2
                  -  Cursed Visors -- defense strength: 11
                  -  Scurrying Hoverwheels -- attack strength: 2, defense strength: 15
                  -  Cursed Boots -- attack strength: 15, defense strength: 9
                  -  Happy Hat -- defense strength: 5
                  -  Happy Crown -- defense strength: 10
                  -  Sacred Visors -- defense strength: 13
                  -  Blessed Scooter -- attack strength: 4, defense strength: 6
                  -  Mysterious Visors -- defense strength: 6
                  -  Dark Slippers -- attack strength: 4, defense strength: 6
                  -  Happy Hammer -- attack strength: 14
                  -  Blessed Scooter -- attack strength: 12, defense strength: 2
                  -  Mysterious Sandals -- attack strength: 3, defense strength: 1
                  -  Cursed Hoverwheels -- attack strength: 10, defense strength: 7
                  -  Scurrying Sandals -- attack strength: 3, defense strength: 10
                  -  Happy Shield -- attack strength: 5
                  -  Infernal Tiara -- defense strength: 1
                  -  Eternal Hover Shoes -- attack strength: 14, defense strength: 5
                  -  Ancient Sneakers -- attack strength: 6, defense strength: 5
                  -  Dark Gauntlet -- attack strength: 4
                """, newBattle2.toString());

        newBattle1.startBattle();

        //newBattle2.startBattle();
    }
}
