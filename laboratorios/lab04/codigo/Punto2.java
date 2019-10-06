package laboratorio4.pkg1;
import java.util.Stack;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Laboratorio41 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Tree t = new Tree();
        Tree.Node root = new Tree.Node("50");
        t.root = root;
        t.root.left = new Tree.Node("30");
        t.root.left.left = new Tree.Node("24");
        t.root.left.left.left = new Tree.Node("5");
        t.root.left.left.right = new Tree.Node("28");
        t.root.left.right = new Tree.Node("45");
        t.root.right = new Tree.Node("98");
        t.root.right.left = new Tree.Node("52");
        t.root.right.right = new Tree.Node("60");
    
        System.out.println("printing nodes of binary tree on pre-order");
        t.preOrderRecursion();

        System.out.println(); 

        System.out.println("printing nodes of binary tree on post-order");
        t.postOrderRecursion(); 
  }
}

class Tree {
    
    Node root;
    
  static class Node {
    String data;
    Node left, right;

    Node(String value) {
      this.data = value;
      left = right = null;
    }

    boolean isLeaf() {
      return left == null ? right == null : false;
    }

  }

  public void preOrder() {
    preOrder(root);
  }


  private void preOrder(Node n) {
    if (n == null) {
        return;
    }
    System.out.printf("%s ", n.data);
    preOrder(n.left);
    preOrder(n.right);
  }

  public void preOrderRecursion() {
    Stack<Node> nodes = new Stack<>();
    nodes.push(root);

    while (!nodes.isEmpty()) {
      Node current = nodes.pop();
      System.out.printf("%s ", current.data);

      if (current.right != null) nodes.push(current.right);
      
      if (current.left != null)  nodes.push(current.left);
    }
  }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node n) {
        if (n == null) return;

        postOrder(n.left); 
        postOrder(n.right);
        System.out.printf("%s ", n.data);
    }

    public void postOrderRecursion() {
        Stack<Node> nodes = new Stack<>();
        nodes.push(root);

        while (!nodes.isEmpty()) {
            Node current = nodes.peek();

            if (current.isLeaf()) {
                Node node = nodes.pop();
                System.out.printf("%s ", node.data);
            } else {
                if(current.right != null){
                    nodes.push(current.right);
                    current.right = null;
                }

                if(current.left != null){
                    nodes.push(current.left);
                    current.left = null;
                }
            }
        }
    }
}
