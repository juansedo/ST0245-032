package laboratorio5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Laboratorio5 {

    public static void main(String[] args) {
        try {
            Map map = new Map("./medellin_col.txt");
            System.out.println("Hola mundo");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}

class Node {
    String name;
    String id;
    double posX;
    double posY;
    LinkedList<Relation> sons;
    
    public Node(String id, String x, String y, String name) {
        sons = new LinkedList<>();
        this.id = id;
        this.posX = Double.parseDouble(x);
        this.posY = Double.parseDouble(y);
        this.name = name;
    }
    
    public void add(String name, Node destination, String length) {
        sons.add(new Relation(name, destination, Double.parseDouble(length)));
    }
}

class Relation {
    String name;
    Node destination;
    double length;
    
    public Relation (String name, Node destination, double length) {
        this.name = name;
        this.destination = destination;
        this.length = length;
    }
}

class Map {
    LinkedList<Node> places;
    
    public Map(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        places = new LinkedList<>();
        
        while (!in.nextLine().contains("Vertices")) {
            
        }
        
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.contains("Arcos.")) break;
            
            String[] str = line.split(" ");
            
            if (str.length <= 1) continue;
            
            String name;
            if (str.length > 3) {
                name = str[3];
                for (int i = 4; i < str.length; i++) {
                    name = " " + str[i];
                }
            }
            else name = "N/A";
            
            places.add(new Node(str[0], str[1], str[2], name));
        }

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] str = line.split(" ");
            
            if (str.length <= 1) continue;
            
            String name;
            if (str.length > 3) {
                name = str[3];
                for (int i = 4; i < str.length; i++) {
                    name = " " + str[i];
                }
            }
            else name = "N/A";
            
            for (Node first_node : places) {
                if (first_node.id.equals(str[0])) {
                    for (Node scnd_node : places) {
                        if (scnd_node.id.equals(str[1])) {
                            first_node.add(name, scnd_node, str[2]);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}
