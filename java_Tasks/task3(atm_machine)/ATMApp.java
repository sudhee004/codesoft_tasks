import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }
}

class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readMenuChoice();
            System.out.println();
            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting... Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    private int readMenuChoice() {
        while (true) {
            System.out.print("Select an option (1-4): ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= 1 && value <= 4) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
        }
    }

    public void withdraw(double amount) {
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.printf("Withdrawal successful. Amount withdrawn: %.2f%n", amount);
            checkBalance();
        } else {
            if (amount <= 0) {
                System.out.println("Withdrawal failed. Amount must be greater than 0.");
            } else if (amount > account.getBalance()) {
                System.out.println("Withdrawal failed. Insufficient balance.");
            } else {
                System.out.println("Withdrawal failed.");
            }
        }
    }

    public void deposit(double amount) {
        boolean success = account.deposit(amount);
        if (success) {
            System.out.printf("Deposit successful. Amount deposited: %.2f%n", amount);
            checkBalance();
        } else {
            System.out.println("Deposit failed. Amount must be greater than 0.");
        }
    }

    public void checkBalance() {
        System.out.printf("Current Balance: %.2f%n", account.getBalance());
    }

    private void handleWithdraw() {
        double amount = readPositiveAmount("Enter amount to withdraw: ");
        withdraw(amount);
    }

    private void handleDeposit() {
        double amount = readPositiveAmount("Enter amount to deposit: ");
        deposit(amount);
    }

    private double readPositiveAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid amount. Please enter a number greater than 0.");
        }
    }
}

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to the ATM ===");
        double initialBalance = readNonNegativeInitialBalance(scanner);
        BankAccount account = new BankAccount(initialBalance);
        ATM atm = new ATM(account, scanner);
        System.out.println();
        atm.start();
        scanner.close();
    }

    private static double readNonNegativeInitialBalance(Scanner scanner) {
        while (true) {
            System.out.print("Enter initial account balance (>= 0): ");
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid input. Please enter a number greater than or equal to 0.");
        }
    }
}
