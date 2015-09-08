package pl.edu.agh.miss.main;

import pl.edu.agh.miss.config.PropertyKeys;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class MainApp {



    public static void main(String[] args) throws Exception {
        // gisDao.refreshWays();
        String dbname = "rynek_database"; //getProperty(PropertyKeys.DBNAME, PropertyKeys.ENV_DBNAME);
        String username = "krzysztofkicinger"; //getProperty(PropertyKeys.USERNAME, PropertyKeys.ENV_USERNAME);
        String password = "postgres"; //getProperty(PropertyKeys.PASSWORD, PropertyKeys.ENV_PASSWORD);
//        String dbname = getProperty(PropertyKeys.DBNAME, PropertyKeys.ENV_DBNAME);
//        String username = getProperty(PropertyKeys.USERNAME, PropertyKeys.ENV_USERNAME);
//        String password = getProperty(PropertyKeys.PASSWORD, PropertyKeys.ENV_PASSWORD);
        JavaGisDao gisDao = JavaGisDao.getInstance(dbname, username, password);
        gisDao.refreshWays();
//        SimulationTestCase testCase = new SimulationTestCase(new Node(1207L), new Node(811L), SimulationService.POSTGIS, SimulationAlgorithm.DIJKSTRA, 0.0, 1, 8);
//        Simulation simulation = new Simulation(testCase, new TrafficSimulationGis(getTraffic(), new HashMap<>()));
//        Path result = simulation.call();
//        // gisDao.refreshWays();
//        // Path p = gisDao.getRouteWithDijkstraAlgorithm(1207, 811);
//        System.out.println(result);
    }

    private static String getProperty(String propertyName, String envPropertyName) {
        String result = System.getProperty(propertyName);
        return result != null ? result : System.getenv(envPropertyName);
    }

    private static Map<Integer, List<Way>> getTraffic() {
        return new HashMap<Integer, List<Way>>() {{
            put(4, Arrays.asList(new Way(560L, new WayWeight(JavaGisDao.getCost(560L) * 30))));
            put(10, Arrays.asList(new Way(717L, new WayWeight(JavaGisDao.getCost(717L) * 25))));
//            put(18, Arrays.asList(new Way(18L, new WayWeight(JavaGisDao.getCost(18L) * 3))));
//            put(26, Arrays.asList(new Way(2152L, new WayWeight(JavaGisDao.getCost(2152L) * 2))));
        }};
    }
}
