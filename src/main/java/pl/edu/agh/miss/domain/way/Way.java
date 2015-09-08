package pl.edu.agh.miss.domain.way;

import pl.edu.agh.miss.domain.Node;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class Way {

    private long osmId = 0L;
    private Node start;
    private Node end;
    private WayType type;
    private WayWeight weight;

    public Way() {
    }

    public Way(Long osmId, WayWeight weight) {
        this.osmId = osmId;
        this.weight = weight;
    }

    public Way(Node start, Node end, WayType type, WayWeight weight) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.weight = weight;
    }

    public Way(Node start, Node end, WayType type, WayWeight weight, Long osmId) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.weight = weight;
        this.osmId = osmId;
    }

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
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
                "osmId=" + osmId +
                ", start=" + start +
                ", end=" + end +
                ", type=" + type +
                ", weight=" + weight +
                '}';
    }
}
