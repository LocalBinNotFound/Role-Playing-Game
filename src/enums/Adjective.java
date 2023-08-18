package enums;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

// enum-like class that has fixed Adjectives and allow for new instances of Adjective
public class Adjective {
    public static final Adjective HAPPY = new Adjective("Happy");
    public static final Adjective SCURRYING = new Adjective("Scurrying");
    public static final Adjective SACRED = new Adjective("Sacred");
    public static final Adjective DARK = new Adjective("Dark");
    public static final Adjective MYSTERIOUS = new Adjective("Mysterious");
    public static final Adjective ANCIENT = new Adjective("Ancient");
    public static final Adjective ETERNAL = new Adjective("Eternal");
    public static final Adjective INFERNAL = new Adjective("Infernal");
    public static final Adjective CURSED = new Adjective("Cursed");
    public static final Adjective BLESSED = new Adjective("Blessed");

    private final List<String> adjectives;
    private final boolean combined;

    private Adjective(String... adjectives) {
        this.adjectives = Arrays.asList(adjectives);
        this.combined = false;
    }

    private Adjective(List<String> adjectives) {
        this.adjectives = adjectives;
        this.combined = true;
    }

    public static Adjective single(String adjective) {
        return new Adjective(adjective);
    }

    public static Adjective combined(List<String> adjectives) {
        return new Adjective(adjectives);
    }

    public List<String> getAdjList() {
        return adjectives;
    }

    public static Adjective getRandomAdjective() {
        Random random = new Random();
        Adjective[] adjectives = {
                HAPPY, SCURRYING, SACRED, DARK, MYSTERIOUS,
                ANCIENT, ETERNAL, INFERNAL, CURSED, BLESSED
        };
        return adjectives[random.nextInt(adjectives.length)];
    }

    public static Adjective getRandomAdjective(Random random) {
        Adjective[] adjectives = {
                HAPPY, SCURRYING, SACRED, DARK, MYSTERIOUS,
                ANCIENT, ETERNAL, INFERNAL, CURSED, BLESSED
        };
        return adjectives[random.nextInt(adjectives.length)];
    }

    @Override
    public String toString() {
        if (combined) {
            return String.join(", ", adjectives);
        } else {
            return adjectives.get(0);
        }
    }
}