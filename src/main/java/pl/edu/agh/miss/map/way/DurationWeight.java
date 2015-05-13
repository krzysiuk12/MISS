package pl.edu.agh.miss.map.way;

import java.util.concurrent.TimeUnit;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class DurationWeight extends WayWeight {

    private TimeUnit unit;

    public DurationWeight() {
    }

    public DurationWeight(Double weight, TimeUnit unit) {
        super(weight);
        this.unit = unit;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "DurationWeight{" +
                "weight=" + weight +
                ", unit='" + unit +
                '}';
    }
}
