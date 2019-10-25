import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * @author juansedo, LizOriana1409
 */
public class TreeDraw extends JPanel {
    /*CONSTANTES*/
    private final int H_SIZE = 1366;     //Tamaño horizontal
    private final int V_SIZE = 768;      //Tamaño vertical
    private final int H_GAP = 10;        //Separación horizontal
    private final int V_GAP = 20;        //Separación vertical
    private final int N_SIZE = 15;       //Tamaño del nodo
    
    /*Medidas para el dibujo*/
    private int interval;                //Intervalo
    private int width;                   //Ancho máximo del árbol
    
    /*Marco y nodo raíz*/
    private JFrame frame;
    private Node root;
    
    public TreeDraw(Node root, int width) {
        this.root = root;
        frame = new JFrame();
        frame.setSize(H_SIZE, V_SIZE);
        frame.setContentPane(this);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.width = width;
        interval = H_SIZE/width;
    }
    
    public void show() {
        frame.setVisible(true);
    }
    
    private void print(Graphics g, Node n, int start, int x, int y) {
        if (n != null) {
            frame.setVisible(true);
            
            Color color;
            if (n.type != null)
                color = n.type.color;
            else
                color = (n.answer)? Color.LIGHT_GRAY: Color.BLACK;
            
            if (n.no != null) {
                connect(g, x, y, x -  (x - start) / 2, y + V_GAP);
                print(g, n.no, x, x - (x - start) / 2, y + V_GAP);
            }
            
            if (n.yes != null) {
                connect(g, x, y, x + (x - start) / 2, y + V_GAP);
                print(g, n.yes, x, x + (x - start) / 2, y + V_GAP);
            }
            put(g, color, x-10, y-5);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        print(g, root, 0, interval * ((width)/2), 10);
        setBackground(Color.WHITE);
    }
    
    public void put(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, N_SIZE, N_SIZE);
    }
    
    public void connect(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
    
}