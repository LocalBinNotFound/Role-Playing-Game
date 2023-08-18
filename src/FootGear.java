import enums.Adjective;
import enums.FootType;

public class FootGear extends BaseGear<FootType> {
    public FootGear(Adjective adjective, FootType footType, int attack, int defense) {
        super(adjective, footType, attack, defense);
    }

    @Override
    public Gear<FootType> createNewGear(Adjective adjective, FootType gearType, int attack, int defense) {
        return new FootGear(adjective, gearType, attack, defense);
    }

    @Override
    public String toString() {
        return getName() + " -- attack strength: " + getAttack() + ", defense strength: " + getDefense();
    }

}
