import java.util.*;

public class Combine {

    Kattio io;

    int x, y, e;
    int[][] edgesXY;

    int v, s, t;
    Node[] nodes;
    Edge[] ogEdges;

    public static void main(String[] args) {
        new Combine();
    }

    Combine() {
        io = new Kattio(System.in, System.out);

        readBipartite();
        buildFlowFromMatching();
        int maxflow = edmondsKarp();
        writeMatchingFromFlow();

        io.flush();
        io.close();
    }

    void readBipartite() {
        x = io.getInt();
        y = io.getInt();
        e = io.getInt();

        edgesXY = new int[e][2];
        ogEdges = new Edge[e];

        for (int i = 0; i < e; i++) {
            int a = io.getInt();
            int b = io.getInt();
            edgesXY[i][0] = a;
            edgesXY[i][1] = b;
        }
    }

    void buildFlowFromMatching() {
        v = x + y + 2;
        s = 1;
        t = v;

        nodes = new Node[v + 1];
        for (int i = 1; i <= v; i++) {
            nodes[i] = new Node(i);
        }

        // s till X
        for (int i = 2; i < x + 2; i++) {
            nodes[s].addEdge(nodes[i], 1);
        }

        // a till b
        for (int i = 0; i < e; i++) {
            int a = edgesXY[i][0] + 1;
            int b = edgesXY[i][1] + 1;
            ogEdges[i] = nodes[a].addEdge(nodes[b], 1);
        }

        // Y till t
        for (int i = x + 2; i < x + y + 2; i++) {
            nodes[i].addEdge(nodes[t], 1);
        }
    }

    int edmondsKarp() {
        int flow = 0;
        Edge[] parent = new Edge[v + 1];

        while (bfs(parent)) {
            int r = Integer.MAX_VALUE;
            for (Edge e = parent[t]; e != null; e = parent[e.from()]) {
                r = Math.min(r, e.residualCapacity());
            }

            for (Edge e = parent[t]; e != null; e = parent[e.from()]) {
                e.addFlow(r);
            }

            flow += r;
        }
        return flow;
    }

    boolean bfs(Edge[] parent) {
        Arrays.fill(parent, null);
        boolean[] vis = new boolean[v + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        vis[s] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (this.nodes[current] == null)
                continue;
            for (Edge e : nodes[current].edges) {
                int neighborId = e.to.id;
                if (!vis[neighborId] && e.residualCapacity() > 0) {
                    vis[neighborId] = true;
                    parent[neighborId] = e;
                    if (neighborId == t)
                        return true;
                    queue.add(neighborId);
                }
            }
        }
        return false;
    }

    void writeMatchingFromFlow() {
        io.println(x + " " + y);

        int m = 0;
        for (Edge e : ogEdges)
            if (e.flow > 0)
                m++;
        io.println(m);

        for (Edge e : ogEdges) {
            if (e.flow > 0) {
                int a = e.from() - 1;
                int b = e.to.id - 1;
                io.println(a + " " + b);
            }
        }
    }
}
