package pl.edu.agh.miss.domain.way;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class DistanceWeight extends WayWeight {

    private DistanceUnit unit;

    public DistanceWeight() {
    }

    public DistanceWeight(Double weight, DistanceUnit unit) {
        super(weight);
        this.unit = unit;
    }

    public DistanceUnit getUnit() {
        return unit;
    }

    public void setUnit(DistanceUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "DistanceWeight{" +
                "weight=" + weight +
                ", unit='" + unit +
                '}';
    }

}
