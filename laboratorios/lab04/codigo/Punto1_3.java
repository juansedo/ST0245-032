/**
 *
 * @author juansedo, LizOriana1409
 */
public class Punto1_3 {
    public static void main(String[] args) {
        /*
        * Esta prueba corresponde a lo pedido en el ejercicio 1_2
        */
        
        BinaryTree me = new BinaryTree("Juan Sebastián Díaz Osorio");
        //Padres
        me.getRoot().setFather("Javier Alonso Díaz Gallego");
        me.getRoot().setMother("Ruth Nelly Osorio García");
        
        Node dad = me.getRoot().getFather();
        Node mom = me.getRoot().getMother();
        
        //Abuelos
        dad.setFather("Viley de Jesús Díaz García");
        dad.setMother("Cruz Lorenza Gallego Henao");
        mom.setFather("Uldarico Osorio Palacios");
        mom.setMother("Olga de Jesús García Correa");
        
        Node grandfatherDad = dad.getFather();
        Node grandmotherDad = dad.getMother();
        Node grandfatherMom = mom.getFather();
        Node grandmotherMom = mom.getMother();
        
        
        //Bisabuelos
        grandfatherDad.setFather("Manuel José Díaz");
        grandfatherDad.setMother("Maria Ulpiana García");
        grandmotherDad.setFather("Arcelio Antonio Gallego");
        grandmotherDad.setMother("Maria Luisa Henao");
        
        grandfatherMom.setFather("");
        grandfatherMom.setMother("Rosamelia Palacios");
        grandmotherMom.setFather("");
        grandmotherMom.setMother("");
    }
}

/**
* Clase que representa una persona en el árbol.
* Tiene nombre y dos nodos padres.
*/
class Node {
    String name;
    private Node mom;
    private Node dad;
    
    public Node (String name) {
        this.name = name;
    }
    
    public Node getFather() {
        if (dad != null) return dad;
        else return null;
    }
    
    public Node getMother() {
        if (mom != null) return mom;
        else return null;
    }
    
    public void setFather(String father) {
        dad = new Node(father);
    }
    
    public void setMother(String mother) {
        mom = new Node(mother);
    }
}

/**
* Clase que representa un árbol genealógico.
* Tiene un nodo raíz del que surge el árbol.
*/
class BinaryTree {
    Node root;
    
    public BinaryTree(String name) {
        root = new Node(name);
    }
    
    public Node getRoot() {
        return root;
    }
    
    /**
    * Método para encontrar la abuela materna
    */
    public String getMaternalGrandmother() {
        String name = this.root.getMother().getMother().name;
        if (name != null) return name;
        return "No definida";
    }
}
