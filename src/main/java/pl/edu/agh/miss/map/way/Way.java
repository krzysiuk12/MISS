package pl.edu.agh.miss.map.way;

import pl.edu.agh.miss.map.Node;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class Way {

    private Node start;
    private Node end;
    private WayType type;
    private WayWeight weight;

    public Way() {
    }

    public Way(Node start, Node end, WayType type, WayWeight weight) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.weight = weight;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public WayType getType() {
        return type;
    }

    public void setType(WayType type) {
        this.type = type;
    }

    public WayWeight getWeight() {
        return weight;
    }

    public void setWeight(WayWeight weight) {
        this.weight = weight;
    }

    public Node getDestination(Node node) {
        if(type == WayType.TWO_WAY && !start.equals(node)) {
            return start;
        }
        return end;
    }

    @Override
    public String toString() {
        return "Way{" +
                "start=" + start +
                ", end=" + end +
                ", type=" + type +
                ", weight=" + weight +
                '}';
    }
}
