package pl.edu.agh.miss.solver.gis;

import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.Path;
import pl.edu.agh.miss.gis.JavaGisDao;
import pl.edu.agh.miss.solver.ISolver;

/**
 * Created by krzysztofkicinger on 9/7/15.
 */
public class PostgisBidirectionalDijkstraSolver implements ISolver {

    @Override
    public Path findPath(Node startNode, Node endNode) {
        return JavaGisDao.getRouteWithBidirectionalDijkstraAlgorithm(startNode.getOsmId(), endNode.getOsmId());
    }

}