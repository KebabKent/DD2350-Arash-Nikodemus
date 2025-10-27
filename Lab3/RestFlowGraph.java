import java.util.ArrayList;
import java.util.List;

public class RestFlowGraph {
    Node s;
    Node t;

    List<Node> nodesX;
    List<Node> nodesY;

    int x;
    int y;

    RestFlowGraph(int x, int y) {
        this.s = new Node(0);
        this.t = new Node(0);

        this.x = x;
        this.y = y;

        this.nodesX = addNodesX();
        this.nodesY = addNodesY();
    }

    List<Node> addNodesX() {
        List<Node> newNodes = new ArrayList<>();
        for (int i = 1; i <= this.x; i++) {
            Node n = new Node(i);
            Edge e = new Edge(n.id, 1);
            this.s.setEdge(e); // Edge from source to node in X
            newNodes.add(n);
        }
        return newNodes;
    }

    List<Node> addNodesY() {
        List<Node> newNodes = new ArrayList<>();
        for (int i = this.x + 1; i <= this.x + this.y; i++) {
            Node n = new Node(i);
            Edge e = new Edge(this.t.id, 1);
            n.setEdge(e); // Edge from node in Y to sink
            newNodes.add(n);
        }
        return newNodes;
    }
}
