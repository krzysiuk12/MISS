package pl.edu.agh.miss.path;

import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.Way;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class Path {

    private List<Node> nodes;
    private List<Way> ways;
    private double totalCost;

    public Path() {
    }

    public Path(List<Node> nodes, List<Way> ways, double totalCost) {
        this.nodes = nodes;
        this.ways = ways;
        this.totalCost = totalCost;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Way> getWays() {
        return ways;
    }

    public void setWays(List<Way> ways) {
        this.ways = ways;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Path{");
        for(Node node : nodes) {
            sb.append(node.getName()).append(" -> ");
        }
        return sb.substring(0, sb.length() - 4).toString();
    }
}
