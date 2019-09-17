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

import java.util.HashMap;

/**
 *
 * @author juansedo, LizOriana1409
 */
class PuntosAdicionales {
    public static void main(String [] args) {
        /*PUNTO 2*/
        
        HashMap<String, String> map = new HashMap<>();
        map.put("Google", "Estados Unidos");
        map.put("La locura", "Colombia");
        map.put("Nokia", "Finlandia");
        map.put("Sony", "Japón");
        
        /*PUNTO 3*/
        search_business(map, "Google");
        search_business(map, "Motorola");
        
        /*PUNTO 4*/
        System.out.println("Empresa en India: ");
        System.out.println(search_country(map, "India"));
        System.out.println("Empresa en Estados Unidos: ");
        System.out.println(search_country(map, "Estados Unidos"));
    }
    
    public static void search_business(HashMap<String, String> map, String business) {
        String str = map.get(business);
        if (str == null) System.out.println("No se encuentran datos de " + business + ".");
        else System.out.println(business + " está en " + str);
    }
    
    public static String search_country(HashMap<String, String> map, String country) {
        for (String s : map.keySet()) {
            if (country.equals(map.get(s))) return s;
        }
        return "";
    }
}

