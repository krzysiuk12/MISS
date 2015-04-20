package pl.edu.agh.miss.tests;

import pl.edu.agh.miss.graph.Graph;
import pl.edu.agh.miss.vertices.Vertex;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public interface IGraphTest {

    public Graph createTestGraph();

    public Vertex getSourceVertex();

    public Vertex getTargetVertex();

}
