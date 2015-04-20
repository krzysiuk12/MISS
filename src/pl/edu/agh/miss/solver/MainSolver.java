package pl.edu.agh.miss.solver;

import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.problem.Problem;
import pl.edu.agh.miss.problem.ProblemUnit;
import pl.edu.agh.miss.vertices.Vertex;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class MainSolver {

    public static Path solveProblem(Problem problem) {
        DijkstraSolver dijkstraSolver = getSolver(problem);
        Path resultPath = new Path();
        resultPath.setSource(problem.getSource());
        Vertex currentVertex = problem.getSource();
        while(currentVertex != problem.getTarget()) {
            dijkstraSolver.computePaths(currentVertex);
            Path currentPath = dijkstraSolver.getShortestPathToTarget(problem.getTarget());
            Vertex nextVertex = currentPath.getNextVertex(currentVertex);

            System.out.println("Current Path: " + currentPath);
            System.out.println("Current Step: " + currentVertex + " -> " + nextVertex);

            resultPath.getMiddleVertices().add(nextVertex);
            currentVertex = nextVertex;
            //TODO: SYMULACJA WARUNKOW

        }
        resultPath.setTarget(resultPath.getMiddleVertices().remove(resultPath.getMiddleVertices().size() - 1));
        return resultPath;
    }

    private static DijkstraSolver getSolver(Problem problem) {
        if(problem.getProblemUnit() == ProblemUnit.DISTANCE) {
            return new DistanceDijkstraSolver();
        } else {
            return new DurationDijkstraSolver();
        }
    }

}
