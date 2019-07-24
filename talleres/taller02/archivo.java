
/**
 *
 * @author jsdiazo, LizOriana1409
 */

public class Recursion {

	/**
	* PUNTO 1
	* Calcula el MCD de dos números a y b.
	*/
	public static int MCD(int a, int b) {
		if (a < b) {
			/*Intercambio de valores. Para que el mayor sea a*/
			a += b;
			b = a-b;
			a -= b;
		}
		
		if (b == 0) return a;	
		else return MCD(b, a%b);
	}
	
	/**
	* PUNTO 2
	* Determina si existe o no una combinación de pesos
	* que dé el número máximo posible.
	* n - Arreglo con los pesos de los objetos que se tiene
	* max - Peso máximo posible
	*/
	
	public static boolean Peso(int [] n, int max) {
        return Peso(n, 0, 0, max);
    }
    
    public static boolean Peso(int[] n, int pos, int sum, int max) {
        /*Comprobación*/
        if (sum == max) return true;
        
        /*Conservación de estado anterior*/
        int start = sum;
        for (int i = pos; i < n.length; i++) {
            if (sum < max) {
                sum += n[i];
                if(Peso(n, i + 1, sum, max)) return true;
                /*Reinicia la suma por otra rama del árbol*/
                sum = start;
                pos++;
            }
		}
        /*No se llegó al caso esperado*/
        return false;
    }
	
	/**
	* PUNTO 3
	* Muestra en consola las combinaciones de objetos posibles
	* dados sus nombres.
	* n - Arreglo con los nombres de los objetos que se tiene
	*/
	public static void Combinacion(String [] n) {
		Combinacion(n,0,"");
	}
	
	public static void Combinacion(String [] n, int pos, String output) {
        /*Comprobación*/
        System.out.println("Forma: " + output);
	
        /*Conservación de estado anterior*/
        String start = output;
		for (int i=pos; i < n.length; i++){
            output += n[i] + " ";
            /*Se llama a sí mismo hasta acabar el largo del arreglo*/
            Combinacion(n, pos + 1, output);
            
            /*Reinicia las combinaciones por otra rama del árbol*/
            output = start;
            pos++;
		}
    }
	
	/**
	* PUNTO 4 (OPCIONAL)
	* Devuelve la subsecuencia común más larga entre dos strings dados.
	* a - Primer string
	* b - Segundo string
	*/
	public static String lcsdyn(String a, String b) {
		lcsdyn(a,b,"");
	}
	
	public static String lcsdyn(String a, String b, String output) {
        if (a.length() == 0 || b.length() == 0) {
            return "";
        }
        
        for (int i = 0; i < a.length(); i++) {
            String snip_a = a.substring(i, i+1);
            int pos_in_b = b.indexOf(snip_a);
            
            /*Si el carácter de a está en b*/
            if (pos_in_b >= 0) {
                /*Creamos una cadena de carácteres de cada carácter de a relativo a b*/
                String str = snip_a + lcsdyn(a.substring(i+1), b.substring(pos_in_b + 1), "");
                
                /*Va modificando el valor de output hasta encontrar el string más grande*/
                if (str.length() > output.length()) output = str;
            } else {
                
                /*Cambia de carácter en a*/
                return lcsdyn(a.substring(i+1), b, output);
            }
        }
        /*Entrega el string más grande*/
        return output;
    }
	
}
