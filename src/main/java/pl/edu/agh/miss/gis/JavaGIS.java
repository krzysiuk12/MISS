package pl.edu.agh.miss.gis;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaGIS {

    public static void main(String[] args) {

        java.sql.Connection conn;

        try {
            //Load the JDBC driver and establish a connection.

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/osm_new";
            conn = DriverManager.getConnection(url, "postgres", "national");

            // Create a statement and execute a select query.

            Statement s = conn.createStatement();
            String newline = System.getProperty("line.separator");
            String query = "SELECT seq, id1 AS node, id2 AS edge, cost FROM pgr_dijkstra('" + newline +
                    "SELECT gid AS id," + newline +
                    "source::integer," + newline +
                    "target::integer," + newline +
                    "length::double precision AS cost" + newline +
                    "FROM ways'," + newline +
                    "10, 9, false, false)";

            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                float cost = r.getFloat("cost");
                System.out.println("Cost at " + cost);
            }
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}