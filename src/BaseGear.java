import enums.*;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGear<T> implements Gear<T> {
    private final Adjective adjective;
    private final T gearType;
    private final int attack;
    private final int defense;

    public BaseGear(Adjective adjective, T gearType, int attack, int defense) {
        if (attack < 0) throw new IllegalArgumentException("Attack value cannot be negative!");
        if (defense < 0) throw new IllegalArgumentException("Defense value cannot be negative!");
        this.adjective = adjective;
        this.gearType = gearType;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public String getName() {
        return adjective + " " + gearType;
    }

    @Override
    public T getGearType() {
        return gearType;
    }

    @Override
    public Adjective getAdjective() {
        return adjective;
    }

    public abstract Gear<T> createNewGear(Adjective adjective, T gearType, int attack, int defense);

    @Override
    public Gear<T> combineGear(Gear<T> other) {
        T combinedGearType = other.getGearType();

        List<String> combinedAdjList = new ArrayList<>();
        combinedAdjList.addAll(getAdjective().getAdjList());
        combinedAdjList.addAll(other.getAdjective().getAdjList());
        Adjective combinedAdjective = Adjective.combined(combinedAdjList);

        int combinedAttack = getAttack() + other.getAttack();
        int combinedDefense = getDefense() + other.getDefense();

        return createNewGear(combinedAdjective, combinedGearType, combinedAttack, combinedDefense);
    }

    @Override
    // compare number of adjectives first, then adjective alphabetically, then gear type alphabetically
    public int compareTo(Gear<T> o) {
        int adjNum = Integer.compare(this.adjective.getAdjList().size(), ((BaseGear<T>) o).adjective.getAdjList().size());
        if (adjNum != 0) return adjNum;
        else {
            int adjComparison = this.adjective.toString().compareTo(((BaseGear<T>) o).adjective.toString());
            if (adjComparison != 0) {
                return adjComparison;
            } else {
                return this.gearType.toString().compareTo(((BaseGear<T>) o).gearType.toString());
            }
        }
    }

}
