package pl.edu.agh.miss.solver.dijkstra;

import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.solver.ISolver;

import java.util.*;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class DijkstraSolver implements ISolver {

    private Set<Vertex> vertices = new HashSet<Vertex>();
    private PriorityQueue<Vertex> verticesQueue = new PriorityQueue<Vertex>();

    @Override
    public List<Node> findPath(Node start, Node end, Map map) {
        // System.out.println("Start: " + start + "\nEnd: " + end);
        initializeVertices(map.getNodes());
        Vertex sourceVertex = getVertexByNode(start);
        setVertexValues(sourceVertex, 0.0, null);
        verticesQueue.add(sourceVertex);
        // System.out.println("Source: " + sourceVertex + "\n");
        while(!verticesQueue.isEmpty()) {
            Vertex startVertex = verticesQueue.poll();
            // System.out.println("Queue start: " + startVertex+ "\n");
            Set<Way> vertexWays = map.getWaysFromSource(startVertex.getNode());
            for(Way way : vertexWays) {
                Vertex currentVertex = getVertexByNode(way.getDestination(startVertex.getNode()));
                //System.out.println("\tCurrent start: " + currentVertex + "\n");
                double totalCostThroughCurrent = getVertexWeight(startVertex) + getEdgeWeight(startVertex, currentVertex, map);
                if(totalCostThroughCurrent < currentVertex.getTotalCost()) {
                    verticesQueue.remove(currentVertex);
                    setVertexValues(currentVertex, totalCostThroughCurrent, startVertex);
                    verticesQueue.add(currentVertex);
                }
            }
        }

        return getPath(end);
    }

    private void initializeVertices(Set<Node> nodes) {
        for(Node node : nodes) {
            vertices.add(new Vertex(node));
        }
    }

    private Vertex getVertexByNode(Node node) {
        for(Vertex vertex : vertices) {
            if(vertex.getNode().equals(node)) {
                return vertex;
            }
        }
        return null;
    }

    private double getVertexWeight(Vertex vertex) {
        return vertex.getTotalCost();
    }

    private double getEdgeWeight(Vertex start, Vertex end, Map map) {
        return map.getWay(start.getNode(), end.getNode()).getWeight().getWeight();
    }

    private void setVertexValues(Vertex vertex, double totalCost, Vertex previous) {
        vertex.setTotalCost(totalCost);
        vertex.setPreviousNode(previous != null ? previous.getNode() : null);
    }

    private List<Node> getPath(Node end) {
        Vertex currentVertex = getVertexByNode(end);
        List<Node> path = new ArrayList<Node>();
        while(currentVertex != null) {
            path.add(currentVertex.getNode());
            currentVertex = getVertexByNode(currentVertex.getPreviousNode());
        }
        Collections.reverse(path);
        return path;
    }

}
