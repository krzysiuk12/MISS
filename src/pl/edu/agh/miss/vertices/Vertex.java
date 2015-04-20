package pl.edu.agh.miss.vertices;

import pl.edu.agh.miss.edges.Edge;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public abstract class Vertex implements Comparable<Vertex> {

    private String name;
    private List<Edge> adjacencies;
    private Vertex previousVertex;
    private double minDistance;
    private double minDuration;

    public Vertex(String name) {
        this.name = name;
        this.previousVertex = null;
        this.minDistance = Double.POSITIVE_INFINITY;
        this.minDuration = Double.POSITIVE_INFINITY;
    }

    public abstract double getWaitingTime();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(List<Edge> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public double getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(double minDuration) {
        this.minDuration = minDuration;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(getMinDistance(), o.getMinDistance());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (!name.equals(vertex.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
