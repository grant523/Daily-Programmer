// Challenge Link: redd.it/4tetif
// Usage: C276E <word> <width> <height>
// Prints rectangles made of input word to specified sizes
public class C276E {

    public static void main(String[] args) {
        String word1 = args[0];
        String word2 = new StringBuilder(word1).reverse().toString();
	int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);

        if(width % 2 == 0) {
            String temp = word1;
            word1 = word2;
            word2 = temp;
        }

        int c = 0;
        for(int i = 0; i < width; i++) {
            if(i == 0) {
                printLine(word1);
            }
            else if(c == 0) {
                printLine(word2.substring(1, word2.length()));
                c = 1;
            }
            else if(c == 1) {
                printLine(word1.substring(1, word1.length()));
                c = 0;
            }
        }
        System.out.println();

    }

    public static void printLine(String s){
        for(int i = 0; i<s.length(); i++) {
            System.out.print(s.charAt(i) + " ");
        }
    }

}
