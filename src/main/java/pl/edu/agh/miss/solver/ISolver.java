package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.path.Path;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public interface ISolver<T> {

    public Path findPath(Node start, Node end, Map map);

}
