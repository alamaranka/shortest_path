package data;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public void addVertex (Vertex v){
        vertices.add(v);
    }

    public void addEdge (Edge e){
        for (Vertex vertex: vertices){
            if (vertex.getName() == e.getStart().getName()){
                vertex.addNeighbor(e.getEnd());
            }
            if (vertex.getName() == e.getEnd().getName()){
                vertex.addNeighbor(e.getStart());
            }
        }
        edges.add(e);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getSize() {
        return this.getVertices().size();
    }
}
