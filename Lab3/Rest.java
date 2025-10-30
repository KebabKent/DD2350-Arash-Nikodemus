import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;

import java.util.LinkedList;

public class Rest {

    Kattio io;

    int v;
    int s;
    int t;
    int e;

    Node[] nodes;
    Edge[] ogEdges;

    Rest() {
        io = new Kattio(System.in, System.out);

        constructFlowGraph();

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

    void fordFulkerson() {
        for (int i = 1; i < this.nodes.length; i++) {
            for (Edge edge : nodes[i].edges) {
                edge.flow = 0;
                edge.reverseEdge.flow = 0;
                edge.restFlow = edge.capacity;
                edge.reverseEdge.restFlow = edge.reverseEdge.capacity;
            }
        }

        Node sNode = this.nodes[1];
        Node tNode = this.nodes[this.nodes.length - 1];

        Path path = bFS(sNode, tNode);

        while (path != null) {
            // We have to save the smallest capacity in the path somewhere to use r
            int r = path.minCapacity;
            for (Edge edge : path.edgesInPath) {
                edge.flow += r;
                edge.reverseEdge.flow = -edge.flow;

                edge.restFlow = edge.capacity - edge.flow;
                edge.reverseEdge.restFlow = edge.reverseEdge.capacity - edge.reverseEdge.flow;
            }

            path = bFS(sNode, tNode);
        }
    }

    class TreeNode {
        Node node;
        Node previous;
        List<TreeNode> children;

        TreeNode(Node node) {
            this.node = node;
            this.children = new ArrayList<>();
        }

        TreeNode(Node node, Node previous) {
            this.node = node;
            this.previous = previous;
            this.children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            this.children.add(child);
        }
    }

    Path bFS(Node startNode, Node targetNode) {
        // While searching for path, save smallest capacity
        Path path = new Path();

        for (int i = 1; i < this.nodes.length; i++) {
            this.nodes[i].priority = -1; // -1 istället för oändlighet
        }

        boolean[] visited = new boolean[this.v + 1];

        Queue<Node> q = new ArrayDeque<>();

        q.add(startNode);
        startNode.priority = 0;
        TreeNode root = new TreeNode(startNode);

        while (!q.isEmpty()) {
            Node current = q.poll();

            for (Edge edge : current.edges) {
                Node neighbor = edge.to;

                if (neighbor.priority == -1) {
                    q.add(neighbor);
                    neighbor.priority = current.priority + 1;
                    TreeNode childTreeNode = new TreeNode(neighbor);
                }
            }
        }

        return null;
    }

    public static void main(String args[]) {
        new Rest();
    }
}
