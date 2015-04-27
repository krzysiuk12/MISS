package pl.edu.agh.miss.edges;

import pl.edu.agh.miss.vertices.Vertex;

import java.util.concurrent.TimeUnit;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class DirectedEdge extends Edge {

    private EdgeWeight weight;

    public DirectedEdge(Vertex source, Vertex target, EdgeWeight weight) {
        super(source, target);
        this.weight = weight;
    }

    public Vertex getSource() {
        return u;
    }

    public Vertex getTarget() {
        return v;
    }

    public EdgeWeight getWeight() {
        return weight;
    }

    public void setWeight(EdgeWeight weight) {
        this.weight = weight;
    }

    public void updateDuration(double duration, TimeUnit durationUnit) {
        getWeight().setDuration(duration);
        getWeight().setDurationUnit(durationUnit);
    }

}
