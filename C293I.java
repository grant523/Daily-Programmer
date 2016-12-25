// Challenge Link: redd.it/5emuuy
// Usage: C293I [-b] <wire1> <wire2> ...
// Determines whether a given sequences of cut wires satisfies satisfied rules

import java.util.Arrays;
import java.util.Scanner;

public class C293I {

    // For non-bonus mode
    private static int state = 0;
    private static boolean failed = false;
    private static boolean defused = false;

    private static void process(String color) {
        switch (state) {
            case 0:
                if (color.equals("white"))
                    state = 1;
                else if (color.equals("red"))
                    state = 2;
                else
                    failed = true;
                break;
            case 1:
                if (color.equals("white"))
                    state = 2;
                else if (color.equals("orange"))
                    state = 3;
                else
                    failed = true;
                break;
            case 2:
                if (color.equals("black"))
                    state = 3;
                else if (color.equals("red"))
                    state = 0;
                else
                    failed = true;
                break;
            case 3:
                if (color.equals("green"))
                    state = 4;
                else if (color.equals("orange"))
                    state = 5;
                else if (!color.equals("black"))
                    failed = true;
                break;
            case 4:
                if (color.equals("orange"))
                    defused = true;
                else if (color.equals("black"))
                    state = 3;
                else
                    failed = true;
                break;
            case 5:
                if (color.equals("green"))
                    defused = true;
                else if (color.equals("black"))
                    state = 3;
                else
                    failed = true;
                break;
        }
    }

    // Different approach for bonus mode
    public static abstract class state {
        String[] paths;

        public abstract String[] getPath();

        public abstract int next(String color);

    }

    public static class state0 extends state {
        public String[] paths = {"red", "white"};

        public String[] getPath() {
            return paths;
        }

        public int next(String color) {
            switch (color) {
                case "white":
                    return 1;
                case "red":
                    return 2;
                default:
                    return -1;
            }
        }
    }

    private static class state1 extends state {
        public String[] paths = {"white", "orange"};

        public String[] getPath() {
            return paths;
        }

        public int next(String color) {
            switch (color) {
                case "white":
                    return 2;
                case "orange":
                    return 3;
                default:
                    return -1;
            }
        }
    }

    private static class state2 extends state {
        String[] paths = {"red", "black"};

        public String[] getPath() {
            return paths;
        }

        public int next(String color) {
            switch (color) {
                case "black":
                    return 3;
                case "red":
                    return 0;
                default:
                    return -1;
            }
        }
    }

    private static class state3 extends state {
        String[] paths = {"green", "orange", "black"};

        public String[] getPath() {
            return paths;
        }

        public int next(String color) {
            switch (color) {
                case "green":
                    return 4;
                case "orange":
                    return 5;
                case "black":
                    return 3;
                default:
                    return -1;
            }
        }
    }

    private static class state4 extends state {
        String[] paths = {"orange", "black"};

        public String[] getPath() {
            return paths;
        }

        public int next(String color) {
            switch (color) {
                case "orange":
                    return 6;
                case "black":
                    return 3;
                default:
                    return -1;
            }
        }
    }

    private static class state5 extends state {
        String[] paths = {"green", "black"};

        public String[] getPath() {
            return paths;
        }

        public int next(String color) {
            switch (color) {
                case "green":
                    return 6;
                case "black":
                    return 3;
                default:
                    return -1;
            }
        }
    }

    // Color to corresponding color code
    private static int cToI(String color) {
        switch (color) {
            case "white":
                return 0;
            case "red":
                return 1;
            case "black":
                return 2;
            case "green":
                return 3;
            case "orange":
                return 4;
            default:
                return 0;
        }
    }


    // Recursive depth-first search
    private static void dfs(state[] states, int i, int[] colors) {
        for (String c : states[i].getPath()) {
            int cInt = cToI(c);
            int t = i;
            int[] copy = colors.clone();
            if (colors[cInt] != 0) {
                i = states[i].next(c);
                colors[cInt]--;
                if (i != 6)
                    dfs(states, i, colors);
                else if (i == 6 && allZero(colors)) {
                    System.out.println("defusable");
                    System.exit(0);
                }
            }
            i = t;
            colors = copy.clone();
        }

    }

    private static boolean allZero(int[] c) {
        for (int i : c) {
            if (i != 0)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (!args[0].equals("-b")) {
            System.out.println("Enter a sequence of wires:");
            while (true) {
                process(in.nextLine().toLowerCase());
                if (failed) {
                    System.out.println("Booom");
                    break;
                }
                if (defused) {
                    System.out.println("defused");
                    break;
                }
            }
        } else {
            // white red black orange green
            System.out.println("Enter amount of each color wire, i.e. \"white 3\", in order white, red, black, orange, green:");
            int[] colors = new int[5];
            for (int i = 0; i < 5; i++) {
                colors[i] = Integer.parseInt(in.nextLine().replaceAll("[^0-9]", ""));
            }
            state[] states = {new state0(), new state1(), new state2(), new state3(), new state4(), new state5()};
            dfs(states, 0, colors);
            System.out.println("not defusable");
        }
    }
}
