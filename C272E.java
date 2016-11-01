// Challenge Link: redd.it/4oylbo
// Usage: C272E <tiles>
// Outputs the letters left in a scrabble bag based on tiles already in play

public class C272E {

    private static char[] tiles = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_'};
    private static int[] values = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10, 0 };
    public static void main(String[] args) {
        for(int x=0; x<args.length; x++) {
            System.out.println("Input " + (x+1));
            output(args[x]);
        }
    }

    public static void output(String input) {
        int[] playCount = { 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1, 2 };
        int[] bagCount = playCount.clone();
        for(int i=0; i<input.length(); i++) {
            for(int n=0; n<tiles.length; n++) {
                if(input.charAt(i)==tiles[n]) {
                    bagCount[n]--;
                }
                if(bagCount[n]<0) {
                    System.out.println("Invalid input. More " + tiles[n] + "'s have been taken from the bag then possible.");
                    return;
                }
            }
        }
        for(int i=0; i<playCount.length; i++) {
            playCount[i] = playCount[i] - bagCount[i];
        }
        System.out.print("Set in Bag: ");
        print(bagCount);
        System.out.println("Value in bag: " + value(bagCount));
        System.out.print("Set in play:");
        print(playCount);
        System.out.println("Value in play: " + value(playCount));
        System.out.println();
    }

    public static void print( int[] count ) {
        for(int i=12; i>=0; i--) {
            boolean header = true;
            for(int n=0; n<count.length; n++) {
                if(count[n]==i && header) {
                    System.out.println();
                    System.out.print(i + ": " + tiles[n]);
                    header = false;
                }
                else if(count[n]==i)
                    System.out.print(" " + tiles[n]);
            }
        }
        System.out.println();
    }

    public static int value( int[] count ) {
        int value = 0;
        for(int i=0; i<count.length; i++) {
            value = value + count[i] * values[i];
        }
        return value;
    }
}
