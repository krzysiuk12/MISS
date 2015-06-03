package pl.edu.agh.miss.solver.gis;

import pl.edu.agh.miss.gis.JavaGisDao;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.solver.ISolver;
import pl.edu.agh.miss.solver.ISolverService;

/**
 * Created by slonka on 01.06.15.
 */
public class PostgisSolver implements ISolver, ISolverService {
    private Path foundPath;

    @Override
    public Path findPath(Node start, Node end, Map map) {
        foundPath = JavaGisDao.getRoute(start.getOsmId(), end.getOsmId());
        return foundPath;
    }

    @Override
    public Path findPath(Node startNode, Node endNode) {
        foundPath = JavaGisDao.getRoute(startNode.getOsmId(), endNode.getOsmId());
        return foundPath;
    }

    @Override
    public Path getPath() throws Exception {
        return foundPath;
    }
}
