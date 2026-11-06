# Java Console Projects

Three standalone Java programs for practicing loops, methods, collections, and console input. The games use simulated money.

| Program | What it does |
| --- | --- |
| Blackjack | Place a bet, hit or stand, and play against the dealer. Aces count as 1 or 11. |
| Banking App | View a simulated balance, deposit funds, and make withdrawals. The balance resets when the app closes. |
| Slot Machine | Place a bet, spin three symbols, and receive a payout for matching adjacent symbols. |

## Run locally

Use JDK 17 or later and open a terminal in this folder. Compile once, then run whichever program you want.

```sh
javac -encoding UTF-8 -d out Blackjack.java BankingApp.java SlotMachine.java
java -cp out Blackjack
java -cp out BankingApp
java -cp out SlotMachine
```

## Example

In Banking App, choose **2** and enter **25** to deposit $25. Then choose **1** to display the balance.

```text
Enter your choice(1-4): 1
**********
$25.00
```

Blackjack and Slot Machine use random draws, so each round can play out differently.
