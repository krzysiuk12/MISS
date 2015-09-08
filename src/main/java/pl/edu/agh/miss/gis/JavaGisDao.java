package pl.edu.agh.miss.gis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.miss.config.PropertyKeys;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.domain.way.WayType;
import pl.edu.agh.miss.domain.way.WayWeight;
import pl.edu.agh.miss.domain.Path;
import pl.edu.agh.miss.simulation.Simulation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaGisDao {

    private static Logger logger = LogManager.getLogger(Simulation.class.getSimpleName());
    private static String newline = System.getProperty("line.separator");
    private static JavaGisDao INSTANCE = null;
    private static Connection connection;
    private static String dbname;
    private static String username;
    private static String password;

    private JavaGisDao(String dbname, String username, String password) {
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    public static JavaGisDao getInstance(String dbName, String username, String password) throws Exception {
        if(INSTANCE == null) {
            synchronized (JavaGisDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new JavaGisDao(dbName, username, password);
                    INSTANCE.getConnection();
                }
            }
        }
        return INSTANCE;
    }

    public static void setCost(Long gid, double cost) {
        try(Statement s = getConnection().createStatement()) {
            s.executeUpdate("UPDATE ways SET to_cost = " + cost + " WHERE GID = " + gid + ";");
            // refreshConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void refreshWays() {
        try(Statement s = getConnection().createStatement()) {
            s.executeUpdate("UPDATE ways SET to_cost=wd.to_cost, length=wd.length FROM (SELECT gid, to_cost, length FROM ways_def) AS wd WHERE ways.gid = wd.gid;");
            // refreshConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static double getCost(Long gid) {
        try(Statement s = getConnection().createStatement()) {
            final ResultSet resultSet = s.executeQuery("SELECT length, to_cost::float FROM ways WHERE gid = " + gid + ";");
            resultSet.next();
            return resultSet.getDouble("to_cost");
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1.0;
    }

    public static Path getRouteWithDijkstraAlgorithm(long startNodeId, long endNodeId) {
        try(Statement s = getConnection().createStatement()) {
            String query = buildDijkstraQuery("pgr_dijkstra", startNodeId, endNodeId);
            ResultSet r = s.executeQuery(query);
            return processQuery(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Path getRouteWithBidirectionalDijkstraAlgorithm(long startNodeId, long endNodeId) {
        try(Statement s = getConnection().createStatement()) {
            String query = buildDijkstraQuery("pgr_bdDijkstra", startNodeId, endNodeId);
            ResultSet r = s.executeQuery(query);
            return processQuery(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Path getRouteWithAstarAlgorithm(long startNodeId, long endNodeId) {
        try(Statement s = getConnection().createStatement()) {
            String query = buildAstarQuery("pgr_astar", startNodeId, endNodeId);
            ResultSet r = s.executeQuery(query);
            return processQuery(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Path getRouteWithBidirectionalAstarAlgorithm(long startNodeId, long endNodeId) {
        try(Statement s = getConnection().createStatement()) {
            String query = buildAstarQuery("pgr_bdAstar", startNodeId, endNodeId);
            ResultSet r = s.executeQuery(query);
            return processQuery(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String buildDijkstraQuery(String algorithm, long nodeIdStart, long nodeIdEnd) {
        return "SELECT ways.x1, ways.y1, ways.x2, ways.y2, seq, id1 AS node, id2 AS edge, cost FROM " + algorithm + "('" + newline +
                "SELECT gid AS id," + newline +
                "source::integer," + newline +
                "target::integer," + newline +
                "length * ways.to_cost AS cost" + newline +
                "FROM ways'," + newline +
                nodeIdStart+", "+nodeIdEnd+", false, false) LEFT JOIN ways on (id1 = ways.gid);";
    }

    private static String buildAstarQuery(String algorithm, long nodeIdStart, long nodeIdEnd) {
        return "SELECT ways.x1, ways.y1, ways.x2, ways.y2, seq, id1 AS node, id2 AS edge, cost FROM " + algorithm + "('" + newline +
                "SELECT gid AS id," + newline +
                "source::integer," + newline +
                "target::integer," + newline +
                "length * ways.to_cost AS cost," + newline +
                "x1, y1, x2, y2" + newline +
                "FROM ways'," + newline +
                nodeIdStart + ", " + nodeIdEnd + ", false, false) LEFT JOIN ways on (id1 = ways.gid);";
    }

    private static Path processQuery(ResultSet r) throws SQLException {
        List<Node> nodes = new ArrayList<>();
        List<Way> ways = new ArrayList<>();
        double totalCost = 0.0;
        double edgeCost = 0.0;
        Node previousNode = null;
        Long previousWayId = null;

        while (r.next()) {
            final double cost = r.getDouble("cost");
            final Node currentNode = new Node(r.getLong("node"));

            nodes.add(currentNode);
            if(previousNode != null) {
                ways.add(new Way(previousNode, currentNode, WayType.TWO_WAY, new WayWeight(edgeCost), previousWayId));
            }
            if(Double.compare(edgeCost, -1.0) != 0) {
                previousNode = currentNode;
                edgeCost = cost;
                totalCost += edgeCost;
                previousWayId = r.getLong("edge");
            }
        }
        Path path = new Path(nodes, ways, totalCost);
        return path;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(PropertyKeys.DRIVER_URL + dbname, username, password);
        }
        return connection;
    }

    private static void refreshConnection() throws Exception {
        if(connection != null) {
            connection.close();
            connection = null;
        }
    }
}