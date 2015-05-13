package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.map.Map;
import pl.edu.agh.miss.map.Node;
import pl.edu.agh.miss.solver.dijkstra.DijkstraSolver;

import java.util.HashSet;
import java.util.List;
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
    private Set<Future<List<Node>>> results = new HashSet<Future<List<Node>>>();
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public MapSolverService(Map map) {
        this.map = map;
        solvers.add(new DijkstraSolver());
    }

    @Override
    public void findPath(Node startNode, Node endNode) {
        for(ISolver solver : solvers) {
            Callable<List<Node>> callableSolver = createCallableSolver(solver, startNode, endNode, map);
            results.add(executor.submit(callableSolver));
        }
    }

    @Override
    public List<Node> getPath() throws Exception {
        for(Future<List<Node>> result : results) {
            if(result.isDone()) {
                return result.get();
            }
        }
        return null;
    }

    private Callable<List<Node>> createCallableSolver(final ISolver solver, final Node start, final Node end, final Map map) {
        return new Callable<List<Node>>() {
            @Override
            public List<Node> call() throws Exception {
                return solver.findPath(start, end, map);
            }
        };
    }
}
