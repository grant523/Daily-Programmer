// Challenge Link: redd.it/5j6ggm
// Prints out the lyrics of Twelve Days of Christmas, with as little code as possible

import java.util.Scanner;

public class C296E {

    private static String[] cardinal = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
    private static String[] ordinal = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] gifts = new String[12];
        System.out.println("Enter a list of twelve gifts: ");
        for (int i = 0; i < 12; i++) {
            gifts[i] = in.nextLine();
        }
        for (int i = 0; i < 12; i++) {
            System.out.println("\nOn the " + ordinal[i] + " day of Christmas\nmy true love sent to me:");
            for (int c = i; c >= 0; c--) {
                if(c==0 && i!= 0)
                    System.out.print("and ");
                System.out.println(cardinal[c] + " " + gifts[c]);
            }
        }
    }
}
