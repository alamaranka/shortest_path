package generators;

import data.Edge;
import data.Graph;
import data.Vertex;

public class GraphGenerator {

    public Graph generate() {

        Graph graph = new Graph();

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);

        Edge e0 = new Edge(v0, v1, 3);
        Edge e1 = new Edge(v0, v2, 1);
        Edge e2 = new Edge(v1, v2, 4);
        Edge e3 = new Edge(v2, v3, 5);
        Edge e4 = new Edge(v2, v4, 1);
        Edge e5 = new Edge(v3, v5, 2);
        Edge e6 = new Edge(v5, v6, 4);
        Edge e7 = new Edge(v5, v7, 5);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);

        graph.addEdge(e0);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        graph.addEdge(e5);
        graph.addEdge(e6);
        graph.addEdge(e7);

        return graph;
    }

}
