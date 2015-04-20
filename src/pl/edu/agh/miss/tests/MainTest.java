package pl.edu.agh.miss.tests;

import pl.edu.agh.miss.graph.Graph;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.solver.DijkstraSolver;
import pl.edu.agh.miss.solver.DistanceDijkstraSolver;
import pl.edu.agh.miss.vertices.Vertex;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class MainTest {

    public static void main(String[] args) {
        IGraphTest graphTest = new GraphTest();
        Graph graph = graphTest.createTestGraph();
        Vertex source = graphTest.getSourceVertex();
        Vertex target = graphTest.getTargetVertex();

        DijkstraSolver dijkstraSolver = new DistanceDijkstraSolver();
        dijkstraSolver.computePaths(source);

        for (Vertex v : graph.getVertices()) {
            System.out.println("Distance to " + v + ": " + dijkstraSolver.getVertexWeight(v));
            Path path = dijkstraSolver.getShortestPathToTarget(v);
            System.out.println("Path: " + path);
        }
    }

}
