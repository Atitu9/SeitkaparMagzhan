import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Searcher searcher = new Searcher();
        Experiment experiment = new Experiment(sorter, searcher);

        int[] demo = sorter.generateRandomArray(10);
        int[] demoBasic = Arrays.copyOf(demo, demo.length);
        int[] demoAdvanced = Arrays.copyOf(demo, demo.length);

        System.out.println("Demo array (size 10):");
        sorter.printArray(demo);

        sorter.basicSort(demoBasic);
        System.out.println("After Bubble Sort:");
        sorter.printArray(demoBasic);

        sorter.advancedSort(demoAdvanced);
        System.out.println("After Merge Sort:");
        sorter.printArray(demoAdvanced);

        int target = demoAdvanced[demoAdvanced.length / 2];
        int index = searcher.search(demoAdvanced, target);
        System.out.printf("Binary Search target %d found at index: %d%n%n", target, index);

        experiment.runAllExperiments();
    }
}
