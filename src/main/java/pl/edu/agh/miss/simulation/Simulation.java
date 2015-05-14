package pl.edu.agh.miss.simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayWeight;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.solver.ISolver;
import pl.edu.agh.miss.solver.ISolverService;
import pl.edu.agh.miss.solver.MapSolverService;
import pl.edu.agh.miss.solver.dijkstra.DijkstraSolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class Simulation implements Callable<Path> {

    private Logger logger = LogManager.getLogger(Simulation.class.getSimpleName());
    private ThreadLocalRandom random = ThreadLocalRandom.current();
    private SimulationTestCase simulationTestCase;
    private Node currentPosition;
    private Path finalPath;

    public Simulation(SimulationTestCase simulationTestCase) {
        this.simulationTestCase = simulationTestCase;
        this.finalPath = createPath();
    }

    @Override
    public Path call() throws Exception {
        currentPosition = simulationTestCase.getSource();
        finalPath.getNodes().add(currentPosition);

        while(!currentPosition.equals(simulationTestCase.getDestination())) {
            logger.info("Current position: " + currentPosition);
            ISolverService solverService = getSolverService();
            logger.info("Solver created...");
            logger.info("Finding path from " + currentPosition + " to " + simulationTestCase.getDestination());
            solverService.findPath(currentPosition, simulationTestCase.getDestination());
            Thread.sleep(simulationTestCase.getTimePeriodInSeconds() * 1000);
            logger.info("Getting path...");

            Path path = solverService.getPath();
            Way nextWay = path.getWays().get(0);
            currentPosition = nextWay.getDestination(currentPosition);
            addWayToPath(nextWay);
            logger.info("Next way: " + nextWay);

            logger.info("Simulate accident...");
            if(Double.compare(random.nextDouble(), simulationTestCase.getAccidentProbability()) <= 0) {
                Map map = simulationTestCase.getMap();
                Way unavailableWay = new ArrayList<>(map.getWays()).get(random.nextInt(map.getWays().size()));
                logger.info("Accident happened on way: " + unavailableWay);
                map.getWays().remove(unavailableWay);
            } else {
                logger.info("No accident happened.");
            }

            logger.info("Simulate traffic...");
            for(Way way : simulationTestCase.getMap().getWays()) {
                WayWeight wayWeight = way.getWeight();
                wayWeight.setWeight(wayWeight.getWeight() * (random.nextDouble(0.3) + 0.7));
                logger.info("For way " + way + " new way weight " + wayWeight.getWeight());
            }

            logger.info("--------------------------------------------------------------------------------------------");
        }
        return finalPath;
    }

    private Set<ISolver> getSolvers() {
        Set<ISolver> solvers = new HashSet<ISolver>();
        if(simulationTestCase.getSimulationService() == SimulationService.IMPLEMENTATION && simulationTestCase.getAlgorithm() == SimulationAlgorithm.DIJKSTRA) {
            solvers.add(new DijkstraSolver());
        }
        return solvers;
    }

    private ISolverService getSolverService() {
        if(simulationTestCase.getSimulationService() == SimulationService.IMPLEMENTATION) {
            return new MapSolverService(simulationTestCase.getMap(), getSolvers());
        }
        return null;
    }

    private Path createPath() {
        Path path = new Path();
        path.setNodes(new ArrayList<>());
        path.setWays(new ArrayList<>());
        path.setTotalCost(0.0);
        return path;
    }

    private void addWayToPath(Way way) {
        finalPath.getNodes().add(currentPosition);
        finalPath.getWays().add(way);
        finalPath.setTotalCost(finalPath.getTotalCost() + way.getWeight().getWeight());
    }
}
