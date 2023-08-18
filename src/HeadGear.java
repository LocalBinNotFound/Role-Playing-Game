import enums.Adjective;
import enums.HeadType;

public class HeadGear extends BaseGear<HeadType> {
    public HeadGear(Adjective adjective, HeadType headType, int defense) {
        super(adjective, headType, 0, defense);
    }

    @Override
    public String toString() {
        return getName() + " -- defense strength: " + getDefense();
    }

    @Override
    public Gear<HeadType> createNewGear(Adjective adjective, HeadType gearType, int attack, int defense) {
        return new HeadGear(adjective, gearType, defense);
    }

}