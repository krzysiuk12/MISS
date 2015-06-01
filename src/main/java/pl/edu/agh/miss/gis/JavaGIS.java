package pl.edu.agh.miss.gis;

import java.sql.*;

public class JavaGIS {

    public static void main(String[] args) {

        Connection conn;

        try {
            conn = getConnection();
            Statement s = conn.createStatement();
            String query = buildRouteQuery("pgr_dijkstra", 10, 9);
            ResultSet r = s.executeQuery(query);
            processQuery(r);
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processQuery(ResultSet r) throws SQLException {
        while (r.next()) {
            float cost = r.getFloat("cost");
            System.out.println("Cost at " + cost);
        }
    }

    private static String buildRouteQuery(String algorithm, int nodeIdStart, int nodeIdEnd) {
        String newline = System.getProperty("line.separator");
        return "SELECT seq, id1 AS node, id2 AS edge, cost FROM " + algorithm + "('" + newline +
                "SELECT gid AS id," + newline +
                "source::integer," + newline +
                "target::integer," + newline +
                "length::double precision AS cost" + newline +
                "FROM ways'," + newline +
                nodeIdStart+", "+nodeIdEnd+", false, false)";
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName("org.postgresql.Driver");
        String pgsql_db = System.getenv("PGSQL_DB");
        String pgsql_user = System.getenv("PGSQL_USER");
        String url = "jdbc:postgresql://localhost:5432/" + pgsql_db;
        String pgsql_password = System.getenv("PGSQL_PASSWORD");
        System.out.println(url + " " + pgsql_user + " " + pgsql_password);
        conn = DriverManager.getConnection(url, pgsql_user, pgsql_password);
        return conn;
    }
}