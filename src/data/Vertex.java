package data;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int name;
    private List<Vertex> neighbors = new ArrayList<>();

    public Vertex (){}

    public Vertex (int name){
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public List<Vertex> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Vertex v) {
        this.neighbors.add(v);
    }

    public boolean isNeighborVertex (Vertex v){
        for (Vertex vertex: neighbors){
            if (vertex.getName() == this.getName()){
                return true;
            }
        }
        return false;
    }
}
