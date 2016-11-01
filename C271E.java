// Challenge Link: redd.it/4nvrnx
// Usage: C271 <sides> <health>
// Calculates probability of rolling a die higher than the health of an opponent 

public class C271E {
    public static void main(String[] args) {
	int d = Integer.parseInt(args[0]);
        int h = Integer.parseInt(args[1]);
        double prob = 1;
        while(h>=d) {
            prob = prob * (1/d);
            h = h-d;
        }
        if(h>0)
            prob = prob *((1.0+d-h)/d);
        System.out.println(prob + " probability of killing in one round.");
    }
}
