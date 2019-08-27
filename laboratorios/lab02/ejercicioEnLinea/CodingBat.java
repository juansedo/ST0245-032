/**
*
*
* @author juansedo, LizOriana1409
*/
class CodingBat {
    
            //Array-2
    /**
    * Suma los elementos de un arreglo, evitando los 13
    * y el número siguiente a este.
    *
    * Complejidad asintótica:
    * T(n) = n + C_1
    * Esto es O(n)
    */
    public int sum13(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 13) i++;
            else sum += nums[i];
        }
        return sum;
    }
    
    /**
    * Suma los elementos de un arreglo, evitando los números
    * entre el primer 6 y el primer 7.
    *
    * Complejidad asintótica:
    * T(n) = n + C_1
    * Esto es O(n)
    */
    public int sum67(int[] nums) {
        int sum = 0;
        boolean able = true;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 6) able = false;
            else if(nums[i] == 7 && !able) able = true;
            else if (able) sum += nums[i];
        }
        return sum;
    }
    
            //Array-3
    /**
    * Reordena un arreglo haciendo que a cada 4
    * le siga un 5.
    *
    * Complejidad asintótica:
    * T(n) = n^2 + C_1
    * Esto es O(n^2)
    */
    public int[] fix45(int[] nums) {
        int i = 0;
        while(i + 1 < nums.length) {
            if(nums[i] == 4 && nums[i+1] != 5) {
                int j = 0;
                while (j < nums.length) {
                    if (nums[j] == 5) {
                        if (j < 1 || nums[j-1] != 4) {
                            int aux = nums[i+1];
                            nums[i+1] = nums[j];
                            nums[j] = aux;
                            break;
                        }
                    }
                j++;
                }
            }
            i++;
        }
        return nums;
    }
    
    /**
    * Divide un arreglo en dos subgrupos. Retorna true si hay 
    * una forma de partir el arreglo tal que ambos lados sumen igual.
    *
    * Complejidad asintótica:
    * T(n) = n^2 + C_1
    * Esto es O(n^2)
    */
    public boolean canBalance(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            sum1 += nums[i];
            sum2 = 0;
            for (int j = nums.length-1; j > i; j--) {
                sum2 += nums[j];
            }
            if (sum1 == sum2) return true;
        }
        return false;
    }
    
    /**
    * Dados dos arreglos, retorna true si todos los elementos del segundo 
    * arreglo están en el primero.
    *
    * Complejidad asintótica:
    * T(n) = n^2 + C_1
    * Esto es O(n^2)
    */
    public boolean linearIn(int[] outer, int[] inner) {
        int j = 0;
        for(int i = 0; i < outer.length && j < inner.length; i++) {
            if (outer[i] == inner[j]) j++;
        }
        return (j==inner.length);
    }
}
