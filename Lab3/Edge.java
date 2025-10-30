public class Edge {
    Node from;
    Node to;

    int capacity;
    int flow;

    Edge reverseEdge;

    Edge(Node from, Node to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;

        this.reverseEdge = new Edge(from, to, 0, this);
        to.addEdge(this.reverseEdge);
    }

    Edge(Node from, Node to, int capacity, Edge reverseEdge) {
        this.from = to;
        this.to = from;
        this.capacity = capacity;
        this.flow = 0;
        this.reverseEdge = reverseEdge;
    }

    int residualCapacity() {
        return this.capacity - this.flow;
    }

    int from() {
        return this.from.id;
    }

    public void addFlow(int delta) {
        this.flow += delta;
        this.reverseEdge.flow = -this.flow;
    }

}
