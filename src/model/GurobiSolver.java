package model;

import data.Edge;
import generators.DistanceMatrixGenerator;
import data.Graph;
import gurobi.*;

import java.util.ArrayList;
import java.util.List;

public class GurobiSolver implements Solver{
    private GRBEnv env;
    private GRBModel model;
    private List<List<GRBVar>> traverse = new ArrayList<>();

    private Graph graph;
    private double[][] distanceMatrix;
    private int size;
    private int start;
    private int end;

    private void prepareInput (Graph graph, int start, int end){
        this.graph = graph;
        this.distanceMatrix = new DistanceMatrixGenerator(graph).generate();
        this.size = graph.getSize();
        this.start = start;
        this.end = end;
    }

    private void generateEnvAndModel(){
        try {
            this.env = new GRBEnv("ShortestPath");
            this.model = new GRBModel(this.env);
        }
        catch (GRBException e) {
            e.getStackTrace();
        }
    }

    private void generateDecisionVariables(){
        try {
            for (int i=0; i<size; i++) {
                List<GRBVar> traverseLine = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    traverseLine.add(model.addVar(0, 1, 0, GRB.BINARY, ""));
                }
                traverse.add(traverseLine);
            }
        }
        catch (GRBException e){
        }
    }

    private void generateObjectiveFunction(){
        try {
            GRBLinExpr cost = new GRBLinExpr();
            for (int i=0; i<size; i++) {
                for (int j = 0; j < size; j++) {
                    cost.addTerm(distanceMatrix[i][j], traverse.get(i).get(j));
                }
            }
            model.setObjective(cost, GRB.MINIMIZE);
        }
        catch (GRBException e){
        }
    }

    private void flowConservation(){
        try {
            for (int v=0; v<size; v++){
                GRBLinExpr grbLinExprIn = new GRBLinExpr();
                GRBLinExpr grbLinExprOut = new GRBLinExpr();
                for (int i=0; i<size; i++){
                    grbLinExprIn.addTerm(1.0, traverse.get(i).get(v));
                    grbLinExprOut.addTerm(1.0, traverse.get(v).get(i));
                }
                if (graph.getVertices().get(v).getName() == start){
                    grbLinExprOut.addConstant(-1.0);
                    model.addConstr(grbLinExprIn, GRB.EQUAL, grbLinExprOut, "");
                } else if (graph.getVertices().get(v).getName() == end){
                    grbLinExprOut.addConstant(1.0);
                    model.addConstr(grbLinExprIn, GRB.EQUAL, grbLinExprOut, "");
                } else {
                    model.addConstr(grbLinExprIn, GRB.EQUAL, grbLinExprOut, "");
                }
            }
        }
        catch (GRBException e){
        }
    }

    private void generateConstraints(){
        flowConservation();
    }

    private void solve(){
        try {
            model.optimize();
        }
        catch (GRBException e){
        }
    }

    public void buildAndSolve(Graph graph, int start, int end){
        prepareInput(graph, start, end);
        generateEnvAndModel();
        generateDecisionVariables();
        generateObjectiveFunction();
        generateConstraints();
        solve();
        printSolution();
    }

    private void printSolution(){
        System.out.println("Shortest Path:");
        printPath(start);
    }

    private void printPath(int vertexName){
        try {
            if (vertexName==start){
                System.out.print(start);
            }
            for (int i=0; i<size; i++) {
                if (traverse.get(vertexName).get(i).get(GRB.DoubleAttr.X) > 0){
                    if (i==start){break;}
                    System.out.print(" -> " + i);
                    printPath(i);
                }
            }
        }
        catch (GRBException e){
        }
    }

}

