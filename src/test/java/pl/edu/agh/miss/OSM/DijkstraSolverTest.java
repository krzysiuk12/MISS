package pl.edu.agh.miss.OSM;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.DistanceUnit;
import pl.edu.agh.miss.map.way.DistanceWeight;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayType;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.solver.dijkstra.DijkstraSolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class DijkstraSolverTest {

    private static Map map;
    private static DijkstraSolver dijkstraSolver;

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
        dijkstraSolver = new DijkstraSolver();
    }

    @Test
    public void testDijkstraSolverFirstTestCase() {
        Node start = map.getNodeByName("Redville");
        Node end = map.getNodeByName("Purpleville");
        Path path = dijkstraSolver.findPath(start, end, map);
        assertEquals(path.getNodes().size(), 3);
        assertEquals(path.getWays().size(), 2);
        assertEquals(path.getTotalCost(), 10.0, 0.0001);
        assertTrue(path.getNodes().contains(map.getNodeByName("Redville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Orangeville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Purpleville")));
    }

    @Test
    public void testDijkstraSolverSecondTestCase() {
        Node start = map.getNodeByName("Blueville");
        Node end = map.getNodeByName("Orangeville");
        Path path = dijkstraSolver.findPath(start, end, map);
        assertEquals(path.getNodes().size(), 3);
        assertEquals(path.getWays().size(), 2);
        assertEquals(path.getTotalCost(), 9.0, 0.0001);
        assertTrue(path.getNodes().contains(map.getNodeByName("Blueville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Purpleville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Orangeville")));
    }

    @Test
    public void testDijkstraSolverThirdTestCase() {
        Node start = map.getNodeByName("Greenville");
        Node end = map.getNodeByName("Orangeville");
        Path path = dijkstraSolver.findPath(start, end, map);
        assertEquals(path.getNodes().size(), 4);
        assertEquals(path.getWays().size(), 3);
        assertEquals(path.getTotalCost(), 12.0, 0.0001);
        assertTrue(path.getNodes().contains(map.getNodeByName("Greenville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Blueville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Purpleville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Orangeville")));
    }

    @Test
    public void testDijkstraSolverFourthTestCase() {
        Node start = map.getNodeByName("Blueville");
        Node end = map.getNodeByName("Purpleville");
        Path path = dijkstraSolver.findPath(start, end, map);
        assertEquals(path.getNodes().size(), 2);
        assertEquals(path.getWays().size(), 1);
        assertEquals(path.getTotalCost(), 7.0, 0.0001);
        assertTrue(path.getNodes().contains(map.getNodeByName("Blueville")));
        assertTrue(path.getNodes().contains(map.getNodeByName("Purpleville")));
    }

    @Test
    public void testDijkstraSolverFifthTestCase() {
        Node start = map.getNodeByName("Blueville");
        Node end = map.getNodeByName("Blueville");
        Path path = dijkstraSolver.findPath(start, end, map);
        assertEquals(path.getNodes().size(), 1);
        assertEquals(path.getWays().size(), 0);
        assertEquals(path.getTotalCost(), 0.0, 0.0001);
        assertTrue(path.getNodes().contains(map.getNodeByName("Blueville")));
    }

}
