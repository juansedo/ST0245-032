/**
 * Implementación de lista ligada
 * @author juansedo, LizOriana1409
 */
public class LinkedList {
    private int size;
    
    /**
    * Referencia al nodo inicial
    */
    public Node startNode;
    
    /**
    * Método constructor.
    */
    public LinkedList() {
        startNode = null;
        size = 0;
    }
    
    /**
    * @return devuelve el tamaño de la lista
    */
    public int Size() {
        return size;
    }
    
    /**
    * Devuelve el valor en la posición pos de la lista.
    * @param pos posición en la lista.
    * @return valor en pos.
    */
    public int get(int pos) {
        /*Si el tamaño es mayor, devuelve el */
        if (pos > size) return get(size-1);
        if (pos < 0) return get(0);
        
        Node m = startNode;
        /*Buscamos el elemento en pos*/
        for (int i = 0; i < size; i++) {
            if (i == pos) break;
            m = m.next;
        }
        return m.getValue();
    }
    
    /**
    * Inserta el valor dado en la posición pos de la lista.
    * @param pos posición en la lista.
    * @param value valor a agregar.
    */
    public void insert(int pos, int value) {
        /*Si la posición es menor que 0, devuelve el primer valor*/
        if (pos < 0) {
            insert(0, value);
            return;
        }
        
        if (pos == 0) insertBeginning(value);
        
        else if (pos < size) {
            size++;
            insertInside(startNode, pos - 1, value);
        }
        
        else insertAfter(value);
    }
    
    /**
    * Inserta el valor dado al principio de la lista.
    * @param value valor a agregar.
    */
    public void insertBeginning(int value) {
        Node n = new Node(value);
        n.next = startNode;
        startNode = n;
        size++;
    }
    
    private void insertInside(Node n, int pos, int value) {
        /*Este método recorre recursivamente los nodos de la lista*/
        if (pos != 0) insertInside(n.next, pos - 1, value);
        else {
            Node m = new Node(value);
            m.next = n.next;
            n.next = m;
        }
    }
    
    /**
    * Inserta el valor dado al final de la lista.
    * @param value valor a agregar.
    */
    public void insertAfter(int value) {
        size++;
        insertAfter(startNode, value);
    }
    
    private void insertAfter(Node n, int value) {
        /*Este método recorre recursivamente los nodos*/
        if (n.next != null) insertAfter(n.next, value);
        else {
            Node m = new Node(value);
            m.next = null;
            n.next = m;
        }
    }
    
    public void delete(int pos) {
        if (pos == 0) deleteBeginning();
        else if (pos < size) deleteInside(startNode, pos);
        else deleteAfter(startNode);
    }
    private void deleteBeginning(){
        Node n = startNode;
        n = n.next;
        size--;
    }
    private void deleteInside(Node n, int pos){
        if (pos != 0) deleteInside(n.next, pos -1);
        else {
            n = n.next; 
        }
    }
    
    private void deleteAfter(Node n){
        if (n.next != null) deleteAfter(n.next);
        else {
            n = null;
            size--;
        }
    }
    
    
    
    @Override
    public String toString() {
        return "[" + toString(startNode);
    }
    
    private String toString(Node n) {
        /*Devuelve:
        * [e1, e2, e3]
        * para una lista de 3 elementos*/
        return n.getValue() + ((n.next != null)? ", " + toString(n.next): "]");
    }
}

/**
 * Clase que representa un nodo
 * @author juansedo, LizOriana1409
 */
class Node {
    private int value;
    /**
    * Nodo enlazado
    */
    public Node next;
    
    /**
    *
    */
    public Node(int i) {
        value = i;
    }
    
    public int getValue() {
        return value;
    }
}
