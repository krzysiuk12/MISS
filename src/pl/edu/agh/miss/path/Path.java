package pl.edu.agh.miss.path;

import pl.edu.agh.miss.vertices.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class Path {

    private Vertex source;
    private Vertex target;
    private List<Vertex> middleVertices;

    public Path() {
        this.middleVertices = new ArrayList<Vertex>();
    }

    public Path(Vertex source, Vertex target, List<Vertex> middleVertices) {
        this.source = source;
        this.target = target;
        this.middleVertices = middleVertices;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public List<Vertex> getMiddleVertices() {
        return middleVertices;
    }

    public void setMiddleVertices(List<Vertex> middleVertices) {
        this.middleVertices = middleVertices;
    }

    @Override
    public String toString() {
        return "Path{" +
                "source=" + source +
                ", target=" + target +
                ", middleVertices=" + middleVertices +
                '}';
    }

}
