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
import pl.edu.agh.miss.solver.gis.PostgisSolver;

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
    private ITrafficSimulation trafficSimulation;
    int pathRecalculated = 0;

    public Simulation(SimulationTestCase simulationTestCase, ITrafficSimulation trafficSimulation) {
        this.simulationTestCase = simulationTestCase;
        this.finalPath = createPath();
        this.trafficSimulation = trafficSimulation;
        pathRecalculated = simulationTestCase.getPathRecalculationInterval();
    }

    @Override
    public Path call() throws Exception {
        currentPosition = simulationTestCase.getSource();
        finalPath.getNodes().add(currentPosition);

        Path path = null;
        while (!currentPosition.equals(simulationTestCase.getDestination())) {
            ISolverService solverService = getSolverService();
            logger.info("Current position: " + currentPosition);
            logger.info("Solver created...");
            logger.info("Finding path from " + currentPosition + " to " + simulationTestCase.getDestination());

            if (pathRecalculated <= 0) {
                pathRecalculated = simulationTestCase.getPathRecalculationInterval();
            }

            if (pathRecalculated == simulationTestCase.getPathRecalculationInterval()) {
                solverService.findPath(currentPosition, simulationTestCase.getDestination());
//                Thread.sleep(simulationTestCase.getTimePeriodInSeconds() * 1000);
                logger.info("Getting path...");
                path = solverService.getPath();
            }
            pathRecalculated--;

            // FIXME: THIS THROWS NULL POINTER EXCEPTION WHEN THERE ARE NO RESULTS
            Way nextWay = path.getWays().get(0);
            solverService.updateWeight(nextWay);
            path.getWays().remove(0);
            currentPosition = nextWay.getDestination(currentPosition);
            addWayToPath(nextWay);
            logger.info("Next way: " + nextWay);

            trafficSimulation.simulateTraffic();
            trafficSimulation.simulateAccident();

            logger.info("--------------------------------------------------------------------------------------------");
        }
        return finalPath;
    }


    private Set<ISolver> getSolvers() {
        Set<ISolver> solvers = new HashSet<ISolver>();
        if (simulationTestCase.getSimulationService() == SimulationService.IMPLEMENTATION && simulationTestCase.getAlgorithm() == SimulationAlgorithm.DIJKSTRA) {
            solvers.add(new DijkstraSolver());
        }
        return solvers;
    }

    private ISolverService getSolverService() {
        if (simulationTestCase.getSimulationService() == SimulationService.IMPLEMENTATION) {
            return new MapSolverService(simulationTestCase.getMap(), getSolvers());
        } else if (simulationTestCase.getSimulationService() == SimulationService.POSTGIS) {
            return new PostgisSolver();
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
