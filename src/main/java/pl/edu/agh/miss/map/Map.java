package pl.edu.agh.miss.map;

import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.map.way.WayType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class Map {

    private String name;
    private Set<Node> nodes;
    private Set<Way> ways;

    public Map() {
    }

    public Map(String name, Set<Node> nodes, Set<Way> ways) {
        this.name = name;
        this.nodes = nodes;
        this.ways = ways;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Way> getWays() {
        return ways;
    }

    public void setWays(Set<Way> ways) {
        this.ways = ways;
    }

    public Way getWay(Node startNode, Node endNode) {
        for(Way way : ways) {
            if(isCorrectWay(way, startNode, endNode)) {
                return way;
            }
        }
        return null;
    }

    public Set<Way> getWaysFromSource(Node sourceNode) {
        Set<Way> waysFromSource = new HashSet<Way>();
        for(Way way : ways) {
            if(way.getStart().equals(sourceNode) || (way.getType() == WayType.TWO_WAY && way.getEnd().equals(sourceNode))) {
                waysFromSource.add(way);
            }
        }
        return waysFromSource;
    }

    public Node getNodeByName(String name) {
        for(Node node : nodes) {
            if(node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    private boolean isCorrectWay(Way way, Node startNode, Node endNode) {
        //System.out.println("Way : " + way + "\nStart: " + startNode + "\nEnd: " + endNode + "\n");
        return (way.getStart().equals(startNode) && way.getEnd().equals(endNode)) ||
               (way.getType() == WayType.TWO_WAY && way.getStart().equals(endNode) && way.getEnd().equals(startNode));
    }

}
