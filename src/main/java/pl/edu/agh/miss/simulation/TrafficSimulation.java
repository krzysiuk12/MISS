package pl.edu.agh.miss.simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.miss.domain.Map;
import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.domain.way.WayWeight;
import pl.edu.agh.miss.simulation.testCases.MapSimulationTestCase;
import pl.edu.agh.miss.simulation.testCases.SimulationTestCase;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by slonka on 03.06.15.
 */
public class TrafficSimulation implements ITrafficSimulation {
    private Logger logger = LogManager.getLogger(Simulation.class.getSimpleName());
    private SimulationTestCase simulationTestCase;
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public TrafficSimulation(SimulationTestCase simulationTestCase) {
        this.simulationTestCase = simulationTestCase;
    }

    @Override
    public void simulateTraffic(int currentIteration) {
        logger.info("Simulate traffic...");
        for(Way way : ((MapSimulationTestCase)simulationTestCase).getMap().getWays()) {
            WayWeight wayWeight = way.getWeight();
            wayWeight.setWeight(wayWeight.getWeight() * (random.nextDouble(0.3) + 0.7));
            logger.info("For way " + way + " new way weight " + wayWeight.getWeight());
        }
    }

    @Override
    public void simulateAccident(int currentIteration) {
        logger.info("Simulate accident...");
        if(Double.compare(random.nextDouble(), simulationTestCase.getAccidentProbability()) <= 0) {
            Map map = ((MapSimulationTestCase)simulationTestCase).getMap();
            Way unavailableWay = new ArrayList<>(map.getWays()).get(random.nextInt(map.getWays().size()));
            logger.info("Accident happened on way: " + unavailableWay);
            map.getWays().remove(unavailableWay);
        } else {
            logger.info("No accident happened.");
        }
    }
}
