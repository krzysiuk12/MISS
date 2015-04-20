package pl.edu.agh.miss.problem;

import pl.edu.agh.miss.graph.Graph;
import pl.edu.agh.miss.vertices.Vertex;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class Problem {

    private final Graph graph;
    private final Vertex source;
    private final Vertex target;
    private final ProblemUnit problemUnit;

    public Problem(Graph graph, Vertex source, Vertex target, ProblemUnit problemUnit) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.problemUnit = problemUnit;
    }

    public Graph getGraph() {
        return graph;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public ProblemUnit getProblemUnit() {
        return problemUnit;
    }
}
