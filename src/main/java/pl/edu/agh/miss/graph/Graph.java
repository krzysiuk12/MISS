package pl.edu.agh.miss.graph;

import pl.edu.agh.miss.vertices.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class Graph {

    private final String name;
    private Set<Vertex> vertices;

    public Graph(String name) {
        this.name = name;
        this.vertices = new HashSet<Vertex>();
    }

    public String getName() {
        return name;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void removeVertex(Vertex vertex) {
        vertices.remove(vertex);
    }
}
