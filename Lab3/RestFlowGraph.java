import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;

public class RestFlowGraph {

    Kattio io;

    int v;
    int e;

    Node[] nodes;

    RestFlowGraph() {
        io = new Kattio(System.in, System.out);

        constructFlowGraph();

        io.close();
    }

    void constructFlowGraph() {
        int v = io.getInt(); // Upp till 2000 hörn
        int s = io.getInt();
        int t = io.getInt();
        int e = io.getInt(); // Upp till 100000 kanter

        this.nodes = new Node[v + 1];

        for (int i = 0; i < e; ++i) {
            int a = io.getInt();
            int b = io.getInt();
            int c = io.getInt();

            Node tempNode;

            if (this.nodes[a] == null) {
                tempNode = new Node(a);
                this.nodes[a] = tempNode;
            }

            if (this.nodes[b] == null) {
                tempNode = new Node(b);
                this.nodes[b] = tempNode;
            }

            this.nodes[a].addEdge(this.nodes[b], c);
        }

        for (Node node : this.nodes) {
            System.out.println("Node " + (node != null ? node.id : "null") + " edges:");
            if (node != null) {
                node.printEdges();
            }
            System.out.println();
        }
    }

    void fordFulkerson() {
        // c[u,v] är kapaciteten från u till v, f[u,v] är flödet, cf[u,v] är
        // restkapaciteten.

        // for varje kant (u,v) i grafen do
        // -- f[u,v]:=0; f[v,u]:=0
        // -- cf[u,v]:=c[u,v];
        // -- cf[v,u]:=c[v,u];
        // while det finns en stig p från s till t i restflödesgrafen do
        // -- r:=min(cf[u,v]: (u,v) ingår i p)
        // -- for varje kant (u,v) i p do
        // -- -- f[u,v]:=f[u,v]+r; f[v,u]:= -f[u,v]
        // -- -- cf[u,v]:=c[u,v] - f[u,v]; cf[v,u]:=c[v,u] - f[v,u]

        for (int i = 1; i < this.nodes.length; i++) {
            for (Edge edge : nodes[i].edges) {
                edge.flow = 0;
            }
        }

        Node s = this.nodes[1];
        Node t = this.nodes[this.nodes.length - 1];

        Edge[] path = bFS(s, t);

        while (path != null) {
            if () {

            }
            else {
                
            }


            

            path = bFS(s, t);
        }
    }

    Edge[] bFS(Node startNode, Node targetNode) {
        for (int i = 1; i < this.nodes.length; i++) {
            this.nodes[i].priority = -1; // -1 istället för oändlighet
        }

        boolean[] visited = new boolean[v + 1];

        Queue<Node> q = new ArrayDeque<>();

        q.add(startNode);
        startNode.priority = 0;

        while (!q.isEmpty()) {
            Node current = q.poll();

            for (Edge edge : current.edges) {
                Node neighbor = edge.to;

                if (neighbor.priority == -1) {
                    q.add(neighbor);
                    neighbor.priority = current.priority + 1;

                }
            }
        }

        return null;
    }

    public static void main(String args[]) {
        new RestFlowGraph();
    }
}
