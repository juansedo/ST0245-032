import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Punto2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        //Inicio del programa
        
        System.out.print("Nodos: ");
        
        String line = in.nextLine();
        boolean isZero = line.trim().equals("0");
        
        try {
            while (!isZero) {
                int nodes = Integer.parseInt(line);
                System.out.print("Arcos: ");
                int arcs = Integer.parseInt(in.nextLine());
                
                Graph graph = new Graph(nodes);
                System.out.println("Ingrese las conexiones (Formato: nodo nodo)");
                for(int i = 0; i < arcs; i++) {
                    line = in.nextLine();
                    String [] rel = line.split(" ");
                    
                    if (rel.length >= 2) {
                        int first = Integer.parseInt(rel[0]), second = Integer.parseInt(rel[1]);
                        if (first >= 0 && first < nodes && second >= 0 && second < nodes)
                            graph.addRelation(first, second);
                        else
                            throw new Exception("Error: Valores ingresados no válidos.");
                    }
                    else {
                        throw new Exception("Error: Faltaron arcos por definir.");
                    }
                }
                
                if (graph.isBicoloreable()) System.out.println("BICOLOREABLE");
                else System.out.println("NO BICOLOREABLE");
                
                System.out.println();
                System.out.print("Nodos: ");
                line = in.nextLine();
                isZero = line.trim().equals("0");
            }
            
            
            //Fin del programa
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Ingresa solo números.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Graph {
    boolean [][] matrix;
    
    public Graph(int size) {
        matrix = new boolean[size][size];
    }
    
    public void addRelation(int fst, int snd) {
        matrix[fst][snd] = true;
        matrix[snd][fst] = true;
    }
    
    public boolean isBicoloreable() {
        return isBicoloreable(0);
    }
    
    private boolean isBicoloreable(int splitter) {
        if (matrix == null) return true;
        if (splitter >= matrix.length) return false;
        
        LinkedList<Integer> sndGroup = new LinkedList<>();
        
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[splitter][j]) sndGroup.add(j);
        }

        for (int j : sndGroup) {
            for (int k : sndGroup) {
                if (matrix[j][k]) return isBicoloreable(splitter + 1);
            }
        }
        sndGroup.clear();
        return true;
    }
}
