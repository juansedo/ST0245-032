
/**
*
* @author juansedo, LizOriana1409
*/
class Algoritmos {
	
	/**
	* PUNTO 1.1
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
	}
	
	/**
	* PUNTO 1.2
	* Devuelve el número de formas en las que se pueden ubicar
	* rectángulos de 2x1 en un espacio de 2xn.
	* 
	* Complejidad asintótica:
	* T(n) = T(n-1) + T(n-2)
	* @param n Ancho del espacio donde se guardan los rectángulos.
	*/
	public int formas (int n) {
		return (n <= 2)? n: formas(n-1) + formas(n-2);
	}
}

