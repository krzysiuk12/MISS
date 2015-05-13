package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.map.Node;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public interface ISolverService {

    public void findPath(Node startNode, Node endNode);

    public List<Node> getPath() throws Exception;

}
