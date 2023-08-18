package enums;

import java.util.Random;

public enum HandType {
    GLOVES("Gloves"),
    SWORD("Sword"),
    SHIELD("Shield"),
    DAGGER("Dagger"),
    AXE("Axe"),
    HAMMER("Hammer"),
    GAUNTLET("Gauntlet"),
    KNIFE("Knife");

    private final String type;

    HandType(String type) {
        this.type = type;
    }

    public static HandType getRandomHandType() {
        Random random = new Random();
        HandType[] handTypes = {
                GLOVES, SWORD, SHIELD, DAGGER, AXE,
                HAMMER, GAUNTLET, KNIFE
        };
        return handTypes[random.nextInt(handTypes.length)];
    }

    public static HandType getRandomHandType(Random random) {
        HandType[] handTypes = {
                GLOVES, SWORD, SHIELD, DAGGER, AXE,
                HAMMER, GAUNTLET, KNIFE
        };
        return handTypes[random.nextInt(handTypes.length)];
    }

    @Override
    public String toString() {
        return type;
    }
}
