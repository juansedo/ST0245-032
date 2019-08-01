
/**
*
* @author juansedo, LizOriana1409
*/
public class Laboratorio1CB {
	
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
     * parenBit 
     * Recursion-1 CodingBat
     */
    public String parenBit(String str) {
        if (str.equals("")) {
            return str;
        } 
        if (str.charAt(0) == '(') {
            if (str.charAt(str.length()-1) == ')'){
                return str;
            }else{
                return parenBit(str.substring(0, str.length()-1));
            }
        } else {
            return parenBit(str.substring(1)); }
    }

    /**
     * nestParen 
     * Recursion-1 CodingBat
     */
    public boolean nestParen (String str){
        int x = str.length();
        if (x == 0){
            return true;
        } 
        if (str.charAt(0) == '(' && str.charAt(x -1) == ')'){
            return nestParen(str.substring(1, x-1));
        } else {
            return false;
        }
    }
	
	/**
	* Recursión 2: groupSum6
	* Devuelve true si se puede obtener target al sumar
	* algunos números de nums. Con la condición de que,
	* después de elegir un número, no se debe tener en cuenta
	* el consecutivo a este.
	*/
	public boolean groupSum6(int start, int[] nums, int target) {
  		if (start >= nums.length) return (target == 0);
  
  		if (nums[start] == 6) return groupSum6(start + 1, nums, target - 6);
  
		return groupSum6(start + 1, nums, target - nums[start])
			    || groupSum6(start + 1, nums, target);
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
	
	/**
     * groupSumClump 
     * Recursion-2 CodingBat
     */
    public boolean groupSumClump(int start, int[] nums, int target) {
        altArray(nums);
        if (start >= nums.length) {
            return target == 0;}
        if (groupSumClump(start+1, nums, target-nums[start])) {
            return true; }
        if (groupSumClump(start+1, nums, target)) {
            return true;}
        else {
            return false;} 
    }
    private void altArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                nums[i-1] += nums[i];
                if (i+1 < nums.length && nums[i] != nums[i+1])
                    nums[i] = 0;
                else if (i == nums.length-1)
                    nums[i] = 0;
            }
        } 
    }

    /**
     * splitOdd10 
     * Recursion-2 CodingBat
     */
    public boolean splitOdd10(int[] nums) {
        return helper(0, nums, 0, 0);
    }
    //Se crea una clase de apoyo para realizar el metodo recursivo 
    private boolean helper(int start, int[] nums, int sum1, int sum2) {
        if (start >= nums.length)
            return sum1 % 10 == 0 && sum2 % 2 == 1 || sum1 % 2 == 1 && sum2 % 10 == 0;
        return helper(start + 1, nums, sum1 + nums[start], sum2)|| helper(start + 1, nums, sum1, sum2 + nums[start]);
    }

    /**
     * splitArray 
     * Recursion-2 CodingBat
     */
    public boolean splitArray(int[] nums) {
        return Help (nums, 0, 0);   
    }
    //Se crea una clase de apoyo para realizar el metodo recursivo 
    public boolean Help (int[] nums, int i, int balance) {
        if(i == nums.length) {
            return (balance == 0);
        }
        if(Help (nums, i + 1, balance + nums[i])) {
            return true; 
        }
        return Help (nums, i + 1, balance - nums[i]);
    }
}
