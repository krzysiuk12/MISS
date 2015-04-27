package pl.edu.agh.miss.vertices;

import java.util.Random;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class TramStop extends Vertex {

    private int waitingPeople;
    private double timePerPerson;

    public TramStop(String name, int waitingPeople) {
        super(name);
        this.waitingPeople = waitingPeople;
        this.timePerPerson = new Random().nextDouble();
    }

    @Override
    public double getWaitingTime() {
        return waitingPeople * timePerPerson;
    }
}
