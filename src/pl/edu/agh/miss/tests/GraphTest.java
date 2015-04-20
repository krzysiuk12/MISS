package pl.edu.agh.miss.tests;

import pl.edu.agh.miss.edges.DirectedEdge;
import pl.edu.agh.miss.edges.DistanceUnit;
import pl.edu.agh.miss.edges.Edge;
import pl.edu.agh.miss.edges.EdgeWeight;
import pl.edu.agh.miss.graph.Graph;
import pl.edu.agh.miss.vertices.TramStop;
import pl.edu.agh.miss.vertices.Vertex;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class GraphTest implements IGraphTest {

    private Graph graph;
    private Vertex source;
    private Vertex target;

    public Graph getGraph() {
        if(graph == null) {
            final Vertex redville = new TramStop("Redvile", 1);
            final Vertex blueville = new TramStop("Blueville", 3);
            final Vertex greenville = new TramStop("Greenville", 5);
            final Vertex orangeville = new TramStop("Orangeville", 7);
            final Vertex purpleville = new TramStop("Purpleville", 8);

            redville.setAdjacencies(new ArrayList<Edge>() {
                {
                    add(new DirectedEdge(redville, blueville, new EdgeWeight(5, DistanceUnit.KILOMETRES, 5, TimeUnit.SECONDS)));
                    add(new DirectedEdge(redville, greenville, new EdgeWeight(10, DistanceUnit.KILOMETRES, 10, TimeUnit.SECONDS)));
                    add(new DirectedEdge(redville, orangeville, new EdgeWeight(8, DistanceUnit.KILOMETRES, 8, TimeUnit.SECONDS)));
                }
            });
            blueville.setAdjacencies(new ArrayList<Edge>() {
                {
                    add(new DirectedEdge(blueville, redville, new EdgeWeight(5, DistanceUnit.KILOMETRES, 5, TimeUnit.SECONDS)));
                    add(new DirectedEdge(blueville, greenville, new EdgeWeight(3, DistanceUnit.KILOMETRES, 3, TimeUnit.SECONDS)));
                    add(new DirectedEdge(blueville, purpleville, new EdgeWeight(7, DistanceUnit.KILOMETRES, 7, TimeUnit.SECONDS)));
                }
            });
            greenville.setAdjacencies(new ArrayList<Edge>() {
                {
                    add(new DirectedEdge(greenville, redville, new EdgeWeight(10, DistanceUnit.KILOMETRES, 10, TimeUnit.SECONDS)));
                    add(new DirectedEdge(greenville, blueville, new EdgeWeight(3, DistanceUnit.KILOMETRES, 3, TimeUnit.SECONDS)));
                }
            });
            orangeville.setAdjacencies(new ArrayList<Edge>() {
                {
                    add(new DirectedEdge(orangeville, redville, new EdgeWeight(8, DistanceUnit.KILOMETRES, 8, TimeUnit.SECONDS)));
                    add(new DirectedEdge(orangeville, purpleville, new EdgeWeight(2, DistanceUnit.KILOMETRES, 2, TimeUnit.SECONDS)));
                }
            });
            purpleville.setAdjacencies(new ArrayList<Edge>() {
                {
                    add(new DirectedEdge(purpleville, blueville, new EdgeWeight(7, DistanceUnit.KILOMETRES, 7, TimeUnit.SECONDS)));
                    add(new DirectedEdge(purpleville, orangeville, new EdgeWeight(2, DistanceUnit.KILOMETRES, 2, TimeUnit.SECONDS)));
                }
            });

            this.source = redville;
            this.target = purpleville;

            graph = new Graph("Redville Simple Test");
            graph.addVertex(redville);
            graph.addVertex(blueville);
            graph.addVertex(greenville);
            graph.addVertex(orangeville);
            graph.addVertex(purpleville);
        }
        return graph;

    }

    @Override
    public Vertex getSourceVertex() {
        return source;
    }

    @Override
    public Vertex getTargetVertex() {
        return target;
    }
}
