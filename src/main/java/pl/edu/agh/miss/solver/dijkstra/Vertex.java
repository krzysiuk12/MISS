package pl.edu.agh.miss.solver.dijkstra;

import pl.edu.agh.miss.domain.Node;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
class Vertex implements Comparable<Vertex> {

    private Node node;
    private Node previousNode;
    private double totalCost;

    Vertex() {
    }

    Vertex(Node node) {
        this(node, null);
    }

    Vertex(Node node, Node previousNode) {
        this.node = node;
        this.previousNode = previousNode;
        this.totalCost = Double.POSITIVE_INFINITY;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(getTotalCost(), o.getTotalCost());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vertex{");
        sb.append("node=").append(node);
        sb.append(", previousNode=").append(previousNode);
        sb.append(", totalCost=").append(totalCost);
        sb.append('}');
        return sb.toString();
    }
}
