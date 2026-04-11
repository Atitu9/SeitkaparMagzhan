import java.util.*;

class BankAccount {
    int accountNumber;
    String username;
    double balance;

    BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
    @Override
    public String toString() {
        return accountNumber + " | " + username + " | Balance: " + balance;
    }
}
class AccountRequest {
    int accountNumber;
    String username;
    double balance;

    AccountRequest(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
    @Override
    public String toString() {
        return accountNumber + " | " + username + " | Initial balance: " + balance;
    }
}
public class Main {
    static Scanner sc = new Scanner(System.in);

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<AccountRequest> requestQueue = new LinkedList<>();

    public static void main(String[] args) {
        accounts.add(new BankAccount(1001, "Ali", 150000));
        accounts.add(new BankAccount(1002, "Sara", 220000));
        showArrayTask();
        while (true) {
            System.out.println("\n=== MINI BANKING SYSTEM ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> bankMenu();
                case 2 -> atmMenu();
                case 3 -> adminMenu();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    static void bankMenu() {
        while (true) {
            System.out.println("\n___ BANK MENU ___");
            System.out.println("1 - Add account");
            System.out.println("2 - Display accounts");
            System.out.println("3 - Search by username");
            System.out.println("4 - Deposit");
            System.out.println("5 - Withdraw");
            System.out.println("6 - Add bill payment");
            System.out.println("7 - Display bill queue");
            System.out.println("8 - Submit account request");
            System.out.println("9 - Show history");
            System.out.println("10 - Peek last transaction");
            System.out.println("11 - Undo last transaction");
            System.out.println("12 - Back");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> addAccount();
                case 2 -> displayAccounts();
                case 3 -> searchAccount();
                case 4 -> deposit();
                case 5 -> withdraw();
                case 6 -> addBill();
                case 7 -> showBills();
                case 8 -> submitRequest();
                case 9 -> showHistory();
                case 10 -> peekHistory();
                case 11 -> undoHistory();
                case 12 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    static void atmMenu() {
        while (true) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1 - Balance enquiry");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Back");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> balanceEnquiry();
                case 2 -> withdraw();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1 - View account requests");
            System.out.println("2 - Process next account request");
            System.out.println("3 - View bill queue");
            System.out.println("4 - Process next bill payment");
            System.out.println("5 - Back");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> showRequests();
                case 2 -> processRequest();
                case 3 -> showBills();
                case 4 -> processBill();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    static BankAccount findAccount(String username) {
        for (BankAccount a : accounts) {
            if (a.username.equalsIgnoreCase(username)) return a;
        }
        return null;
    }
    static void addAccount() {
        System.out.print("Enter account number: ");
        int num = readInt();
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter balance: ");
        double bal = readDouble();

        accounts.add(new BankAccount(num, user, bal));
        System.out.println("Account added successfully.");
    }
    static void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts.");
            return;
        }
        int i = 1;
        for (BankAccount a : accounts) {
            System.out.println(i++ + ". " + a.username + " - Balance: " + a.balance);
        }
    }
    static void searchAccount() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        BankAccount a = findAccount(user);
        System.out.println(a == null ? "Account not found." : a);
    }
    static void deposit() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        BankAccount a = findAccount(user);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Deposit amount: ");
        double amount = readDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        a.balance += amount;
        history.push("Deposit " + amount + " to " + user);
        System.out.println("New balance: " + a.balance);
    }
    static void withdraw() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        BankAccount a = findAccount(user);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Withdraw amount: ");
        double amount = readDouble();

        if (amount <= 0 || amount > a.balance) {
            System.out.println("Invalid operation.");
            return;
        }
        a.balance -= amount;
        history.push("Withdraw " + amount + " from " + user);
        System.out.println("New balance: " + a.balance);
    }
    static void addBill() {
        System.out.print("Enter bill name: ");
        String bill = sc.nextLine();
        billQueue.offer(bill);
        history.push("Bill payment added: " + bill);
        System.out.println("Added: " + bill);
    }
    static void showBills() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }
        System.out.println("Bill Queue:");
        for (String bill : billQueue) System.out.println(bill);
    }
    static void processBill() {
        if (billQueue.isEmpty()) {
            System.out.println("No bills to process.");
            return;
        }
        String bill = billQueue.poll();
        history.push("Bill processed: " + bill);
        System.out.println("Processing: " + bill);
    }
    static void submitRequest() {
        System.out.print("Enter account number: ");
        int num = readInt();
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter initial balance: ");
        double bal = readDouble();

        requestQueue.offer(new AccountRequest(num, user, bal));
        System.out.println("Account opening request submitted.");
    }
    static void showRequests() {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        for (AccountRequest r : requestQueue) System.out.println(r);
    }
    static void processRequest() {
        if (requestQueue.isEmpty()) {
            System.out.println("No requests to process.");
            return;
        }
        AccountRequest r = requestQueue.poll();
        accounts.add(new BankAccount(r.accountNumber, r.username, r.balance));
        System.out.println("Processed: " + r.username + " added to main account list.");
    }
    static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions.");
            return;
        }
        for (String h : history) System.out.println(h);
    }
    static void peekHistory() {
        System.out.println(history.isEmpty() ? "No transactions." : "Last transaction: " + history.peek());
    }
    static void undoHistory() {
        System.out.println(history.isEmpty() ? "No transactions to undo." : "Undo -> " + history.pop());
    }
    static void balanceEnquiry() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        BankAccount a = findAccount(user);
        System.out.println(a == null ? "Account not found." : "Current balance: " + a.balance);
    }
    static void showArrayTask() {
        BankAccount[] arr = {
                new BankAccount(2001, "Arman", 100000),
                new BankAccount(2002, "Dana", 250000),
                new BankAccount(2003, "Aruzhan", 175000)
        };
        System.out.println("\nPhysical Data Structure - BankAccount[3]:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println((i + 1) + ". " + arr[i]);
        }
    }
    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter integer: ");
            sc.next();
        }
        int x = sc.nextInt();
        sc.nextLine();
        return x;
    }
    static double readDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Enter number: ");
            sc.next();
        }
        double x = sc.nextDouble();
        sc.nextLine();
        return x;
    }
}