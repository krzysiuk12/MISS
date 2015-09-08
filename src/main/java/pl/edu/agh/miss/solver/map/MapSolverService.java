package pl.edu.agh.miss.solver.map;

import pl.edu.agh.miss.domain.Map;
import pl.edu.agh.miss.solver.ISolver;
import pl.edu.agh.miss.solver.AbstractSolverService;

import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class MapSolverService extends AbstractSolverService {

    private Map map;

    public MapSolverService(Map map, Set<ISolver> solvers) {
        super(solvers, solvers.size());
        this.map = map;
    }

    public MapSolverService(Map map, Set<ISolver> solvers, int executorsPoolSize) {
        super(solvers, executorsPoolSize);
        this.map = map;
    }

}
