package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.domain.Map;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.domain.Path;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public abstract class AbstractSolverService {

    protected Set<ISolver> solvers = new HashSet<>();
    protected Set<Future<Path>> results = new HashSet<>();
    protected ExecutorService executor;

    public AbstractSolverService(Set<ISolver> solvers, int executorsPoolSize) {
        this.solvers = solvers;
        this.executor = Executors.newFixedThreadPool(executorsPoolSize);
    }

    public void updateWayInformation(Way way) {
    }

    public Path findPath(Node startNode, Node endNode) {
        for(ISolver solver : solvers) {
            Callable<Path> callableSolver = createCallableSolver(solver, startNode, endNode);
            results.add(executor.submit(callableSolver));
        }
        return null;
    }

    public Path getPath() throws Exception {
        Path bestResult = new Path(Double.MAX_VALUE);
        for (Future<Path> result : results) {
            if (result.isDone() && (Double.compare(result.get().getTotalCost(), bestResult.getTotalCost()) < 0)) {
                bestResult = result.get();
            }
        }
        results = new HashSet<>();
        executor.shutdownNow();
        return bestResult;
    }

    private Callable<Path> createCallableSolver(final ISolver solver, final Node start, final Node end) {
        return () -> solver.findPath(start, end);
    }

}
