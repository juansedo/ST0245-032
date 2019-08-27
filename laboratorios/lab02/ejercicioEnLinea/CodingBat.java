/**
*
*
* @author juansedo, LizOriana1409
*/
class CodingBat {
    
            //Array-2
    /**
    * Cuenta la cantidad de impares de un arreglo de enteros.
    *
    * Complejidad asintótica:
    * T(n) = n + C_1
    * Esto es O(n)
    */
    public int countEvens(int[] nums) { 
        int cont = 0; 
        for (int i = 0; i < nums.length; i++) 
            if (nums[i] % 2 == 0) cont++; 
        return cont; 
    }
    
    /**
    * Busca el valor máximo y mínimo del arreglo y
    * devuelve max - min.
    *
    * Complejidad asintótica:
    * T(n) = n + C_1
    * Esto es O(n)
    */
    public int bigDiff(int[] nums) { 
        int max = nums[0]; 
        int min = nums[0]; 
        for (int i = 0; i < nums.length; i++) { 
            if (nums[i] > max) max = nums[i];   
            if (nums[i] <= min) min = nums[i]; 
        } 
        return max - min; 
    }
    
    /**
    * Suma los elementos del arreglo dado y le resta un valor
    * máximo y uno mínimo. Devuelve el promedio de lo que queda.
    *
    * Complejidad asintótica:
    * T(n) = n + C_1
    * Esto es O(n)
    */
    public int centeredAverage(int[] nums) { 
        int max = nums[0]; 
        int min = nums[0]; 
        int sum = 0; 
        for (int i = 0; i < nums.length; i++) { 
            sum += nums[i]; 
            if (nums[i] > max) max = nums[i]; 
            if (nums[i] < min) min = nums[i]; 
        } 
    return (sum - (max + min)) / (nums.length - 2); 
    }
    
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
    public int maxSpan(int[] nums) { 
        if (nums.length > 0) { 
            int n = 1; 
            for (int i = 0; i < nums.length; i++) 
                for (int j = nums.length - 1; j > i; j--) 
                    if (nums[j] == nums[i]) { 
                        int cont = (j - i) + 1; 
                        if (cont > n) n = cont; 
                        break; 
                    } 
            return n;
        } else return 0; 
    } 
 
    public int[] fix34(int[] nums) { 
        for (int i = 0; i < nums.length; i++) 
            if (nums[i] == 3) { 
                int x = nums[i + 1]; 
                nums[i + 1] = 4; 
                for (int j = i + 2; j < nums.length; j++) 
                    if (nums[j] == 4) nums[j] = x; 
            }
        return nums;
    }
    
    
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
