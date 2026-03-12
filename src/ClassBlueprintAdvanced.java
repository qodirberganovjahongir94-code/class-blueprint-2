/**
 * Advanced: Class with static vs instance, multiple constructors,
 * method chaining (fluent API), toString/equals.
 */
public class ClassBlueprintAdvanced {

    static class BankAccount {
        private static int nextId = 1000;      // class-level (shared)
        private final int  id;                 // instance-level
        private final String owner;
        private double balance;
        private int transactionCount;

        // Constructor 1: start with 0 balance
        BankAccount(String owner) {
            this(owner, 0.0);
        }

        // Constructor 2: start with initial deposit
        BankAccount(String owner, double initialBalance) {
            this.id      = nextId++;
            this.owner   = owner;
            this.balance = initialBalance;
        }

        // Method chaining (fluent API)
        BankAccount deposit(double amount) {
            if (amount > 0) { balance += amount; transactionCount++; }
            return this;   // return this for chaining
        }

        BankAccount withdraw(double amount) {
            if (amount > 0 && amount <= balance) { balance -= amount; transactionCount++; }
            else System.out.println("  Withdraw $" + amount + " failed.");
            return this;
        }

        void printStatement() {
            System.out.printf("Account #%d | Owner: %-10s | Balance: $%8.2f | Transactions: %d%n",
                id, owner, balance, transactionCount);
        }

        @Override public String toString() {
            return "BankAccount{id=" + id + ", owner=" + owner + ", balance=" + balance + "}";
        }

        static int totalAccounts() { return nextId - 1000; }
    }

    public static void main(String[] args) {
        System.out.println("=== Bank Account System ===");
        BankAccount alice = new BankAccount("Alice", 1000.0);
        BankAccount bob   = new BankAccount("Bob");    // starts at 0
        BankAccount carol = new BankAccount("Carol", 500.0);

        // Method chaining
        alice.deposit(500).deposit(200).withdraw(150).withdraw(50);
        bob.deposit(1000).withdraw(200).deposit(300);
        carol.deposit(1000).withdraw(2000).deposit(100);   // one withdraw fails

        System.out.println("\n=== Statements ===");
        alice.printStatement();
        bob.printStatement();
        carol.printStatement();

        System.out.println("\nTotal accounts opened: " + BankAccount.totalAccounts());
        System.out.println("toString: " + alice);
    }
}
