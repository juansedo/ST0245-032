
/**
*
* @author juansedo, LizOriana1409
*/
public class Laboratorio1 {
	
	/**
	* Recursión 1: strCount
	* Devuelve las veces que el string sub se encuentra
	* en el string str.
	*/
	public int strCount(String str, String sub) {
	return (str.indexOf(sub) >= 0)?
		1 + strCount(str.substring(str.indexOf(sub) + sub.length()), sub):
		0;
	}
	
	/**
	* Recursión 1: strCopies
	* Devuelve true si el string sub se encuentra n veces
	* en el string str.
	*/
	public boolean strCopies(String str, String sub, int n) {
	return (n == 0)?
		true:
		(str.indexOf(sub) >= 0)?
			strCopies(str.substring(str.indexOf(sub)+ 1), sub, n-1): false;
	}
	
	/**
	* Recursión 1: strDist
	* Devuelve el tamaño del substring de str que empieza y termina
	* con el string sub.
	*/
	public int strDist(String str, String sub) {
  
		if (str.length() < sub.length()) return 0;
  
		return (str.startsWith(sub))?
			(endsWith(str, sub))? str.length(): strDist(str.substring(0, str.length() - 1), sub):
			strDist(str.substring(1), sub);
	}
	/**
	* Devuelve true si str termina con sub.
	*/
	public boolean endsWith(String str, String sub) {
		return str.substring(str.length() - sub.length(), str.length()).equals(sub);
	}
	
	/**
	* Recursión 2: groupNoAdj
	* Devuelve true si se puede obtener target al sumar
	* algunos números de nums. Con la condición de que,
	* después de elegir un número, no se debe tener en cuenta
	* el consecutivo a este.
	*/
	public boolean groupNoAdj(int start, int[] nums, int target) {
		if (start >= nums.length && target != 0) return false;
  
		return (target == 0)? true:
			groupNoAdj(start + 2, nums, target - nums[start])
			|| groupNoAdj(start + 1, nums, target);
	}
	
}
