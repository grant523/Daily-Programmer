import java.io.*;
import java.util.*;

public class C294I {

    // Trie data structure for storing/processing word list
    private class Trie {

        private Node root;

        public Trie() {
            root = new Node();
        }

        private class Node {
            char c;
            ArrayList<String> words;
            Node[] arr;

            Node() {
                words = new ArrayList<String>();
                arr = new Node[26];
            }

            Node(char t) {
                c = t;
                words = new ArrayList<String>();
                arr = new Node[26];
            }
        }

        // Inserts a new word into the trie
        public void insert(String w) {
            String sorted = sort(w);
            Node n = root;
            for (int i = 0; i < sorted.length(); i++) {
                char letter = sorted.charAt(i);
                int index = letter - 'a';
                if (n.arr[index] == null) {
                    n.arr[index] = new Node(letter);
                }
                n = n.arr[index];
            }
            n.words.add(w);
        }

        // Finds and prints highest-scoring word that can be formed from given tiles
        public void findWord(String tiles) {
            String word = dfs(tiles, root);
            System.out.println(points(word) + " (\"" + word + "\")");
        }

        private String dfs(String tiles, Node n) {
            String t, word = "";
            String modified;
            int index;
            if (!n.words.isEmpty()) {
                word = max(n.words);
            }
            for (int i = 0; i < n.arr.length; i++) {
                if (n.arr[i] != null) {
                    index = tiles.indexOf(n.arr[i].c);
                    if (index != -1) {
                        modified = tiles.substring(0, index) + tiles.substring(index + 1);
                        t = dfs(modified, n.arr[i]);
                        if (points(t) > points(word))
                            word = t;
                    }
                }
            }
            return word;
        }

        // Sorts a string into alphabetical order, i.e. cda -> acda
        private String sort(String word) {
            char[] temp = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                temp[i] = word.charAt(i);
            }
            Arrays.sort(temp);
            StringBuilder sb = new StringBuilder(temp.length);
            for (char c : temp) sb.append(c);
            return sb.toString();
        }

        // Returns the word in an arraylist worth the most points
        private String max(ArrayList<String> words) {
            int max = points(words.get(0)), imax = 0;
            int current;
            for (int i = 1; i < words.size(); i++) {
                current = points(words.get(i));
                if (current > max) {
                    imax = i;
                    max = current;
                }
            }
            return words.get(imax);
        }

        // Returns point worth of a string
        private int points(String w) {
            int[] cToP = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
            int points = 0;
            for (int i = 0; i < w.length(); i++) {
                points += (i + 1) * cToP[w.charAt(i) - 'a'];
            }
            return points;
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Trie t = new C294I().new Trie();
        Scanner in;
        try {
            File words = new File("enable1.txt");
            in = new Scanner(words);
            while (in.hasNext()) {
                String word = in.nextLine();
                t.insert(word);
            }
        } catch (IOException e) {
            System.out.println("No word list found");
            System.exit(1);
        }
        System.out.println("Pre-processing Runtime: " + (System.currentTimeMillis() - start) + " ms");
        in = new Scanner(System.in);
        System.out.println(t.points("oology"));
        while (true) {
            String tiles = in.next();
            if (tiles.compareTo("s") == 0)
                System.exit(1);
            t.findWord(tiles);
        }

    }
}
