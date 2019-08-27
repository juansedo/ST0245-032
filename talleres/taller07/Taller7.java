/**
 * Implementación de lista ligada
 * @author juansedo, LizOriana1409
 */
public class LinkedList {
    private int size;
    public Node startNode;
    
    public LinkedList() {
        startNode = null;
        size = 0;
    }
    
    public int Size() {
        return size;
    }
    
    public int get(int pos) {
        if (pos > size) return get(size-2);
        if (pos < 0) return get(0);
        
        Node m = startNode;
        
        for (int i = 0; i < size; i++) {
            if (i == pos) break;
            m = m.next;
        }
        return m.getValue();
    }
    
    public void insert(int pos, int value) {
        if (pos < 0) {
            insert(0, value);
            return;
        }
        
        size++;
        if (pos == 0) insertBeginning(value);
        else if (pos < size) insertInside(startNode, pos - 1, value);
        else insertAfter(value);
    }
    
    public void insertBeginning(int value) {
        Node n = new Node(value);
        n.next = startNode;
        startNode = n;
        size++;
    }
    
    private void insertInside(Node n, int pos, int value) {
        if (pos != 0) insertInside(n.next, pos - 1, value);
        else {
            Node m = new Node(value);
            m.next = n.next;
            n.next = m;
        }
    }
    
    public void insertAfter(int value) {
        insertAfter(startNode, value);
    }
    
    private void insertAfter(Node n, int value) {
        if (n.next != null) insertAfter(n.next, value);
        else {
            Node m = new Node(value);
            m.next = null;
            n.next = m;
        }
    }
    
    public void delete() {
        
    }
    
    
    
    @Override
    public String toString() {
        return "[" + toString(startNode);
    }
    
    private String toString(Node n) {
        return n.getValue() + ((n.next != null)? ", " + toString(n.next): "]");
    }
}

class Node {
    private int value;
    public Node next;
    
    public Node(int i) {
        value = i;
    }
    
    public int getValue() {
        return value;
    }
}
