import java.util.Scanner;

public class BankingApp {
     static Scanner scanner= new Scanner(System.in);

    public static void main(String[] args) {

        double balance=0;
        boolean isRunning= true;
        int choice;
        while (isRunning) {
            System.out.println("***************");
            System.out.println("Banking program");
            System.out.println("**********");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("***************");

            System.out.print("Enter your choice(1-4): ");
            if (!scanner.hasNext()) {
                break;
            }
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a whole number from 1 to 4.");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();

            switch(choice){
                case 1 -> showBalance(balance);
                case 2-> balance += deposit();
                case 3-> balance-=withdraw(balance);
                case 4-> isRunning= false;
                default -> System.out.println("INVALID CHOICE");
            }
        }
        System.out.println("*************************");
        System.out.println(("Thank you! Have a nice day"));
        System.out.println("*************************");
        scanner.close();
    }

    static void showBalance(double balance){
        System.out.println("**********");
        System.out.printf("$%.2f\n", balance);
    }
    static double deposit(){
        double amount;
        System.out.println("Enter an amount to be deposited: ");
        amount = readAmount();
        return amount;

    }
    static double readAmount() {
        while (scanner.hasNext()) {
            if (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid amount:");
                scanner.nextLine();
                continue;
            }
            double amount = scanner.nextDouble();
            if (Double.isFinite(amount) && amount > 0) {
                return amount;
            }
            System.out.println("Please enter a finite amount greater than zero:");
            scanner.nextLine();
        }
        return 0;
    }

    static double withdraw(double balance){
        double amount;
        System.out.print("Enter amount to be withdrawn: ");
        amount = readAmount();
        if (amount> balance){
            System.out.println("Insufficient funds");
            return 0;
        }
        return amount;

    }

}
