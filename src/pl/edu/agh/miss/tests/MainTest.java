package pl.edu.agh.miss.tests;

import pl.edu.agh.miss.graph.Graph;
import pl.edu.agh.miss.solver.DistanceDijkstraSolver;
import pl.edu.agh.miss.vertices.Vertex;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class MainTest {

    public static void main(String[] args) {
        IGraphTest graphTest = new GraphTest();
        Graph graph = graphTest.createTestGraph();
        Vertex source = graphTest.getSourceVertex();
        Vertex target = graphTest.getTargetVertex();

        DistanceDijkstraSolver distanceDijkstraSolver = new DistanceDijkstraSolver();
        distanceDijkstraSolver.computePaths(source);

        for (Vertex v : graph.getVertices()) {
            System.out.println("Distance to " + v + ": " + v.getMinDistance());
            List<Vertex> path = distanceDijkstraSolver.getShortestPathToTarget(v);
            System.out.println("Path: " + path);
        }
    }

}
