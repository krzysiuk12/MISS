package pl.edu.agh.miss.domain.way;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class WayWeight {

    protected double weight;

    public WayWeight() {
    }

    public WayWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return Double.toString(weight);
    }
}
