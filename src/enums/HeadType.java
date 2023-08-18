package enums;

import java.util.Random;

public enum HeadType {
    HAT("Hat"),
    HELMET("Helmet"),
    VISORS("Visors"),
    CROWN("Crown"),
    TIARA("Tiara"),
    BANDANNA("Bandanna"),
    BEANIE("Beanie");

    private final String type;

    HeadType(String type) {
        this.type = type;
    }

    public static HeadType getRandomHeadType() {
        Random random = new Random();
        HeadType[] headTypes = {
                HAT, HELMET, VISORS, CROWN, TIARA, BANDANNA, BEANIE
        };
        return headTypes[random.nextInt(headTypes.length)];
    }

    public static HeadType getRandomHeadType(Random random) {
        HeadType[] headTypes = {
                HAT, HELMET, VISORS, CROWN, TIARA, BANDANNA, BEANIE
        };
        return headTypes[random.nextInt(headTypes.length)];
    }

    @Override
    public String toString() {
        return type;
    }
}
