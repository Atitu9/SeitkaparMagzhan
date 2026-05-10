import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Edge>> adjacencyList; // БЫЛО: List<Integer>

    public Graph() {
        vertices      = new LinkedHashMap<>();
        adjacencyList = new LinkedHashMap<>();
    }
    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
        }
    }
    public void addEdge(int from, int to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            System.out.println("Warning: vertex not found (" + from + " or " + to + ")");
            return;
        }
        Vertex src = vertices.get(from);
        Vertex dst = vertices.get(to);
        adjacencyList.get(from).add(new Edge(src, dst)); // БЫЛО: .add(to)
    }
    public void printGraph() {
        System.out.println("Graph adjacency list:");
        for (Map.Entry<Integer, List<Edge>> entry : adjacencyList.entrySet()) {
            Vertex v = vertices.get(entry.getKey());
            System.out.print("  " + v + " -- [");
            List<Edge> edges = entry.getValue();
            for (int i = 0; i < edges.size(); i++) {
                System.out.print(edges.get(i).getDestination());
                if (i < edges.size() - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
    public void bfs(int start) {
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.print("BFS from V" + start + ": ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(vertices.get(current) + " ");
            for (Edge edge : adjacencyList.get(current)) {
                int neighborId = edge.getDestination().getId();
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    queue.add(neighborId);
                }
            }
        }
        System.out.println();
    }
    public void dfs(int start) {
        if (!vertices.containsKey(start)) return;
        Set<Integer> visited = new LinkedHashSet<>();
        System.out.print("DFS from V" + start + ": ");
        dfsRecursive(start, visited);
        System.out.println();
    }
    private void dfsRecursive(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(vertices.get(current) + " ");
        for (Edge edge : adjacencyList.get(current)) { // БЫЛО: for (int neighbor : ...)
            int neighborId = edge.getDestination().getId();
            if (!visited.contains(neighborId)) {
                dfsRecursive(neighborId, visited);
            }
        }
    }
    public int getVertexCount() { return vertices.size(); }
    public int getEdgeCount() {
        int count = 0;
        for (List<Edge> edges : adjacencyList.values()) count += edges.size();
        return count;
    }
}