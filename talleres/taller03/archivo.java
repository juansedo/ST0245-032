/**
 *
 * @author juansedo, LizOriana1409
 */
public class Taller3 {
    public static void permute(String a, String output) {
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (output.indexOf(c) < 0) permute(a, output + c);
        }
        if (output.length() == a.length()) System.out.println(output);
    }
}
