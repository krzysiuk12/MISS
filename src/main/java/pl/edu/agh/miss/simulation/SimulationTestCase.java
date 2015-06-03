package pl.edu.agh.miss.simulation;

import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class SimulationTestCase {

    private final Map map;
    private final Node source;
    private final Node destination;
    private final SimulationService simulationService;
    private final SimulationAlgorithm algorithm;
    private final double accidentProbability;
    private final int timePeriodInSeconds;
    private final int pathRecalculationInterval;

    public SimulationTestCase(Map map, Node source, Node destination, SimulationService simulationService, SimulationAlgorithm algorithm, double accidentProbability, int timePeriodInSeconds, int pathRecalculationInterval) {
        this.map = map;
        this.source = source;
        this.destination = destination;
        this.simulationService = simulationService;
        this.algorithm = algorithm;
        this.accidentProbability = accidentProbability;
        this.timePeriodInSeconds = timePeriodInSeconds;
        this.pathRecalculationInterval = pathRecalculationInterval;
    }

    public int getPathRecalculationInterval() {
        return pathRecalculationInterval;
    }

    public Map getMap() {
        return map;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public SimulationService getSimulationService() {
        return simulationService;
    }

    public SimulationAlgorithm getAlgorithm() {
        return algorithm;
    }

    public double getAccidentProbability() {
        return accidentProbability;
    }

    public int getTimePeriodInSeconds() {
        return timePeriodInSeconds;
    }
}
