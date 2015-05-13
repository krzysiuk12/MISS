package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public interface ISolver<T> {

    public List<Node> findPath(Node start, Node end, Map map);

}
