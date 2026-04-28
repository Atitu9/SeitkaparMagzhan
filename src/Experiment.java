import java.util.Arrays;

public class Experiment {
    private final Sorter sorter;
    private final Searcher searcher;

    public Experiment(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }
    public long measureSortTime(int[] arr, String type) {
        int[] copy = Arrays.copyOf(arr, arr.length);

        long start = System.nanoTime();
        if ("basic".equalsIgnoreCase(type)) {
            sorter.basicSort(copy);
        } else if ("advanced".equalsIgnoreCase(type)) {
            sorter.advancedSort(copy);
        } else {
            throw new IllegalArgumentException("Unknown sort type: " + type);
        }
        long end = System.nanoTime();
        return end - start;
    }
    public long measureSearchTime(int[] arr, int target) {
        int[] sortedCopy = Arrays.copyOf(arr, arr.length);
        sorter.advancedSort(sortedCopy);

        long start = System.nanoTime();
        searcher.search(sortedCopy, target);
        long end = System.nanoTime();

        return end - start;
    }
    public void runAllExperiments() {
        int[] sizes = {10, 100, 5000};

        System.out.println("    Assignment 3: Sorting and Searching Performance Analysis ");
        System.out.println("Selected algorithms: Bubble Sort (basic), Merge Sort (advanced), Binary Search (search)\n");

        printTableHeader();

        for (int size : sizes) {
            int[] randomArr = sorter.generateRandomArray(size);
            int[] sortedArr = Arrays.copyOf(randomArr, randomArr.length);
            sorter.advancedSort(sortedArr);

            runCase(size, "Random", randomArr);
            runCase(size, "Sorted", sortedArr);
        }
    }
    private void runCase(int size, String inputType, int[] arr) {
        long basicTime = measureSortTime(arr, "basic");
        long advancedTime = measureSortTime(arr, "advanced");
        int target = arr.length == 0 ? 0 : arr[arr.length / 2];
        long searchTime = measureSearchTime(arr, target);
        System.out.printf("| %5d | %-6s | %18d | %18d | %17d |%n", size, inputType, basicTime, advancedTime, searchTime);
    }
    private void printTableHeader() {
        System.out.println("| Size  | Input  |    Bubble Sort    |    Merge Sort     |    Binary Search   |");
        System.out.println("|-------|--------|-------------------|-------------------|--------------------|");
    }
}
