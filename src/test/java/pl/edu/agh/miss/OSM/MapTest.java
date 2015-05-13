package pl.edu.agh.miss.OSM;

import org.junit.Test;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.DistanceUnit;
import pl.edu.agh.miss.map.way.DistanceWeight;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class MapTest {

    @Test
    public void testTwoWayMapCreation() {
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

        Map map = new Map("Test Map One", nodes, ways);

        assertEquals(redvilleToOrangeville, map.getWay(redville, orangeville));
        assertEquals(redvilleToOrangeville, map.getWay(orangeville, redville));

        assertNull(map.getWay(redville, purpleville));

        assertEquals(purplevilleToOrangeville, map.getWay(orangeville, purpleville));
        assertEquals(purplevilleToOrangeville, map.getWay(purpleville, orangeville));

        assertEquals(bluevilleToPurpleville, map.getWay(blueville, purpleville));
        assertEquals(bluevilleToPurpleville, map.getWay(purpleville, blueville));

        assertEquals(bluevilleToRedville, map.getWay(redville, blueville));
        assertEquals(bluevilleToRedville, map.getWay(blueville, redville));

        assertEquals(bluevilleToGreenville, map.getWay(greenville, blueville));
        assertEquals(bluevilleToGreenville, map.getWay(blueville, greenville));

        assertEquals(redvilleToGreenville, map.getWay(redville, greenville));
        assertEquals(redvilleToGreenville, map.getWay(greenville, redville));
    }

}
