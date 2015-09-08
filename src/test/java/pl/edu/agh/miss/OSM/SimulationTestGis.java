package pl.edu.agh.miss.OSM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.domain.way.WayWeight;
import pl.edu.agh.miss.gis.JavaGisDao;
import pl.edu.agh.miss.gis.TrafficSimulationGis;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.Path;
import pl.edu.agh.miss.simulation.*;
import pl.edu.agh.miss.simulation.testCases.SimulationTestCase;

import java.util.*;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
@RunWith(Parameterized.class)
public class SimulationTestGis {

    private static Simulation simulation;
    private Logger logger = LogManager.getLogger(SimulationTestGis.class.getName());
    int pathRecalculationInterval = 10;

    public SimulationTestGis(int pathRecalculationInterval) {
        this.pathRecalculationInterval = pathRecalculationInterval;
    }

    @Parameterized.Parameters
    public static Collection recalculationIntervals() {
        return Arrays.asList(
//                1,
//                2,
//                3,
//                4,
//                5,
//                6,
//                7,
//                8,
//                9,
                10
        );
    }

    @Before
    public void before() throws Exception {
        String dbname = "rynek_database";
        String username = "krzysztofkicinger";
        String password = "postgres";
        JavaGisDao gisDao = JavaGisDao.getInstance(dbname, username, password);
        gisDao.refreshWays();
        SimulationTestCase testCase = new SimulationTestCase(new Node(196L), new Node(579L), SimulationService.POSTGIS, SimulationAlgorithm.DIJKSTRA, 0.0, 1, pathRecalculationInterval);
        simulation = new Simulation(testCase, new TrafficSimulationGis(getTraffic(), new HashMap<>()));
    }

    @Test
    public void simulationFirstTest() throws Exception {
        //SimulationTestCase simulationTestCase = new SimulationTestCase(startNode, endNode, SimulationService.POSTGIS, SimulationAlgorithm.DIJKSTRA, 0.05, 2, pathRecalculationInterval);
        //TrafficSimulationGis trafficSimulation = new TrafficSimulationGis(null, null);
        //Simulation simulation = new Simulation(simulationTestCase, trafficSimulation);
        Path path = simulation.call();
        System.out.println(pathRecalculationInterval + ":\n " + path + "\n\n");
    }

    private static Map<Integer, List<Way>> getTraffic() {
        return new HashMap<Integer, List<Way>>() {{
            put(4, Arrays.asList(new Way(560L, new WayWeight(JavaGisDao.getCost(560L) * 10000))));
            put(19, Arrays.asList(new Way(717L, new WayWeight(JavaGisDao.getCost(717L) * 3))));
//            put(18, Arrays.asList(new Way(18L, new WayWeight(JavaGisDao.getCost(18L) * 3))));
//            put(26, Arrays.asList(new Way(2152L, new WayWeight(JavaGisDao.getCost(2152L) * 2))));
        }};
    }

}
