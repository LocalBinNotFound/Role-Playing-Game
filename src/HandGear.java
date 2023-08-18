import enums.Adjective;
import enums.HandType;

public class HandGear extends BaseGear<HandType> {
    public HandGear(Adjective adjective, HandType handType, int attack) {
        super(adjective, handType, attack, 0);
    }

    public HandGear(Adjective adjective, HandType handType, int attack, int defense) {
        super(adjective, handType, attack, defense);
    }

    @Override
    public Gear<HandType> createNewGear(Adjective adjective, HandType gearType, int attack, int defense) {
        if (defense == 0) {
            return new HandGear(adjective, gearType, attack);
        } else {
            return new HandGear(adjective, gearType, attack, defense);
        }
    }

    @Override
    public String toString() {
        if (getDefense() == 0) {
            return getName() + " -- attack strength: " + getAttack();
        } else {
            return getName() + " -- attack strength: " + getAttack() + ", defense strength: " + getDefense();
        }
    }
}
