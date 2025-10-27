import java.util.List;
import java.util.ArrayList;

public class Node {
    int id;
    List<Edge> edges;

    Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }

    void setEdge(Edge edge) {
        edges.add(edge);
    }
}