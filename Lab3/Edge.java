public class Edge {
    int to;
    int capacity;
    int flow;
    Edge reverse;

    Edge(int to, int capacity) {
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }
}
