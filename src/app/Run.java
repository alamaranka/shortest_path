package app;

import generators.GraphGenerator;
import model.GurobiSolver;

public class Run {

    public static void main(String[] args) {
        new GurobiSolver().buildAndSolve(new GraphGenerator().generate(), 6, 7);
    }

}
