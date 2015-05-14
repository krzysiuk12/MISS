package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.path.Path;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public interface ISolverService {

    public void findPath(Node startNode, Node endNode);

    public Path getPath() throws Exception;



}
