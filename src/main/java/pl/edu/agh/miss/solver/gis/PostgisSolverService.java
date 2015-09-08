package pl.edu.agh.miss.solver.gis;

import pl.edu.agh.miss.gis.JavaGisDao;
import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.domain.Path;
import pl.edu.agh.miss.solver.ISolver;
import pl.edu.agh.miss.solver.AbstractSolverService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * Created by slonka on 01.06.15.
 */
public class PostgisSolverService extends AbstractSolverService {

    private static final Set<ISolver> SOLVERS = new HashSet<ISolver>() {{
        add(new PostgisDijkstraSolver());
        add(new PostgisAstarSolver());
        // add(new PostgisBidirectionalDijkstraSolver());
        // add(new PostgisBidirectionalAstarSolver());
    }};

    public PostgisSolverService() {
        super(SOLVERS, SOLVERS.size());
    }

    public PostgisSolverService(int executorsPoolSize) {
        super(SOLVERS, executorsPoolSize);
    }

    @Override
    public void updateWayInformation(Way way) {
        double currentWeight = way.getWeight().getWeight();
        way.getWeight().setWeight(currentWeight * JavaGisDao.getCost(way.getOsmId()));;
    }
}
