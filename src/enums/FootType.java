package enums;

import java.util.Random;

public enum FootType {
    BOOTS("Boots"),
    SANDALS("Sandals"),
    SNEAKERS("Sneakers"),
    SLIPPERS("Slippers"),
    WADERS("Waders"),
    SCOOTER("Scooter"),
    HOVERWHEELS("Hoverwheels"),
    HOVER_SHOES("Hover Shoes"),
    HOVERBOARD("Hoverboard");

    private final String type;

    FootType(String type) {
        this.type = type;
    }

    public static FootType getRandomFootType() {
        Random random = new Random();
        FootType[] footTypes = {
                BOOTS, SANDALS, SNEAKERS, SLIPPERS,
                WADERS, SCOOTER, HOVERWHEELS, HOVER_SHOES, HOVERBOARD
        };
        return footTypes[random.nextInt(footTypes.length)];
    }

    public static FootType getRandomFootType(Random random) {
        FootType[] footTypes = {
                BOOTS, SANDALS, SNEAKERS, SLIPPERS,
                WADERS, SCOOTER, HOVERWHEELS, HOVER_SHOES, HOVERBOARD
        };
        return footTypes[random.nextInt(footTypes.length)];
    }

    @Override
    public String toString() {
        return type;
    }
}
