// Challenge Link: redd.it/4q35ip
// Usage: C273E <degrees>
// Converts between Farenheit, Celsius, Kelvin, Degrees and Radians, depending on units specified

import java.text.DecimalFormat;
import java.util.Scanner;

public class C273E {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
        System.out.println(convert(in.nextLine()));
    }

    public static String convert(String input) {
        int length = input.length();
        double value = Double.parseDouble(input.substring(0,length-2));
        DecimalFormat format = new DecimalFormat("0.##");
        String convert = input.substring(length-2, length);
        String output = "";
        switch(convert) {
            case "rd":  value = value * (180/Math.PI);
                        output = output + format.format(value) + "d";
                        break;
            case "dr":  value = value * (Math.PI/180);
                        output = output + format.format(value) + "r";
                        break;
            case "cf":  value = value * 9/5 + 32;
                        output = output + format.format(value) + "f";
                        break;
            case "fc":  value = (value - 32) * 5/9;
                        System.out.println(value);
                        output = output + format.format(value) + "c";
                        break;
            case "ck":  value = value + 273.15;
                        output = output + format.format(value) + "k";
                        break;
            case "kc":  value = value - 273.15;
                        output = output + format.format(value) + "c";
                        break;
            case "fk":  value = (value + 459.67) * (5/7);
                        output = output + format.format(value) + "k";
                        break;
            case "kf":  value = value * (9/5) - 459.67;
                        output = output + format.format(value) + "f";
                        break;
            default: return "No candidate for conversion";
        }
	return output;
    }
}
