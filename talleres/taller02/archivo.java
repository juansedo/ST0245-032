
/**
 *
 * @author jsdiazo, LizOriana1409
 */

public class Recursion {

	/**
	* PUNTO 1
	* Calcula el MCD de dos números a y b.
	*/
	
	public int MCD(int a, int b) {
		if (a < b) {
			/*Intercambio de valores. Para que el mayor sea a*/
			a += b;
			b = a-b;
			a -= b;
		}
		
		if (b == 0) return a;	
		else return MCD(b, a%b);
	}
	
	
}
