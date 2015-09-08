package pl.edu.agh.miss.simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.miss.domain.Map;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.domain.Path;
import pl.edu.agh.miss.simulation.testCases.MapSimulationTestCase;
import pl.edu.agh.miss.simulation.testCases.SimulationTestCase;
import pl.edu.agh.miss.solver.AbstractSolverService;
import pl.edu.agh.miss.solver.map.MapSolverService;
import pl.edu.agh.miss.solver.dijkstra.DijkstraSolver;
import pl.edu.agh.miss.solver.gis.PostgisSolverService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
    private ITrafficSimulation trafficSimulation;
    int pathRecalculated = 0;

    public Simulation(SimulationTestCase simulationTestCase, ITrafficSimulation trafficSimulation) {
        this.simulationTestCase = simulationTestCase;
        this.finalPath = createPath();
        this.trafficSimulation = trafficSimulation;
        pathRecalculated = 0;
    }

    @Override
    public Path call() throws Exception {
        currentPosition = simulationTestCase.getSource();
        finalPath.getNodes().add(currentPosition);

        Path path = null;
        int iterationNumber = 1;
        while (!currentPosition.equals(simulationTestCase.getDestination())) {
            AbstractSolverService solverService = getSolverService();
            logger.debug("Current position: " + currentPosition);
            logger.debug("Solver created...");
            logger.debug("Finding path from " + currentPosition + " to " + simulationTestCase.getDestination());

            if ((pathRecalculated % simulationTestCase.getPathRecalculationInterval()) == 0) {
                solverService.findPath(currentPosition, simulationTestCase.getDestination());
                Thread.sleep(simulationTestCase.getTimePeriodInSeconds() * 1000);
                logger.info("Getting path...");
                path = solverService.getPath();
            }
            pathRecalculated++;


            if(path != null && path.getWays() != null && !path.getWays().isEmpty()) {
                Way nextWay = path.getWays().get(0);
                solverService.updateWayInformation(nextWay);
                path.getWays().remove(0);
                currentPosition = nextWay.getDestination(currentPosition);
                addWayToPath(nextWay);
                logger.info("Next way: " + nextWay);
                logger.debug("--------------------------------------------------------------------------------------------");
            } else {
                logger.debug("PATH CANNOT BE FOUND!!!");
            }
            trafficSimulation.simulateTraffic(iterationNumber);
            trafficSimulation.simulateAccident(iterationNumber);
            iterationNumber++;
        }
        return finalPath;
    }

    private AbstractSolverService getSolverService() {
        if (simulationTestCase.getSimulationService() == SimulationService.IMPLEMENTATION) {
            Map map = ((MapSimulationTestCase)simulationTestCase).getMap();
            return new MapSolverService(map, new HashSet<>(Arrays.asList(new DijkstraSolver(map))));
        } else if (simulationTestCase.getSimulationService() == SimulationService.POSTGIS) {
            return new PostgisSolverService();
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
