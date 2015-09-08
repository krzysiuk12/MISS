package pl.edu.agh.miss.simulation;

import pl.edu.agh.miss.domain.way.Way;

import java.util.List;
import java.util.Map;

/**
 * Created by slonka on 03.06.15.
 */
public interface ITrafficSimulation {

    void simulateTraffic(int currentIteration);

    void simulateAccident(int currentIteration);

}
