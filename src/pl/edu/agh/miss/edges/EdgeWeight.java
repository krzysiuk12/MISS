package pl.edu.agh.miss.edges;

import java.util.concurrent.TimeUnit;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class EdgeWeight {

    private final double distance;
    private final DistanceUnit distanceUnit;
    private double duration;
    private TimeUnit durationUnit;

    public EdgeWeight(double distance, DistanceUnit distanceUnit, double duration, TimeUnit durationUnit) {
        this.distance = distance;
        this.distanceUnit = distanceUnit;
        this.duration = duration;
        this.durationUnit = durationUnit;
    }

    public double getDistance() {
        return distance;
    }

    public DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public TimeUnit getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(TimeUnit durationUnit) {
        this.durationUnit = durationUnit;
    }

}
