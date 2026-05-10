public class Experiment {
    private long[][] results;
    private String[] labels;
    private int[] graphSizes;
    private int currentIndex;

    public Experiment(int[] graphSizes, String[] labels) {
        this.graphSizes = graphSizes;
        this.labels = labels;
        this.results = new long[graphSizes.length][2];
        this.currentIndex = 0;
    }
    public void runTraversals(Graph g) {
        System.out.println("\n--- " + labels[currentIndex] + " ---");
        System.out.println("Vertices: " + g.getVertexCount() + "  |  Edges: " + g.getEdgeCount());

        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();
        long bfsTime = bfsEnd - bfsStart;

        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();
        long dfsTime = dfsEnd - dfsStart;

        results[currentIndex][0] = bfsTime;
        results[currentIndex][1] = dfsTime;
        currentIndex++;

        System.out.println("BFS time: " + bfsTime + " ns  (" + (bfsTime / 1_000) + " µs)");
        System.out.println("DFS time: " + dfsTime + " ns  (" + (dfsTime / 1_000) + " µs)");
    }
    public void runMultipleTests() {
        System.out.println("========================================");
        System.out.println("   GRAPH TRAVERSAL PERFORMANCE TESTS   ");
        System.out.println("========================================");
    }
    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("      PERFORMANCE COMPARISON TABLE");
        System.out.println("========================================");
        System.out.printf("%-22s %-15s %-15s %-10s%n", "Graph Size", "BFS (ns)", "DFS (ns)", "Faster");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < graphSizes.length; i++) {
            long bfs      = results[i][0];
            long dfs      = results[i][1];
            String faster = (bfs <= dfs) ? "BFS" : "DFS";
            System.out.printf("%-22s %-15d %-15d %-10s%n", graphSizes[i] + " vertices", bfs, dfs, faster);
        }
        System.out.println("========================================");
    }
}