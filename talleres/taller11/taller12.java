import java.util.ArrayList;
import java.util.LinkedList;
import javafx.util.Pair;

/**
 * Implementacion de un grafo dirigido usando listas de adyacencia
 *
 * @author juansedo, LizOriana1409
 */
public class DigraphAL {
    //Definimos el par como (elemento relacionado, peso de relación)
    LinkedList<LinkedList<Pair<Integer,Integer>>> listaDeListas;
    
    /**
     * Constructor para el grafo dirigido
     *
     * @param size el numero de vertices que tendra el grafo dirigido
     *
     */
    public DigraphAL(int size) {
        //super(size);
        listaDeListas = new LinkedList<>();
    }
    
    /**
     * Metodo para añadir un arco nuevo, donde se representa cada nodo con un
     * entero y se le asigna un peso a la longitud entre un nodo fuente y uno
     * destino
     *
     * @param source desde donde se hara el arco
     * @param destination hacia donde va el arco
     * @param weight el peso de la longitud entre source y destination
     */
    public void addArc(int source, int destination, int weight) {
        if (listaDeListas.get(source) == null) {
            listaDeListas.add(source, new LinkedList<>());
        }
        listaDeListas.get(source).add(new Pair(destination, weight));
    }

    /**
     * Metodo para obtener una lista de hijos desde un nodo, es decir todos los
     * nodos asociados al nodo pasado como argumento
     *
     * @param vertex nodo al cual se le busca los asociados o hijos
     * @return todos los asociados o hijos del nodo vertex, listados en una
     * ArrayList Para más información de las clases:
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">
     * Ver documentacion ArrayList </a>
     */
    public ArrayList<Integer> getSuccessors(int vertex) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Pair<Integer, Integer> p : listaDeListas.get(vertex)) {
            list.add(p.getKey());
        }
        return list;
    }

    /**
     * Metodo para obtener el peso o longitud entre dos nodos
     *
     * @param source desde donde inicia el arco
     * @param destination donde termina el arco
     * @return un entero con dicho peso
     */
    public int getWeight(int source, int destination) {
        return listaDeListas.get(source).get(destination).getValue();
    }
}

/**
 * Implementacion de un grafo dirigido usando matrices de adyacencia
 *
 * @author Mauricio Toro, Mateo Agudelo, <su nombre>
 */
class DigraphAM {
    private int[][] matriz;

    /**
     * Constructor para el grafo dirigido
     *
     * @param size el número de vertices que tendrá el grafo dirigido.
     *
     */
    public DigraphAM(int size) {
        //super(size);
        matriz = new int[size][size];
    }

    /**
     * Metodo para añadir un arco nuevo, donde se representa cada nodo con un
     * entero y se le asigna un peso a la longitud entre un nodo fuente y uno
     * destino
     *
     * @param source desde donde se hara el arco
     * @param destination hacia donde va el arco
     * @param weight el peso de la longitud entre source y destination
     */
    public void addArc(int source, int destination, int weight) {
        matriz[source][destination] = weight;
    }

    /**
     * Metodo para obtener una lista de hijos desde un nodo, es decir todos los
     * nodos asociados al nodo pasado como argumento
     *
     * @param vertex nodo al cual se le busca los asociados o hijos
     * @return todos los asociados o hijos del nodo vertex, listados en una
     * ArrayList Para más información de las clases:
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html">
     * Ver documentacion ArrayList </a>
     */
    public ArrayList<Integer> getSuccessors(int vertex) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[vertex][i] != 0) list.add(i);
        }
        return list;
    }

    /**
     * Método para obtener el peso o longitud entre dos nodos
     *
     * @param source desde donde inicia el arco
     * @param destination donde termina el arco
     * @return un entero con dicho peso
     */
    public int getWeight(int source, int destination) {
        return matriz[source][destination];
    }
}
