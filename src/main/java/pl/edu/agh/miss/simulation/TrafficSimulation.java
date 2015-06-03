package pl.edu.agh.miss.simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayWeight;

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
    public void simulateTraffic() {
        logger.info("Simulate traffic...");
        for(Way way : simulationTestCase.getMap().getWays()) {
            WayWeight wayWeight = way.getWeight();
            wayWeight.setWeight(wayWeight.getWeight() * (random.nextDouble(0.3) + 0.7));
            logger.info("For way " + way + " new way weight " + wayWeight.getWeight());
        }
    }
}
