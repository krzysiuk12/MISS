package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.edges.DirectedEdge;
import pl.edu.agh.miss.edges.Edge;
import pl.edu.agh.miss.vertices.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public abstract class DijkstraSolver {

    public void computePaths(Vertex source) {
        initializeValues(source);

        PriorityQueue<Vertex> verticesQueue = new PriorityQueue<Vertex>();
        verticesQueue.add(source);

        while (!verticesQueue.isEmpty()) {
            Vertex startVertex = verticesQueue.poll();

            for (Edge edge : startVertex.getAdjacencies()) {
                Vertex currentVertex = ((DirectedEdge)edge).getTarget();
                double weightThroughCurrent = getEdgeWeight(edge) + getVertexWeight(startVertex);
                if (weightThroughCurrent < currentVertex.getMinDistance()) {
                    verticesQueue.remove(currentVertex);
                    setVertexValues(currentVertex, weightThroughCurrent, startVertex);
                    verticesQueue.add(currentVertex);
                }
            }
        }
    }

    public List<Vertex> getShortestPathToTarget(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        Vertex vertex = target;
        while (vertex != null) {
            path.add(vertex);
            vertex = vertex.getPreviousVertex();
        }
        Collections.reverse(path);
        return path;
    }

    protected abstract void initializeValues(Vertex source);

    protected abstract double getEdgeWeight(Edge edge);

    protected abstract double getVertexWeight(Vertex vertex);

    protected abstract void setVertexValues(Vertex vertex, double weight, Vertex previous);

}
