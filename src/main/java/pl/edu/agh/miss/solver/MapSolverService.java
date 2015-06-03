package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.map.way.Way;
import pl.edu.agh.miss.path.Path;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class MapSolverService implements ISolverService {

    private Map map;
    private Set<ISolver> solvers = new HashSet<ISolver>();
    private Set<Future<Path>> results = new HashSet<Future<Path>>();
    private ExecutorService executor;

    public MapSolverService(Map map, Set<ISolver> solvers) {
        this(map, solvers, solvers.size());
    }

    public MapSolverService(Map map, Set<ISolver> solvers, int executorsPoolSize) {
        this.solvers = solvers;
        this.map = map;
        this.executor = Executors.newFixedThreadPool(executorsPoolSize);
    }

    @Override
    public Path findPath(Node startNode, Node endNode) {
        for(ISolver solver : solvers) {
            Callable<Path> callableSolver = createCallableSolver(solver, startNode, endNode, map);
            results.add(executor.submit(callableSolver));
        }
        return null;
    }

    @Override
    public Path getPath() throws Exception {
        for(Future<Path> result : results) {
            if(result.isDone()) {
                return result.get();
            }
        }
        return null;
    }

    @Override
    public void updateWeight(Way nextWay) {

    }

    private Callable<Path> createCallableSolver(final ISolver solver, final Node start, final Node end, final Map map) {
        return new Callable<Path>() {
            @Override
            public Path call() throws Exception {
                return solver.findPath(start, end, map);
            }
        };
    }
}
