public class Edge {
    Node to;

    int capacity;
    int flow;

    Edge restEdge;

    Edge(Node from, Node to, int capacity) {
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;

        this.restEdge = new Edge(to, from, 0, this);
        to.addEdge(this.restEdge);
    }

    Edge(Node from, Node to, int capacity, Edge restEdge) {
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
        this.restEdge = restEdge;
    }
}
