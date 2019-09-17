/**
* Clase que representa una tabla hash con 10 elementos como máximo
* @author juansedo, LizOriana1409
*/
public class TablaHash {
   private int[] tabla;
	 
	 public TablaHash() {
	 		tabla = new int[10];
   }

   /**
   * @param k llave o key de la tabla
   */
   private int funcionHash(String k){
       return ((int) k.charAt(0)) % 10;
   }

   /**
   * @param k es la llave del elemento que servirá como referencia al buscar
   * Este método se utiliza para obtener el elmento con la clave k, se enlaza con funcionHash
   */
   public int get(String k) {
	 		return tabla[funcionHash(k)];
   }

   /**
   * @param k es la llave del elemento que servirá como referencia al buscar posteriormente
   * @param v es el valor asociado a la llave k
   * Este método se utiliza para agregar un nuevo elemento
   */
   public void put(String k, int v){
	 		int a = funcionHash(k);
			tabla[a] = v;
   }
}
