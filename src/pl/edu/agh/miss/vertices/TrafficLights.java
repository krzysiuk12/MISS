package pl.edu.agh.miss.vertices;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class TrafficLights extends Vertex {

    private double changeTime;

    public TrafficLights(String name, double changeTime) {
        super(name);
        this.changeTime = changeTime;
    }

    @Override
    public double getWaitingTime() {
        return changeTime;
    }

}
