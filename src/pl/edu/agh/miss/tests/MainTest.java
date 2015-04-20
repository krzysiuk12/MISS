package pl.edu.agh.miss.tests;

import pl.edu.agh.miss.path.Path;
import pl.edu.agh.miss.problem.Problem;
import pl.edu.agh.miss.problem.ProblemUnit;
import pl.edu.agh.miss.solver.MainSolver;

/**
 * Created by Krzysztof Kicinger on 2015-04-20.
 */
public class MainTest {

    public static void main(String[] args) {
        IGraphTest graphTest = new GraphTest2();
        Problem problem = new Problem(graphTest.getGraph(), graphTest.getSourceVertex(), graphTest.getTargetVertex(), ProblemUnit.DISTANCE);
        Path result = MainSolver.solveProblem(problem);
        System.out.println("Path: " + result);
    }

}
