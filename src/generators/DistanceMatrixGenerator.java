package generators;

import data.Edge;
import data.Graph;
import data.Vertex;
import java.util.List;

public class DistanceMatrixGenerator {
    private Graph graph;
    private int size;
    private double unconnectedTraversePenalty = 1_000_000;

    public DistanceMatrixGenerator(Graph graph){
        this.graph = graph;
        this.size = graph.getSize();
    }

    public double[][] generate(){

        List<Edge> edges = graph.getEdges();
        double[][] distanceMatrix = new double[size][size];

        for (Edge edge: edges){
            Vertex start = edge.getStart();
            Vertex end = edge.getEnd();
            distanceMatrix[start.getName()][end.getName()] = edge.getWeight();
            distanceMatrix[end.getName()][start.getName()] = edge.getWeight();
        }

        for (int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                if (distanceMatrix[i][j] == 0){
                    distanceMatrix[i][j] = unconnectedTraversePenalty;
                }
            }
        }

        return distanceMatrix;
    }

    public void print(double[][] distanceMatrix) {
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                System.out.print(distanceMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
