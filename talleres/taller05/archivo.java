class Taller5 {
  
  //Forma por ciclos
  // O(n^2)
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
  
  //Forma por recursión
  //O(n^2)
  public static int [] sort(int [] arr, int start) {
    if (arr.length == start) return arr;
    return sort(insert(arr, start), start + 1);
  }

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
