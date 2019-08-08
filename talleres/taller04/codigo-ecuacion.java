public class Taller 4 {
/**
 * Complejidad: T(n) = C2 + n + T(n - 1)
 * Ejercicio 1 Codigo
 */
public static int Sumar(int array[]){
    int x = array.length;
    int array2 [] = new int[x-1]; 
    if (x == 0){
      return array[x];
    } else {
      return (array[x] + Sumar(array2));
    }
  }
  
  /**
 *
 * Ejercicio 2 Codigo
 */
 public static boolean Max(int start, int[] nums, int target) {
    if (start >= nums.length){ 
	  return target == 0;
	} else {
    return Max(start + 1, nums, target - nums[start]) || Max(start + 1, nums, target);
	}
}
  
/**
 *
 * Ejercicio 3 Codigo
 * Complejidad: T(n) = T(n-1) + T(n -2)
 */
 public static int fibonacci (int n){
	  if (n <= 1){
		  return n;
	  } else {
	  	return fibonacci(n-1) + fibonacci(n - 2);
	  }
  }
}