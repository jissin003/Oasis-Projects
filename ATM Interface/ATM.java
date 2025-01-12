import java.util.*;

class ATM {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            User user = bank.validateUser(userId, pin);
            if (user != null) {
                System.out.println("Login successful!");
                performOperations(user);
            } else {
                System.out.println("Invalid User ID or PIN. Try again.");
            }
        }
    }

    private static void performOperations(User user) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> user.getAccount().showTransactionHistory();
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    user.getAccount().withdraw(amount);
                }
                case 3 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    user.getAccount().deposit(amount);
                }
                case 4 -> {
                    System.out.print("Enter recipient User ID: ");
                    String recipientId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double amount = scanner.nextDouble();
                    User recipient = bank.getUser(recipientId);
                    if (recipient != null) {
                        user.getAccount().transfer(amount, recipient.getAccount());
                    } else {
                        System.out.println("Recipient not found.");
                    }
                }
                case 5 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

class User {
    private final String userId;
    private final String pin;
    private final Account account;

    public User(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.account = new Account(initialBalance);
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }
}

class Account {
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount. Try again.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount));
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void transfer(double amount, Account recipientAccount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipientAccount.deposit(amount);
            transactions.add(new Transaction("Transfer", amount));
            System.out.println("Transferred: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void showTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

class Transaction {
    private final String type;
    private final double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " - Amount: $" + amount;
    }
}

class Bank {
    private final Map<String, User> users = new HashMap<>();

    public Bank() {
        // Sample users user1 and user2
        users.put("user1", new User("user1", "1234", 1000.0));
        users.put("user2", new User("user2", "5678", 2000.0));
    }

    public User validateUser(String userId, String pin) {
        User user = users.get(userId);
        return (user != null && user.getPin().equals(pin)) ? user : null;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
