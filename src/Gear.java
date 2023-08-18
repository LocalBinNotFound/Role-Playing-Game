import enums.Adjective;

public interface Gear<T> extends Comparable<Gear<T>> {
    int getAttack();
    int getDefense();
    Adjective getAdjective();
    T getGearType();
    String getName();
    Gear<T> combineGear(Gear<T> other);
}
