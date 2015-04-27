package pl.edu.agh.miss.edges;

import pl.edu.agh.miss.vertices.Vertex;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public abstract class Edge {

    protected final Vertex u;
    protected final Vertex v;

    protected Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

}
