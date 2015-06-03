package pl.edu.agh.miss.OSM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.miss.gis.TrafficSimulationGis;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.DistanceUnit;
import pl.edu.agh.miss.map.way.DistanceWeight;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayType;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.simulation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class SimulationTestGis {

    private static Map map;
    private static Node startNode;
    private static Node endNode;
    private Logger logger = LogManager.getLogger(SimulationTestGis.class.getName());

    @BeforeClass
    public static void beforeClass() {
        startNode = new Node(103, 50.0619085, 19.9324703);
        endNode = new Node(943, 50.0612428, 19.9326939);
        map = new Map("Test Map One", null, null);
    }

    @Test
    public void simulationFirstTest() throws Exception {
        SimulationTestCase simulationTestCase = new SimulationTestCase(map, startNode, endNode, SimulationService.POSTGIS, SimulationAlgorithm.DIJKSTRA, 0.05, 2);
        TrafficSimulationGis trafficSimulation = new TrafficSimulationGis();
        Simulation simulation = new Simulation(simulationTestCase, trafficSimulation);
        Path path = simulation.call();
        logger.info("Final path: " + path);
    }
}
