package pl.edu.agh.miss.solver.gis;

import pl.edu.agh.miss.gis.JavaGisDao;
import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.solver.ISolver;

/**
 * Created by slonka on 01.06.15.
 */
public class PostgisSolver implements ISolver{

    @Override
    public Path findPath(Node start, Node end, Map map) {
        return JavaGisDao.getRoute(start.getOsmId(), end.getOsmId());
    }
}
