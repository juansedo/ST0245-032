class Taller5 {
          //Insertion Sort
  /** Ciclos
  * Organiza por medio del insertion sort un arreglo.
  * La complejidad de este código está dada por O(n^2),
  * donde n representa la longitud del string,
  * ya que usa dos ciclos anidados.
  */
  public static int [] sort(int [] arr) {
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1] > arr[i]) {
        for(int j = i; j >= 1; j--) {
          if (arr[j-1] > arr[j]) {
            arr[j] += arr[j-1];
            arr[j-1] = arr[j] - arr[j-1];
            arr[j] -= arr[j-1];
          }
          else break;
        }
      }
    }
    return arr;
  }
  
  /** Recursión
  * Organiza por medio del insertion sort un arreglo.
  * La complejidad de este código está dada por la ecuación:
  * T(n) = F(n) + T(n-1) + C3
  * donde F(n) hace referencia al método recursivo insert() y
  * n a la longitud de la cadena.
  *
  * Operándose en Wolfram resulta:
  * T(n) = C1*n + C2 + C3*n + (C4*n)(n+1)/2
  * Que es O(n^2).
  */
  public static int [] rsort(int [] arr) {
    return rsort(arr, 1);
  }
  
  public static int [] rsort(int [] arr, int start) {
    if (arr.length == start) return arr;
    return rsort(insert(arr, start), start + 1);
  }
  
  /**
  * Complejidad:
  * F(n) = F(n - 1) + C4, que es O(n)
  * Donde n es la longitud del arreglo.
  */
  private static int [] insert(int [] arr, int start) {
    if (start == 0) return arr;
    
    if (arr[start - 1] > arr[start]) {
      arr[start] += arr[start-1];
      arr[start-1] = arr[start] - arr[start-1];
      arr[start] -= arr[start-1];
      
      return insert(arr, start - 1);
    }

    return arr;
  }

}
