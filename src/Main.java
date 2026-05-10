import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  SMALL GRAPH DEMO  (10 vertices)       ");
        System.out.println("========================================");

        Graph small = new Graph();
        for (int i = 0; i < 10; i++) small.addVertex(new Vertex(i));

        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}, {3,7},{4,8},{5,9},{6,0},{7,1},{8,2},{9,3}
        };
        for (int[] e : edges) small.addEdge(e[0], e[1]);
        small.printGraph();
        System.out.println();
        small.bfs(0);
        small.dfs(0);
        System.out.println();

        int[]    sizes  = {10, 30, 100};
        String[] labels = {"Small  (10  vertices)", "Medium (30  vertices)", "Large  (100 vertices)"
        };
        Experiment experiment = new Experiment(sizes, labels);
        experiment.runMultipleTests();
        for (int i = 0; i < sizes.length; i++) {
            Graph g = buildGraph(sizes[i], 42L);
            experiment.runTraversals(g);
        }
        experiment.printResults();
    }
    private static Graph buildGraph(int size, long seed) {
        Graph g    = new Graph();
        Random rnd = new Random(seed);
        for (int i = 0; i < size; i++) g.addVertex(new Vertex(i));
        for (int i = 0; i < size; i++) {
            for (int e = 0; e < 3; e++) {
                int target = rnd.nextInt(size);
                if (target != i) g.addEdge(i, target);
            }
        }
        return g;
    }
}