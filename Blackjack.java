import java.util.*;

public class Blackjack {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("🎲 Welcome to Blackjack!");
        int playerMoney = 100;

        while (playerMoney > 0) {
            System.out.println("\nYour current balance: $" + playerMoney);
            System.out.print("Enter your bet: ");
            if (!scanner.hasNext()) {
                break;
            }
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a whole number for your bet.");
                scanner.nextLine();
                continue;
            }
            int bet = scanner.nextInt();
            scanner.nextLine(); // clear newline

            if (bet <= 0 || bet > playerMoney) {
                System.out.println("Invalid bet!");
                continue;
            }

            List<String> playerHand = new ArrayList<>();
            List<String> dealerHand = new ArrayList<>();

            playerHand.add(drawCard());
            playerHand.add(drawCard());
            dealerHand.add(drawCard());
            dealerHand.add(drawCard());

            System.out.println("\nYour hand: " + playerHand + " (Total: " + getHandValue(playerHand) + ")");
            System.out.println("Dealer shows: " + dealerHand.get(0));

            // Player turn
            while (true) {
                System.out.print("Do you want to (H)it or (S)tand? ");
                if (!scanner.hasNextLine()) {
                    System.out.println("\nGame over. Final balance: $" + playerMoney);
                    return;
                }
                String move = scanner.nextLine().trim();

                if (move.equalsIgnoreCase("H")) {
                    playerHand.add(drawCard());
                    System.out.println("Your hand: " + playerHand + " (Total: " + getHandValue(playerHand) + ")");
                    if (getHandValue(playerHand) > 21) {
                        System.out.println("💥 You bust!");
                        playerMoney -= bet;
                        break;
                    }
                } else if (move.equalsIgnoreCase("S")) {
                    break;
                } else {
                    System.out.println("Invalid input.");
                }
            }

            // Dealer's turn (if player hasn't busted)
            if (getHandValue(playerHand) <= 21) {
                System.out.println("\nDealer's hand: " + dealerHand + " (Total: " + getHandValue(dealerHand) + ")");
                while (getHandValue(dealerHand) < 17) {
                    dealerHand.add(drawCard());
                    System.out.println("Dealer hits: " + dealerHand + " (Total: " + getHandValue(dealerHand) + ")");
                }

                int playerTotal = getHandValue(playerHand);
                int dealerTotal = getHandValue(dealerHand);

                if (dealerTotal > 21 || playerTotal > dealerTotal) {
                    System.out.println("🎉 You win!");
                    playerMoney += bet;
                } else if (playerTotal == dealerTotal) {
                    System.out.println("🤝 Push! It's a tie.");
                } else {
                    System.out.println("😞 Dealer wins.");
                    playerMoney -= bet;
                }
            }

            System.out.print("Play again? (Y/N): ");
            String again = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
            if (!again.equalsIgnoreCase("Y")) break;
        }

        System.out.println("Game over. Final balance: $" + playerMoney);
    }

    static String drawCard() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        return ranks[random.nextInt(ranks.length)];
    }

    static int getHandValue(List<String> hand) {
        int value = 0;
        int aceCount = 0;

        for (String card : hand) {
            switch (card) {
                case "J", "Q", "K" -> value += 10;
                case "A" -> {
                    value += 11;
                    aceCount++;
                }
                default -> value += Integer.parseInt(card);
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }
}
