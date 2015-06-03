package pl.edu.agh.miss.gis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayType;
import pl.edu.agh.miss.map.way.WayWeight;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.simulation.Simulation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JavaGisDao {
    static String newline = System.getProperty("line.separator");
    private static Logger logger = LogManager.getLogger(Simulation.class.getSimpleName());

    public static void main(String[] args) {
        Path p = getRoute(10, 9);
    }

    public static Path getRoute(long nodeIdStart, long nodeIdEnd) {
        Connection conn;
        Path p = null;
        try {
            // use connection pooling
            conn = getConnection();
            Statement s = conn.createStatement();
            String query = buildRouteQuery("pgr_dijkstra", nodeIdStart, nodeIdEnd);
            ResultSet r = s.executeQuery(query);
            p = processQuery(r);
            filterQuery(nodeIdStart, nodeIdEnd, p);
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static void setCost(Long gid, double cost) {
        Connection conn;
        try {
            // use connection pooling
            conn = getConnection();
            Statement s = conn.createStatement();
            String query = buildCostQuery(gid, cost);
            s.executeUpdate(query);
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getCost(Long gid) {
        Connection conn;
        double cost = 0.0;
        try {
            // use connection pooling
            conn = getConnection();
            Statement s = conn.createStatement();
            String query = "SELECT to_cost::float FROM ways WHERE gid = " + gid + ";";
            final ResultSet resultSet = s.executeQuery(query);
            resultSet.next();
            cost = resultSet.getDouble("to_cost");
            s.close();
            conn.close();
        } catch (Exception e) {
            // no cost defined
        }

        return cost;
    }
    public static void filterQuery(long nodeIdStart, long nodeIdEnd, Path p) {
        p.setNodes(p.getNodes().stream().filter(node -> {
            return node.getOsmId() != nodeIdStart;
        }).collect(Collectors.toList()));
        p.setWays(p.getWays().stream().filter(way -> {
            return way.getStart().getOsmId() != nodeIdStart;
        }).collect(Collectors.toList()));
    }

    private static Path processQuery(ResultSet r) throws SQLException {
        List<Node> nodes = new ArrayList<>();
        List<Way> ways = new ArrayList<>();
        double totalCost = 0.0;

        while (r.next()) {
            double cost = r.getDouble("cost");
            totalCost += cost;
            final Node fromNode = new Node(r.getLong("node"), r.getDouble("y1"), r.getDouble("x1"));
            nodes.add(fromNode);
            final Node toNode = new Node(r.getLong("node"), r.getDouble("y2"), r.getDouble("x2"));
            nodes.add(toNode);
            ways.add(new Way(fromNode, toNode, WayType.TWO_WAY, new WayWeight(cost), r.getLong("edge")));
        }

        Path path = new Path(nodes, ways, totalCost);
        return path;
    }

    private static String buildRouteQuery(String algorithm, long nodeIdStart, long nodeIdEnd) {
        return "SELECT ways.x1, ways.y1, ways.x2, ways.y2, seq, id1 AS node, id2 AS edge, cost FROM " + algorithm + "('" + newline +
                "SELECT gid AS id," + newline +
                "source::integer," + newline +
                "target::integer," + newline +
                "length * ways.to_cost AS cost" + newline +
                "FROM ways'," + newline +
                nodeIdStart+", "+nodeIdEnd+", false, false) LEFT JOIN ways on (id1 = ways.gid);";
    }

    private static String buildCostQuery(Long gid, double cost) {
        return "update ways set to_cost = " + cost + " where gid = " + gid + ";";
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName("org.postgresql.Driver");
        String pgsql_db = System.getenv("PGSQL_DB");
        String pgsql_user = System.getenv("PGSQL_USER");
        String url = "jdbc:postgresql://localhost:5432/" + pgsql_db;
        String pgsql_password = System.getenv("PGSQL_PASSWORD");
        logger.info(url + " " + pgsql_user + " " + pgsql_password);
        conn = DriverManager.getConnection(url, pgsql_user, pgsql_password);
        return conn;
    }
}