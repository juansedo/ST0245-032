import java.util.ArrayList;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Taller3 {
	/**
	* Ejercicio 1.
	* Dado un n que representa la cantidad de aros de la torre de Hanoi,
    * el algoritmo dice paso a paso como solucionar el problema.
    */
     public static void TorresHanoi(int n) {
          Torres(n, 1, 3, 2);
     }

     public static void Torres(int n, int origen, int destino, int auxiliar) {
          if (n == 1) {
               System.out.println("Mover de " + origen + " a " + destino);
          } else {
               Torres(n - 1, origen, auxiliar, destino);
               System.out.println("Mover de " + origen + " a " + destino);
               Torres(n - 1, auxiliar,destino, origen);
          }
     }
    
    /**
    * Ejercicio 2.
	* El algoritmo, dado un string a, encuentra cada permutación posible
	* con las letras de este. Manteniendo siempre el mismo tamaño de string.
    */
	public static void permutar(String a) {
          permutar(a, "");
    }
    
	public static void permutar(String a, String output) {
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (output.indexOf(c) < 0) permutar(a, output + c);
        }
        if (output.length() == a.length()) {
            /**
            * El valor de output en este momento es una permutación de abcd.
            * Pasa a la función desencriptarArchivo (aunque, en nuestro contexto, no existe). 
            */
			String s = desencriptarArchivo(output);
			if (!s.isEmpty()) {
				System.out.println(s);
			}
        }
    }
}


/**
 * Clase para solucionar el ejercicio 3
 * @author juansedo, LizOriana1409
 */
class ProblemaReinas {
	
	/**
	* Ejercicio 1.
	* Dado un n que representa la cantidad de reinas y cuadros,
    * el algoritmo muestra todas las formas en las que pueden ubicarse las
	* reinas tal que no se amenace ninguna con la otra.
    */
    public static void Queens(int n) {
        Queens(new ArrayList<>(), new boolean [n][n], 0);
    }
    
    /** Algoritmo que imprime recursivamente las soluciones de un tablero de ajedrez
     * para el problema de las n reinas.
     * 
     * @param pos lista de las posiciones de las reinas.
     * @param m matriz que representa el tablero.
     * @param row fila en la que se está buscando solución.
     */
    public static void Queens(ArrayList<Posicion> pos, boolean [][] m, int row) {
        
        for (int col = 0; col < m.length; col++) {
            m[row][col] = true;
            pos.add(new Posicion(row, col));
            
            if (testM(pos)) {
                if (row == m.length - 1) {          /*Tablero completado*/
                    System.out.println("\n");
                    printM(m);                      //Se imprime
                    eraseContent(m[row]);           //Se borra el contenido 
                    pos.remove(pos.size()-1);
                    return;
                } else {
                    Queens(pos, m, row + 1);        //Se avanza de fila
                }
            }
            // Se borra la fila y posición
            eraseContent(m[row]);
            if (row == 0) pos.clear();
            else pos.remove(pos.size() - 1);
        }
    }
    
    /**Averigua si en el tablero hay reinas enfrentadas según sus posiciones.
     * 
     * @param pos posiciones de las reinas.
     * @return true si no están enfrentadas, false en caso de que sí.
     */
    public static boolean testM(ArrayList<Posicion> pos) {
        for(Posicion p : pos) {
            for(Posicion q: pos) {
                if (p.equals(q)) continue;
                if (p.compareValues(q)) return false;
            }
        }
        return true;
    }
    
    /** Imprime el tablero con un formato fijo en consola donde R son las reinas
     * y los números indican las columnas y filas del tablero <br>
     * 
     *   1 2 3 4
     * 1 - R - -
     * 2 - - - R
     * 3 R - - -
     * 4 - - R -
     * 
     * @param m matriz que representa el tablero.
     */
    public static void printM(boolean [][] m) {
        System.out.print("   ");
        for (int i = 1; i <= m.length; i++)System.out.print(i + " ");
        System.out.println();
        
        for(int i = 0; i < m.length; i++) {
            if (i<9) System.out.print((i+1) + "  ");
            else System.out.print((i+1) + " ");
            
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j]) System.out.print("R ");
                else System.out.print("- ");
            }
            System.out.println();
        }
    }
    
    /**Borra los true de un arreglo booleano.
     * @param m arreglo booleano.
     */
    public static void eraseContent(boolean [] m) {
        for(int i = 0; i < m.length; i++) if(m[i]) m[i] = false;
    }
}

/**
 * Clase que define una posición en el tablero de ajedrez.
 * @author juansedo, LizOriana1409
 */
class Posicion {
    
    private int x, y;
    private int relPrimary; //Primera diagonal
    private int relSecond;  //Segunda diagonal
    
    /**
     * @param x valor de la posición en columna
     * @param y valor de la posición en fila
     */
    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
        relPrimary = y - x;
        relSecond = y + x;
    }
    
    /**
     * @return el valor de x
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return el valor de y
     */
    public int getY() {
        return y;
    }
    
    /**
     * @return el valor de y - x, relacionado con la diagonal primaria
     */
    public int getPrimary() {
        return relPrimary;
    }
    
    /**
     * @return el valor de y + x, relacionado con la diagonal secundaria
     */
    public int getSecond() {
        return relSecond;
    }
    
    /**
     * Compara la relación de la posición con otra dada para ver si 
     * están en la misma columna, fila o diagonal.
     * @param p segunda posición
     * @return true si están en la misma columna fila o diagonal, false en caso
     * contrario.
     */
    public boolean compareValues(Posicion p) {
        return (x == p.getX()) || (y == p.getY()) ||
                (relPrimary == p.getPrimary()) || (relSecond == p.getSecond());
    }
}

