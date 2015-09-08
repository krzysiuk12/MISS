package pl.edu.agh.miss.simulation.testCases;

import pl.edu.agh.miss.domain.Map;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.simulation.SimulationAlgorithm;
import pl.edu.agh.miss.simulation.SimulationService;

/**
 * Created by krzysztofkicinger on 9/7/15.
 */
public class MapSimulationTestCase extends SimulationTestCase {

    private final Map map;

    public MapSimulationTestCase(Map map, Node source, Node destination, SimulationService simulationService, SimulationAlgorithm algorithm, double accidentProbability, int timePeriodInSeconds, int pathRecalculationInterval) {
        super(source, destination, simulationService, algorithm, accidentProbability, timePeriodInSeconds, pathRecalculationInterval);
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

}
