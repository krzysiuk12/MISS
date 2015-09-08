package pl.edu.agh.miss.domain;

import pl.edu.agh.miss.domain.way.Way;

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

    public Path(double totalCost) {
        this.totalCost = totalCost;
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
        final String separator = " -> ";
        final Node startNode = getNodes().stream().findFirst().get();
        final Node destinationNode = getNodes().get(getNodes().size() - 1);
        final StringBuffer sb = new StringBuffer("Path (from ").append(startNode.getOsmId()).append(" to ").append(destinationNode.getOsmId()).append(") {\n\tNodes:");
        for(Node node : getNodes()) {
            sb.append(node.getOsmId()).append(separator);
        }
        sb.replace(sb.length() - separator.length(), sb.length(), "");
        sb.append(",\n\tWays: ");
        for(Way way : getWays()) {
            sb.append(way.getOsmId()).append(separator);
        }
        sb.replace(sb.length() - separator.length(), sb.length(), "");
        sb.append(",\n\tCost = ").append(totalCost);
        sb.append("\n}");
        return sb.toString();
    }
}
