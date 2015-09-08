package pl.edu.agh.miss.OSM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.Path;
import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.domain.way.WayWeight;
import pl.edu.agh.miss.gis.JavaGisDao;
import pl.edu.agh.miss.gis.TrafficSimulationGis;
import pl.edu.agh.miss.simulation.Simulation;
import pl.edu.agh.miss.simulation.SimulationAlgorithm;
import pl.edu.agh.miss.simulation.SimulationService;
import pl.edu.agh.miss.simulation.testCases.SimulationTestCase;

import java.util.*;

/**
 * Created by krzysztofkicinger on 9/8/15.
 */
@RunWith(Parameterized.class)
public class GoogleSimulationTestGis {

    private static Simulation simulation;
    private Logger logger = LogManager.getLogger(SimulationTestGis.class.getName());
    int pathRecalculationInterval = 10;

    public GoogleSimulationTestGis(int pathRecalculationInterval) {
        this.pathRecalculationInterval = pathRecalculationInterval;
    }

    @Parameterized.Parameters
    public static Collection recalculationIntervals() {
        return Arrays.asList(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
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
        simulation = new Simulation(testCase, new TrafficSimulationGis(getTraffic(), getAccidents()));
    }

    @Test
    public void simulationFirstTest() throws Exception {
        //SimulationTestCase simulationTestCase = new SimulationTestCase(startNode, endNode, SimulationService.POSTGIS, SimulationAlgorithm.DIJKSTRA, 0.05, 2, pathRecalculationInterval);
        //TrafficSimulationGis trafficSimulation = new TrafficSimulationGis(null, null);
        //Simulation simulation = new Simulation(simulationTestCase, trafficSimulation);
        Path path = simulation.call();
        System.out.println(path);
    }

    private static Map<Integer, List<Way>> getTraffic() {
        return new HashMap<Integer, List<Way>>() {{
            put(1, Arrays.asList(
                    new Way(235L, new WayWeight(1.23)),
                    new Way(236L, new WayWeight(1.21)),
                    new Way(237L, new WayWeight(1.21)),
                    new Way(238L, new WayWeight(1.24)),
                    new Way(239L, new WayWeight(1.22)),
                    new Way(25L, new WayWeight(1.23)),
                    new Way(26L, new WayWeight(1.24)),
                    new Way(299L, new WayWeight(1.23)),
                    new Way(210L, new WayWeight(1.24)),
                    new Way(211L, new WayWeight(1.21)),
                    new Way(795L, new WayWeight(1.23)),
                    new Way(794L, new WayWeight(1.21))));
            put(2, Arrays.asList(
                    new Way(235L, new WayWeight(1.16)),
                    new Way(236L, new WayWeight(1.16)),
                    new Way(237L, new WayWeight(1.15)),
                    new Way(238L, new WayWeight(1.19)),
                    new Way(239L, new WayWeight(1.18)),
                    new Way(25L, new WayWeight(1.18)),
                    new Way(26L, new WayWeight(1.19)),
                    new Way(299L, new WayWeight(1.16)),
                    new Way(210L, new WayWeight(1.18)),
                    new Way(211L, new WayWeight(1.16)),
                    new Way(795L, new WayWeight(1.17)),
                    new Way(794L, new WayWeight(1.18))));
            put(3, Arrays.asList(
                    new Way( 235L, new WayWeight(1.03)),
                    new Way( 236L, new WayWeight(1.03)),
                    new Way( 237L, new WayWeight(1.01)),
                    new Way( 238L, new WayWeight(1.03)),
                    new Way( 239L, new WayWeight(1.02)),
                    new Way( 25L, new WayWeight(1.0)),
                    new Way( 26L, new WayWeight(1.02)),
                    new Way( 299L, new WayWeight(1.02)),
                    new Way( 210L, new WayWeight(1.04)),
                    new Way( 211L, new WayWeight(1.04)),
                    new Way( 795L, new WayWeight(1.03)),
                    new Way( 794L, new WayWeight(1.04))));
            put(4, Arrays.asList(
                    new Way( 235L, new WayWeight(1.0)),
                    new Way( 236L, new WayWeight(1.0)),
                    new Way( 237L, new WayWeight(1.0)),
                    new Way( 238L, new WayWeight(1.0)),
                    new Way( 239L, new WayWeight(1.0)),
                    new Way( 25L, new WayWeight(1.0)),
                    new Way( 26L, new WayWeight(1.0)),
                    new Way( 299L, new WayWeight(1.0)),
                    new Way( 210L, new WayWeight(1.0)),
                    new Way( 211L, new WayWeight(1.0)),
                    new Way( 795L, new WayWeight(1.0)),
                    new Way( 794L, new WayWeight(1.0))));
            put(5, Arrays.asList(
                    new Way( 235L, new WayWeight(1.06)),
                    new Way( 236L, new WayWeight(1.09)),
                    new Way( 237L, new WayWeight(1.09)),
                    new Way( 238L, new WayWeight(1.14)),
                    new Way( 239L, new WayWeight(1.06)),
                    new Way( 25L, new WayWeight(1.1)),
                    new Way( 26L, new WayWeight(1.07)),
                    new Way( 299L, new WayWeight(1.14)),
                    new Way( 210L, new WayWeight(1.07)),
                    new Way( 211L, new WayWeight(1.13)),
                    new Way( 795L, new WayWeight(1.13)),
                    new Way( 794L, new WayWeight(1.14))));
            put(6, Arrays.asList(
                    new Way( 235L, new WayWeight(1.16)),
                    new Way( 236L, new WayWeight(1.19)),
                    new Way( 237L, new WayWeight(1.19)),
                    new Way( 238L, new WayWeight(1.15)),
                    new Way( 239L, new WayWeight(1.17)),
                    new Way( 25L, new WayWeight(1.19)),
                    new Way( 26L, new WayWeight(1.15)),
                    new Way( 299L, new WayWeight(1.18)),
                    new Way( 210L, new WayWeight(1.15)),
                    new Way( 211L, new WayWeight(1.19)),
                    new Way( 795L, new WayWeight(1.18)),
                    new Way( 794L, new WayWeight(1.18))));
            put(7, Arrays.asList(
                    new Way( 235L, new WayWeight(1.24)),
                    new Way( 236L, new WayWeight(1.22)),
                    new Way( 237L, new WayWeight(1.21)),
                    new Way( 238L, new WayWeight(1.23)),
                    new Way( 239L, new WayWeight(1.22)),
                    new Way( 25L, new WayWeight(1.22)),
                    new Way( 26L, new WayWeight(1.24)),
                    new Way( 299L, new WayWeight(1.21)),
                    new Way( 210L, new WayWeight(1.2)),
                    new Way( 211L, new WayWeight(1.23)),
                    new Way( 795L, new WayWeight(1.21)),
                    new Way( 794L, new WayWeight(1.23))));
            put(8, Arrays.asList(
                    new Way( 235L, new WayWeight(1.5)),
                    new Way( 236L, new WayWeight(1.48)),
                    new Way( 237L, new WayWeight(1.53)),
                    new Way( 238L, new WayWeight(1.47)),
                    new Way( 239L, new WayWeight(1.5)),
                    new Way( 25L, new WayWeight(1.52)),
                    new Way( 26L, new WayWeight(1.52)),
                    new Way( 299L, new WayWeight(1.47)),
                    new Way( 210L, new WayWeight(1.53)),
                    new Way( 211L, new WayWeight(1.54)),
                    new Way( 795L, new WayWeight(1.53)),
                    new Way( 794L, new WayWeight(1.54))));
            put(9, Arrays.asList(
                    new Way( 235L, new WayWeight(1.65)),
                    new Way( 236L, new WayWeight(1.64)),
                    new Way( 237L, new WayWeight(1.58)),
                    new Way( 238L, new WayWeight(1.59)),
                    new Way( 239L, new WayWeight(1.65)),
                    new Way( 25L, new WayWeight(1.58)),
                    new Way( 26L, new WayWeight(1.58)),
                    new Way( 299L, new WayWeight(1.65)),
                    new Way( 210L, new WayWeight(1.63)),
                    new Way( 211L, new WayWeight(1.6)),
                    new Way( 795L, new WayWeight(1.64)),
                    new Way( 794L, new WayWeight(1.66))));
        }};
    }

    private static Map<Integer, List<Way>> getAccidents() {
        return new HashMap<Integer, List<Way>>() {{
            put(2, Arrays.asList(new Way(238L, new WayWeight(1.23))));
            put(5, Arrays.asList(new Way(210L, new WayWeight(1.23))));
        }};
    }

}
