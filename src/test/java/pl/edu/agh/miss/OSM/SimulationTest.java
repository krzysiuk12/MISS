package pl.edu.agh.miss.OSM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.DistanceUnit;
import pl.edu.agh.miss.map.way.DistanceWeight;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayType;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.simulation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
@RunWith(Parameterized.class)
public class SimulationTest {

    private int pathRecalculationInterval = 0;

    public SimulationTest(int pathRecalculationInterval) {
        this.pathRecalculationInterval = pathRecalculationInterval;
    }
    private static Map map;
    private Logger logger = LogManager.getLogger(SimulationTest.class.getName());

    @BeforeClass
    public static void beforeClass() {
        Node redville = new Node("Redville", 1.0, 1.0);
        Node orangeville = new Node("Orangeville", 1.0, 1.0);
        Node purpleville = new Node("Purpleville", 1.0, 1.0);
        Node blueville = new Node("Blueville", 1.0, 1.0);
        Node greenville = new Node("Greenville", 1.0, 1.0);

        Way redvilleToOrangeville = new Way(redville, orangeville, WayType.TWO_WAY, new DistanceWeight(8.0, DistanceUnit.KILOMETRES));
        Way purplevilleToOrangeville = new Way(orangeville, purpleville, WayType.TWO_WAY, new DistanceWeight(2.0, DistanceUnit.KILOMETRES));
        Way bluevilleToPurpleville = new Way(purpleville, blueville, WayType.TWO_WAY, new DistanceWeight(7.0, DistanceUnit.KILOMETRES));
        Way bluevilleToRedville = new Way(redville, blueville, WayType.TWO_WAY, new DistanceWeight(5.0, DistanceUnit.KILOMETRES));
        Way bluevilleToGreenville = new Way(greenville, blueville, WayType.TWO_WAY, new DistanceWeight(3.0, DistanceUnit.KILOMETRES));
        Way redvilleToGreenville = new Way(greenville, redville, WayType.TWO_WAY, new DistanceWeight(10.0, DistanceUnit.KILOMETRES));

        Set<Node> nodes = new HashSet<Node>(Arrays.asList(redville, orangeville, purpleville, blueville, greenville));
        Set<Way> ways = new HashSet<Way>(Arrays.asList(redvilleToOrangeville, purplevilleToOrangeville, bluevilleToGreenville, bluevilleToPurpleville, bluevilleToRedville, redvilleToGreenville));

        map = new Map("Test Map One", nodes, ways);
    }

    @Parameterized.Parameters
    public static Collection recalculationIntervals() {
        return Arrays.asList(
                1,
                2,
                3,
                4
        );
    }
    @Test
    public void simulationSecondTest() throws Exception {
        Node redville = new Node("Redville", 1.0, 1.0);
        Node orangeville = new Node("Orangeville", 1.0, 1.0);
        Node purpleville = new Node("Purpleville", 1.0, 1.0);
        Node blueville = new Node("Blueville", 1.0, 1.0);
        Node greenville = new Node("Greenville", 1.0, 1.0);

        // Way redvilleToOrangeville = new Way(redville, orangeville, WayType.TWO_WAY, new DistanceWeight(8.0, DistanceUnit.KILOMETRES));
        Way purplevilleToOrangeville = new Way(orangeville, purpleville, WayType.TWO_WAY, new DistanceWeight(2.0, DistanceUnit.KILOMETRES));
        Way bluevilleToPurpleville = new Way(purpleville, blueville, WayType.TWO_WAY, new DistanceWeight(7.0, DistanceUnit.KILOMETRES));
        Way bluevilleToRedville = new Way(redville, blueville, WayType.TWO_WAY, new DistanceWeight(5.0, DistanceUnit.KILOMETRES));
        // Way bluevilleToGreenville = new Way(greenville, blueville, WayType.TWO_WAY, new DistanceWeight(3.0, DistanceUnit.KILOMETRES));
        Way redvilleToGreenville = new Way(greenville, redville, WayType.TWO_WAY, new DistanceWeight(10.0, DistanceUnit.KILOMETRES));

        Set<Node> nodes = new HashSet<Node>(Arrays.asList(redville, orangeville, purpleville, blueville, greenville));
        Set<Way> ways = new HashSet<Way>(Arrays.asList(purplevilleToOrangeville, bluevilleToPurpleville, bluevilleToRedville, redvilleToGreenville));

        Map map = new Map("Test Map One", nodes, ways);

        SimulationTestCase simulationTestCase = new SimulationTestCase(map, map.getNodeByName("Orangeville"), map.getNodeByName("Greenville"), SimulationService.IMPLEMENTATION, SimulationAlgorithm.DIJKSTRA, 0.05, 2, pathRecalculationInterval);
        TrafficSimulation trafficSimulation = new TrafficSimulation(simulationTestCase);
        Simulation simulation = new Simulation(simulationTestCase, trafficSimulation);
        Path path = simulation.call();
        System.out.println(pathRecalculationInterval + " " + path);
    }


}
