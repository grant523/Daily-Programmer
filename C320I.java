// Challenge Link: redd.it/6ilyfi
// Determines the outcome of a game of War (card game) given the initial decks of both players
// Input in the form of: 1 2 3 1 4 4
//                       2 1 4 5 3 1
// where the first line is player 1's deck and the second line is player 2's deck

import java.util.*;

public class C320I {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the decks: ");
        ArrayList<Integer> deck1 = createDeck(sc.nextLine());
        ArrayList<Integer> deck2 = createDeck(sc.nextLine());

        int b = 1;
        while (!deck1.isEmpty() && !deck2.isEmpty()) {

            int card1 = deck1.get(0);
            int card2 = deck2.get(0);
            deck1.remove(0);
            deck2.remove(0);

            System.out.println("Battle " + b);
            System.out.println("Cards in play: " + card1 + " vs. " + card2);

            int result;

            if (card1 > card2)
                result = 1;
            else if (card1 < card2)
                result = 2;
            else
                result = war(deck1, deck2);

            if (result == 1) {
                System.out.println("Player 1 wins the battle");
                deck1.add(card1);
                deck1.add(card2);
            } else if (result == 2) {
                System.out.println("Player 2 wins the battle");
                deck2.add(card2);
                deck2.add(card1);
            } else {
                System.out.println("\nGame over! It's a tie");
                System.exit(0);
            }

            System.out.println("Resulting decks:");
            System.out.println("Deck 1: " + deck1);
            System.out.println("Deck 2: " + deck2 + "\n");

            b++;
        }

        if (deck1.isEmpty())
            System.out.println("Game over! Player 2 wins");
        else if (deck2.isEmpty())
            System.out.println("Game over! Player 1 wins");
    }

    // Creates an ArrayList containing the contents of a deck given an input string
    // First element in the ArrayList is the top of the deck
    private static ArrayList<Integer> createDeck(String cards) {
        Scanner line = new Scanner(cards);
        ArrayList<Integer> deck = new ArrayList<Integer>();
        while (line.hasNext()) {
            deck.add(line.nextInt());
        }
        return deck;
    }

    // War function
    // Called on two decks when the two cards in a battle are of equal value
    private static int war(ArrayList<Integer> deck1, ArrayList<Integer> deck2) {
        System.out.println("It's a tie! War commencing");
        int n = 4;
        int r = 0;

        if (deck1.isEmpty() && deck2.isEmpty()) {
            System.out.println("Both players are out of cards!");
            return 0;
        }
        else if (deck1.isEmpty())
            return 1;
        else if (deck2.isEmpty())
            return 2;


        if (deck1.size() < 4 || deck2.size() < 4) {
            n = Math.min(deck1.size(), deck2.size());
        }

        // Remove up to four (as many as possible) cards from the top of each deck and store them for comparison
        // and re-distribution (to the winner)
        ArrayList<Integer> draw1 = new ArrayList<Integer>(deck1.subList(0, n));
        deck1.subList(0, n).clear(); // deleteRange() is a protected method, not sure why

        ArrayList<Integer> draw2 = new ArrayList<Integer>(deck2.subList(0, n));
        deck2.subList(0, n).clear();

//        ArrayList<Integer> draw1 = new ArrayList<Integer>();
//        for (int i = 0; i < n; i++) {
//            draw1.add(deck1.get(0));
//            deck1.remove(0);
//        }
//
//        ArrayList<Integer> draw2 = new ArrayList<Integer>();
//        for (int i = 0; i < n; i++) {
//            draw2.add(deck2.get(0));
//            deck2.remove(0);
//        }

        System.out.println("Cards in play:");
        System.out.println(draw1);
        System.out.println(draw2);

        System.out.println("Comparing: " + draw1.get(n - 1) + " vs. " + draw2.get(n - 1));
        if (draw1.get(n - 1) > draw2.get(n - 1)) {
            r = 1;
        } else if (draw1.get(n - 1) < draw2.get(n - 1)) {
            r = 2;
        } else {
            r = war(deck1, deck2);
        }

        if (r == 1) {
            System.out.println("Player 1 wins the war");
            deck1.addAll(draw1);
            deck1.addAll(draw2);
        } else if (r == 2) {
            System.out.println("Player 2 wins the war");
            deck2.addAll(draw2);
            deck2.addAll(draw1);
        }

        return r;
    }
}
