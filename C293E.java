// Challenge Link: redd.it/5e4mde
// Usage: C293E <wire1> <wire2> ...
// Outputs the letters left in a scrabble bag based on tiles already in play
public class C293E {
    public static void main(String args[]) {
        for (int i = 1; i < args.length; i++) {
            if(!check(args[i-1], args[i])) {
                System.out.println("boom");
                System.exit(1);
            }
        }
        System.out.println("Bomb defused");
    }

    // Returns true if rules are followed, false otherwise
    private static boolean check(String prev, String curr) {
        if (prev.equals("white")){
            if(curr.equals("white") || curr.equals("black")) {
                return false;
            }
        } else if (prev.equals("red")) {
            if(!curr.equals("green")) {
                return false;
            }
        } else if (prev.equals("black")) {
            if(curr.equals("white")|| curr.equals("green") || curr.equals("orange")) {
                return false;
            }
        } else if (prev.equals("orange")) {
            if(!(curr.equals("red") || curr.equals("black"))){
                return false;
            }
        } else if (prev.equals("green")) {
            if(!(curr.equals("orange") || curr.equals("white"))){
                return false;
            }
        } else if (prev.equals("purple")) {
            if (curr.equals("purple") || curr.equals("green") || curr.equals("orange") || curr.equals("white")) {
                return false;
            }
        }
        return true;
    }
}
