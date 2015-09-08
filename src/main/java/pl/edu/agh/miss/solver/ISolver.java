package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.domain.Map;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.Path;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public interface ISolver {

    Path findPath(Node start, Node end);

}
