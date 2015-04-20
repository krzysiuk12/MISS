package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.edges.DirectedEdge;
import pl.edu.agh.miss.edges.Edge;
import pl.edu.agh.miss.vertices.Vertex;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class DurationDijkstraSolver extends DijkstraSolver {

    @Override
    protected void initializeValues(Vertex source) {
        source.setMinDuration(0.0);
        source.setPreviousVertex(null);
    }

    @Override
    protected double getEdgeWeight(Edge edge) {
        return ((DirectedEdge)edge).getWeight().getDuration();
    }

    @Override
    protected double getVertexWeight(Vertex vertex) {
        return vertex.getMinDuration();
    }

    @Override
    protected void setVertexValues(Vertex vertex, double weight, Vertex previous) {
        vertex.setMinDuration(weight);
        vertex.setPreviousVertex(previous);
    }

}
