/**
 *
 * @author juansedo, LizOriana1409
 */
class Node {

     private int value;
     private Node central;
     private Node left; //Izquierda
     private Node right; // Derecha

     public Node(int value) {
          this.value = value;
     }

     public void SetValue(int value) {
          this.value = value;
     }

     public int GetValue() {
          return value;
     }

     public void SetCentral(Node central) {
          this.central = central;
     }

     public Node GetCentral() {
          return central;
     }

     public void SetLeft(Node left) {
          this.left = left;
     }

     public Node GetLeft() {
          return left;
     }

     public void SetRight(Node right) {
          this.right = right;
     }

     public Node GetRight() {
          return right;
     }
}

class Tree {

     private Node raiz;
     
     public Tree(int value) { 
          this.raiz = new Node(value); 
     }

     public Tree(Node raiz) { 
          this.raiz = raiz; 
     }

     public Node getRaiz() {
          return raiz;
     }

     public void setRaiz(Node raiz) {
          this.raiz = raiz;
     }

     public void add (int value) {
          add(raiz, value);
     }
     
     private void add (Node n, int value) {
          if (n.GetLeft() == null && n.GetRight() == null) {
               if (value >= n.GetValue()) n.SetRight(new Node(value));
               else n.SetLeft(new Node(value));
               
          } else if (n.GetRight() == null) {
               if (value >= n.GetValue()) n.SetRight(new Node(value));
               else add(n.GetLeft(), value);
          
          } else if (n.GetLeft() == null) {
               if (value >= n.GetValue()) add(n.GetRight(), value);
               else n.SetLeft(new Node(value));
          
          } else {
               if (value >= n.GetValue()) add(n.GetRight(), value);
               else add(n.GetLeft(), value);
          }
     }
     
     public boolean search (int value) {
          return search(raiz, value);
     }
     
     private boolean search (Node n, int value){
         if (n == null) return false;
         if (n.GetValue() == value) return true;
         
         return search(n.GetLeft(), value) || search(n.GetRight(), value);
     }
     
     @Override
     public String toString (){
          return "[ " + toString(raiz) + "]";
     }
     
     private String toString(Node n){
          String str = "";
          if (n.GetLeft() != null) str += toString(n.GetLeft());
          
          str += n.GetValue() + " ";
          
          if (n.GetRight() != null) str += toString(n.GetRight());
          
          return str;
     }
}
