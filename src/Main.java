import java.util.Scanner;
static Scanner sc = new Scanner(System.in);
// Task-1
public static void Task1 (int x){
    if (x == 0) return;
    Task1(x/10);
   System.out.println(x%10);
}
// Task-2
 public static int[] Task2 (int[] arr, int i){
    if (i == arr.length) return arr;
    arr[i] = sc.nextInt();
     return Task2(arr, i +1);
}
public static int ave (int[] arr, int x){
    if(x == 0) return 0;
    return arr[x-1] + ave(arr, x - 1);
}
// Task-3
public static boolean Task3 (int x){
    if(x == 0)return false;
    if(x == 1) return true;

    return Task3(x-2);
}
//Task-4
public static int Task4 (int x){
    if(x == 0)return 1;
    return Task4(x-1) * x;
}
//Task-5
public static int Task5(int n) {
    if (n <= 1) return n;
    return Task5(n - 1) + Task5(n - 2);
}
//Task-6
public static int Task6(int n,int a) {
    if(a == 0) return 1;
    return n * Task6(n, a - 1);
}
//Task-7
public static void Task7(int n) {
    if (n == 0) return;
    int a = sc.nextInt();
    Task7(n - 1);
    System.out.print(a + " ");
}
//Task-8
public static boolean Task8(String m) {
    if (m.isEmpty()) return true;
    if(!Character.isDigit(m.charAt(0))) return false;
    return Task8(m.substring(1));
}
//Task-9
public static int Task9(String c) {
    if (c.isEmpty()) return 0;
    return 1 + Task9(c.substring(1));
}
//Task-10
public static int Task10(int a, int b) {
    if (b == 0) return a;
    return Task10(b, a % b);
}
public static void main (String[] args) {
    //int n= sc.nextInt();
    //Task1(n);
    /* Task-2
    int[] arr = new int[n];
    Task2(arr, 0);
    int sum = ave(arr, n);
    double average = (double) sum / n;
    System.out.println(average);*/
    /* Task-3
    if (Task3(n)==true) {
        System.out.println("Prime");
    } else {
        System.out.println("Composite");} */
   /*Task-4
    int f = Task4(n);
    System.out.println(f); */
    //Task-5
   // System.out.println(Task5(n));
    //Task-6
    //System.out.println(Task6(n, a));int a= sc.nextInt();
    //Task7(n);
    /* Task-8
    String m= sc.next();
    if (Task8(m)) {
        System.out.println("Yes");
    } else {
        System.out.println("No");}*/
    /* Task-9
    String c= sc.next();
    System.out.println(Task9(c));*/
    /* Task-10
    int a = sc.nextInt();
    int b = sc.nextInt();
    System.out.println(Task10(a, b));*/
}

