package pl.edu.agh.miss.OSM;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.DistanceUnit;
import pl.edu.agh.miss.map.way.DistanceWeight;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayType;
import pl.edu.agh.miss.solver.dijkstra.DijkstraSolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class DijkstraSolverTest {

    private static Map map;

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

    @Test
    public void testDijkstraSolver() {
        DijkstraSolver dijkstraSolver = new DijkstraSolver();
        Node start = map.getNodeByName("Redville");
        Node end = map.getNodeByName("Purpleville");
        List<Node> nodes = dijkstraSolver.findPath(start, end, map);
        System.out.println(nodes);
    }

}
