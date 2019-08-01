
/**
*
* @author juansedo, LizOriana1409
*/
class Algoritmos {
	
	/**
	* Devuelve el número de formas en las que se pueden ubicar
	* rectángulos de 2x1 en un espacio de 2xn.
	* @param n Ancho del espacio donde se guardan los rectángulos.
	*/
	public int formas (int n) {
		return (n <= 2)? n: formas(n-1) + formas(n-2);
	}
	
	/**
	* Imprime las formas en las que se pueden ubicar
	* rectángulos de 2x1 en un espacio de 2xn, en un formato especial.
	* @param n Ancho del espacio donde se guardan los rectángulos.
	*/
	public void formas (int n, String s) {
		if (s.length() == n) {
			System.out.println(s);
			return;
		}
		
		if (s.length() + 1 < n) {
			formas(n, s + "BB");
		}
		
		if (s.length() < n) {
			formas(n, s + "A");
		}
	}
	
	
}
