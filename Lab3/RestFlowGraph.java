import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;

import java.util.LinkedList;

public class RestFlowGraph {

    Kattio io;

    int v;
    int s;
    int t;
    int e;

    Node[] nodes;
    Edge[] ogEdges;

    RestFlowGraph() {
        io = new Kattio(System.in, System.out);

        constructFlowGraph();
        int maxflow = edmondsKarp();
        System.out.println(maxflow);

        io.close();
    }

    void constructFlowGraph() {
        this.v = io.getInt(); // Upp till 2000 hörn
        this.s = io.getInt();
        this.t = io.getInt();
        this.e = io.getInt(); // Upp till 100000 kanter

        this.nodes = new Node[v + 1];
        this.ogEdges = new Edge[e];

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

            this.ogEdges[i] = this.nodes[a].addEdge(this.nodes[b], c);

        }

        for (Node node : this.nodes) {
            System.out.println("Node " + (node != null ? node.id : "null") + " edges:");
            if (node != null) {
                node.printEdges();
            }
            System.out.println();
        }
    }

    class Path {
        LinkedList<Edge> edgesInPath;
        int minCapacity;

        Path() {
            this.edgesInPath = new LinkedList<>();
            this.minCapacity = Integer.MAX_VALUE;
        }

        void addEdge(Edge edge) {
            this.edgesInPath.add(edge);
            if (edge.residualCapacity() < this.minCapacity) {
                this.minCapacity = edge.residualCapacity();
            }
        }
    }

    /* { */
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
    /*
     * for (Node node : this.nodes) {
     * if (node == null)
     * continue;
     * for (Edge edge : node.edges) {
     * edge.flow = 0;
     * edge.reverseEdge.flow = 0;
     * edge.restFlow = edge.capacity;
     * edge.reverseEdge.restFlow = edge.reverseEdge.capacity;
     * }
     * }
     * 
     * Node sNode = this.nodes[1];
     * Node tNode = this.nodes[this.nodes.length - 1];
     * }
     */

    int edmondsKarp() {
        int flow = 0;
        Edge[] path = new Edge[v + 1];
        // path!= nulll
        while (bfs(this.nodes, this.s, this.t, path)) {
            // We have to save the smallest capacity in the path somewhere to use r
            int r = Integer.MAX_VALUE;
            for (Edge edge = path[t]; edge != null; edge = path[edge.from()]) {
                r = Math.min(r, edge.residualCapacity());
            }

            for (Edge edge = path[t]; edge != null; edge = path[edge.from()]) {
                edge.addFlow(r);
            }

            flow += r;

            /*
             * edge.flow += r;
             * edge.reverseEdge.flow = -edge.flow;
             * 
             * edge.restFlow = edge.capacity - edge.flow;
             * edge.reverseEdge.restFlow = edge.reverseEdge.capacity -
             * edge.reverseEdge.flow;}
             */

            // path = bFS(sNode, tNode);
        }
        return flow;

    }

    boolean bfs(Node[] nodes, int s, int t, Edge[] path) {
        // maybe add an array that keeps track of the paths
        boolean[] vis = new boolean[this.v + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(s);
        vis[s] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : nodes[u].edges) {
                int v = e.to.id;
                if (!vis[v] && e.residualCapacity() > 0) {
                    vis[v] = true;
                    path[v] = e;
                    if (v == t)
                        return true; // found shortest augmenting path
                    q.add(v);
                }
            }
        }
        return false;
    }

    // Path bFS(Node startNode, Node targetNode) {
    // // While searching for path, save smallest capacity
    // Path path = new Path();

    // for (int i = 1; i < this.nodes.length; i++) {
    // this.nodes[i].priority = -1; // -1 istället för oändlighet
    // }

    // boolean[] visited = new boolean[this.v + 1];

    // Queue<Node> q = new ArrayDeque<>();

    // q.add(startNode);
    // startNode.priority = 0;

    // while (!q.isEmpty()) {
    // Node current = q.poll();

    // for (Edge edge : current.edges) {
    // Node neighbor = edge.to;

    // if (neighbor.priority == -1) {
    // q.add(neighbor);
    // neighbor.priority = current.priority + 1;

    // }
    // }
    // }

    // return null;
    // }

    public static void main(String args[]) {
        new RestFlowGraph();
    }
}
