/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.unogame;

/**
 *
 * @author prabs
 */
import java.util.*;

public class GameController {
    private List<Player> players;
    private Deck deck;
    private Deck discardPile;
    private int currentPlayerIndex;
    private int direction = 1;
    private Scanner scanner = new Scanner(System.in);

    public GameController(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.discardPile = new Deck();
        setupDeck();
        deck.shuffle();
    }

    private void setupDeck() {
        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
        for (String color : colors) {
            for (String num : numbers) {
                deck.addCard(new Card(color, num));
                if (!num.equals("0")) {
                    deck.addCard(new Card(color, num));
                }
            }
            deck.addCard(new SpecialCard(color, "Skip", CardType.SKIP));
            deck.addCard(new SpecialCard(color, "Reverse", CardType.REVERSE));
            deck.addCard(new SpecialCard(color, "Draw Two", CardType.DRAW_TWO));
        }
        for (int i = 0; i < 4; i++) {
            deck.addCard(new SpecialCard("Black", "Wild", CardType.WILD));
            deck.addCard(new SpecialCard("Black", "Wild Draw Four", CardType.WILD_DRAW_FOUR));
        }
    }

    public void startGame() {
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.drawCard(deck);
            }
        }
        discardPile.addCard(deck.drawCard());
        playLoop();
    }

    private void playLoop() {
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            Card topCard = discardPile.getCards().get(discardPile.size() - 1);

            System.out.println("\nTop card: " + topCard);
            System.out.println(currentPlayer.getName() + "'s turn. Your hand:");
            for (int i = 0; i < currentPlayer.getHand().getCards().size(); i++) {
                System.out.println((i+1) + ". " + currentPlayer.getHand().getCards().get(i));
            }

            if (!currentPlayer.getHand().hasPlayableCard(topCard)) {
                System.out.println("No playable card. Drawing...");
                currentPlayer.drawCard(deck);
                nextTurn();
                continue;
            }

            System.out.print("Choose a card to play: ");
            int choice = scanner.nextInt() - 1;
            Card chosen = currentPlayer.getHand().getCards().get(choice);
            if (chosen.getColor().equals(topCard.getColor()) || 
                chosen.getValue().equals(topCard.getValue()) || 
                chosen instanceof SpecialCard) {

                currentPlayer.playCard(chosen, discardPile);

                if (chosen instanceof SpecialCard) {
                    ((SpecialCard) chosen).applyEffect(this, currentPlayer);
                }

                if (currentPlayer.getHand().getCards().isEmpty()) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                }
                nextTurn();
            } else {
                System.out.println("Invalid card. Try again next turn.");
                nextTurn();
            }
        }
    }

    public void skipNextPlayer() {
        nextTurn();
    }

    public void reverseDirection() {
        direction *= -1;
    }

    public void drawCardsToNextPlayer(int count) {
        int nextIndex = (currentPlayerIndex + direction + players.size()) % players.size();
        Player nextPlayer = players.get(nextIndex);
        for (int i = 0; i < count; i++) {
            nextPlayer.drawCard(deck);
        }
    }

    public void chooseColor(Player player) {
        System.out.println("Choose a color: 1. Red  2. Blue  3. Green  4. Yellow");
        int choice = scanner.nextInt();
        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        System.out.println(player.getName() + " changed color to " + colors[choice - 1]);
    }

    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
    }
}
