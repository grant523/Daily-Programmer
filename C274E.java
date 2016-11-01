// Challenge Link: redd.it/4r8fod
// Usage: C274E <key> <cipher>
// Decrypts a secret message by pulling the first letter of words in a document indicated by numbers in a cipher

import java.util.*;
import java.io.*;

public class C274E {

    public static void main(String[] args) throws IOException {
        String key = "";
        String cipherText = "";
        try {
            key = args[0];
            cipherText = args[1];

        }
        catch (Error E) {
        }
        Scanner in1 = new Scanner(new File(key));
        Scanner in2 = new Scanner(new File(cipherText)).useDelimiter(", ");
        ArrayList<String> keyList = new ArrayList<>();
        ArrayList<Integer> cipherList = new ArrayList<>();
        while(in1.hasNext()) {
            keyList.add(in1.next());
        }

        while(in2.hasNext()) {
            cipherList.add(in2.nextInt());
        }

        String word = "";
        String result = "";
        int index = 0;
        for(int i = 0; i <cipherList.size(); i++) {
            index = cipherList.get(i).intValue() - 1;
            if(index > 1332)
                index = index - 1332;
            word = keyList.get(index);
            result = result + word.charAt(0);
        }
        System.out.println(result);
    }
}
