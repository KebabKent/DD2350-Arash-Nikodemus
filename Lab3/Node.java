import java.util.List;
import java.util.ArrayList;

public class Node {
    int id;
    List<Edge> edges;

    int priority; // Används i BFS

    Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }

    void addEdge(Node destinationNode, int capacity) {
        Edge edge = new Edge(this, destinationNode, capacity);
        edges.add(edge);
    }

    void addEdge(Edge edge) {
        edges.add(edge);
    }

    void printEdges() {
        for (Edge edge : edges) {
            System.out.println("Edge to node " + edge.to.id + " with capacity " + edge.capacity);
        }
    }
}