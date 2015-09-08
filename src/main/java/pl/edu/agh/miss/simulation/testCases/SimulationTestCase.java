package pl.edu.agh.miss.simulation.testCases;

import pl.edu.agh.miss.domain.Map;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.simulation.SimulationAlgorithm;
import pl.edu.agh.miss.simulation.SimulationService;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class SimulationTestCase {

    private final Node source;
    private final Node destination;
    private final SimulationService simulationService;
    private final SimulationAlgorithm algorithm;
    private final double accidentProbability;
    private final int timePeriodInSeconds;
    private final int pathRecalculationInterval;

    public SimulationTestCase(Node source, Node destination, SimulationService simulationService, SimulationAlgorithm algorithm, double accidentProbability, int timePeriodInSeconds, int pathRecalculationInterval) {
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
