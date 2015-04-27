package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.edges.DirectedEdge;
import pl.edu.agh.miss.edges.Edge;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.vertices.Vertex;

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

    public Path getShortestPathToTarget(Vertex target) {
        Path path = new Path();
        path.setTarget(target);
        Vertex vertex = target.getPreviousVertex();
        while (vertex != null) {
            path.getMiddleVertices().add(0, vertex);
            vertex = vertex.getPreviousVertex();
        }
        if(path.getMiddleVertices().isEmpty()) {
            path.setSource(path.getTarget());
        } else {
            path.setSource(path.getMiddleVertices().get(0));
            path.getMiddleVertices().remove(0);
        }
        return path;
    }

    protected abstract void initializeValues(Vertex source);

    protected abstract double getEdgeWeight(Edge edge);

    public abstract double getVertexWeight(Vertex vertex);

    protected abstract void setVertexValues(Vertex vertex, double weight, Vertex previous);

}
