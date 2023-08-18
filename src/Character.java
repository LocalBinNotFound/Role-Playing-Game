import java.util.*;

public class Character {
    private String name;
    private int playerAttack;
    private int playerDefense;
    private HeadGear headGear;
    private List<FootGear> footGears;
    private List<HandGear> handGears;

    private static final int initAttack = 0;
    private static final int initDefense = 0;

    public Character() {
        this("DefaultCharacter", initAttack, initDefense);
        this.headGear = null;
        this.footGears = new ArrayList<>();
        this.handGears = new ArrayList<>();
    }

    public Character(String name) {
        this(name, initAttack, initDefense);
        this.headGear = null;
        this.footGears = new ArrayList<>();
        this.handGears = new ArrayList<>();
    }

    public Character(String name, int playerAttack, int playerDefense) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Please enter a valid name!");
        if (playerAttack < 0) throw new IllegalArgumentException("Attack value cannot be negative!");
        if (playerDefense < 0) throw new IllegalArgumentException("Defense value cannot be negative!");
        this.name = name;
        this.playerAttack = playerAttack;
        this.playerDefense = playerDefense;
        this.headGear = null;
        this.footGears = new ArrayList<>();
        this.handGears = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlayerAttack() {
        return playerAttack;
    }

    public int getPlayerDefense() {
        return playerDefense;
    }

    public HeadGear getHeadGear() {
        return headGear;
    }

    public List<HandGear> getHandGears() {
        return handGears;
    }

    public List<FootGear> getFootGears() {
        return footGears;
    }

    // returns number of remaining gear slots
    public int gearSlot(String gearType) {
        return switch (gearType) {
            case "class enums.HeadType" -> headGear == null ? 1 : 0;
            case "class enums.HandType" -> 2 - handGears.size();
            case "class enums.FootType" -> 2 - footGears.size();
            default -> 0;
        };
    }

    public void pickupGear(Gear<?> gear) {
        if (gear instanceof HeadGear) {
            if (headGear == null) {
                headGear = (HeadGear) gear;
            } else {
                headGear = (HeadGear) headGear.combineGear((HeadGear) gear);
            }
            updateStrength(gear);
        } else if (gear instanceof FootGear) {
            if (footGears.size() < 2) {
                footGears.add((FootGear) gear);
            } else {
                Collections.sort(footGears);
                FootGear fgToBeCombined = footGears.get(0);
                fgToBeCombined = (FootGear) fgToBeCombined.combineGear((FootGear) gear);
                footGears.set(0, fgToBeCombined);
            }
            updateStrength(gear);
        } else if (gear instanceof HandGear) {
            if (handGears.size() < 2) {
                handGears.add((HandGear) gear);
            } else {
                Collections.sort(handGears);
                HandGear hgToBeCombined = handGears.get(0);
                hgToBeCombined = (HandGear) hgToBeCombined.combineGear((HandGear) gear);
                handGears.set(0, hgToBeCombined);
            }
            updateStrength(gear);
        } else {
            throw new IllegalArgumentException("Invalid gear type! Cannot pick up gear!");
        }
    }

    private void updateStrength(Gear<?> gear) {
        playerAttack += gear.getAttack();
        playerDefense += gear.getDefense();
    }

    public int calculateDamage(Character o) {
        return playerAttack - o.getPlayerDefense();
    }

    @Override
    public String toString() {
        return  "Name = " + name + "\n" +
                "Attack = " + playerAttack + "\n" +
                "Defense = " + playerDefense + "\n" +
                "HeadGear = " + headGear + "\n" +
                "FootGears = " + footGears + "\n" +
                "HandGears = " + handGears + "\n";
    }

    // helper method to print to a table form
    public String printToTable() {
        StringBuilder sb = new StringBuilder();

        String footGearsStr = (footGears.isEmpty() ? "" : footGears.get(0).getName()) + (footGears.size() > 1 ? " | " + footGears.get(1).getName() : "");
        String handGearsStr = handGears.isEmpty() ? "" : handGears.get(0).getName() + (handGears.size() > 1 ? " | " + handGears.get(1).getName() : "");
        sb.append(String.format("%-11s|%7d |%8d | %-41s | %-67s | %-57s\n", name, playerAttack, playerDefense,
                headGear != null ? headGear.getName() : "", footGearsStr, handGearsStr));
        return sb.toString();
    }
}
