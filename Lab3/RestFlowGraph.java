import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

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
        writeOutput(maxflow);

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

        // for (Node node : this.nodes) {
        // System.out.println("Node " + (node != null ? node.id : "null") + " edges:");
        // if (node != null) {
        // node.printEdges();
        // }
        // System.out.println();
        // }
    }

    int edmondsKarp() {
        int flow = 0;
        Edge[] path = new Edge[v + 1];

        while (bfs(path)) {
            // We have to save the smallest capacity in the path somewhere to use r
            int r = Integer.MAX_VALUE;
            for (Edge edge = path[t]; edge != null; edge = path[edge.from()]) {
                r = Math.min(r, edge.residualCapacity());
            }

            for (Edge edge = path[t]; edge != null; edge = path[edge.from()]) {
                edge.addFlow(r);
            }

            flow += r;
        }
        return flow;
    }

    boolean bfs(Edge[] path) {
        Arrays.fill(path, null);
        // maybe add an array that keeps track of the paths
        boolean[] vis = new boolean[this.v + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(this.s);
        vis[s] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (this.nodes[current] == null)
                continue;
            for (Edge edge : this.nodes[current].edges) {
                int neighborId = edge.to.id;
                if (!vis[neighborId] && edge.residualCapacity() > 0) {
                    vis[neighborId] = true;
                    path[neighborId] = edge;
                    if (neighborId == this.t)
                        return true; // found shortest augmenting path
                    queue.add(neighborId);
                }
            }
        }
        return false;
    }

    void writeOutput(int maxflow) {
        io.println(v);
        io.println(s + " " + t + " " + maxflow);

        int k = 0;
        for (Edge e : ogEdges) {
            if (e.flow > 0)
                k++;
        }
        io.println(k);

        for (Edge e : ogEdges) {
            if (e.flow > 0)
                io.println(e.from() + " " + e.to.id + " " + e.flow);
        }
    }

    public static void main(String args[]) {
        new RestFlowGraph();
    }
}