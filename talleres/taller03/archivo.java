/**
 *
 * @author juansedo, LizOriana1409
 */
public class Taller3 {
	/**
    *
    * Ejercicio 1.
    */
     public static void TorresPrueba() {
          int n = 4; //Se puede cambiar a gusto.
          Torres(n, 1, 3, 2);
     }

     public static void Torres(int n, int origen, int destino, int auxiliar) {
          if (n == 1) {
               System.out.println("Mover de " + origen + " a " + destino);
          } else {
               Torres(n - 1, origen, auxiliar, destino);
               System.out.println("Mover de " + origen + " a " + destino);
               Torres(n - 1, auxiliar,destino, origen);
               
          }
     }
    
    /**
    *
    * Ejercicio 2. 
    */
    public static void permute(String a, String output) {
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (output.indexOf(c) < 0) permute(a, output + c);
        }
        if (output.length() == a.length()) {
            /**
            * El valor de output en este momento es una permutación de abcd.
            * Debe pasarse a la función desencriptarArchivo. 
            */
        }
    }
}
