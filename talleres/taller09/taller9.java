/**
 * Clase que representa una tabla hash con 10 elementos como máximo
 *
 * @author juansedo, LizOriana1409
 */
public class TablaHash {

    private int[] tabla;
    
    public TablaHash() {
        tabla = new int[10];
    }
    
    /**
     * @param k Nombre de la persona
     * @return Posición en la que se va a ubicar
     */
    private int funcionHash(String k) {
        int num = 0;
        for (int i = 0; i < k.length(); i++) {
            num += Math.pow( ((int)k.charAt(i))*i, 32-i);
        }
        return num % 10;
    }
    
    /**
     * @param k Nombre de la persona
     * @return Número de teléfono
     */
    public int get(String k) {
        return tabla[funcionHash(k)];
    }
    
    /**
     * @param k Nombre de la persona
     * @param v Número de teléfono
     */
    public void put(String k, int v) {
        tabla[funcionHash(k)] = v;
    }
}
