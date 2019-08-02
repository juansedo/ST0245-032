import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Formas {

    public static void main(String[] args) {
        formas(7);      //Cambiar al valor deseado
    }
    
    /**
     * Utiliza la función formas y print para representar las maneras de organizar
     * unos rectángulos de 2x1 en un espacio de 2xn.
     * @param n El valor para el área
     */
    public static void formas(int n) {
        /*Genera el JFrame*/
        JFrame frame = startWindow("Combinaciones", 800, 600);
        
        /*Imprime el JFrame*/
        print(frame, 10, 5, formas(n, "", new ArrayList<>()), 0);
    }
    
    /**
     * Devuelve un string que representa la cantidad de maneras con las que
     * se ordenan rectángulos de 2x1 en un área de 2xn
     * @param n El valor para el área
     * @param s String que lleva la solución inicial
     * @param output ArrayList con las combinaciones
     * @return La variable output o una lista nueva en caso de error.
     */
    public static ArrayList<String> formas(int n, String s, ArrayList<String> output) {
        if (s.length() == n) output.add(s);
        
        if (s.length() + 1 < n) formas(n, s + "BB", output);
            
        if (s.length() < n) formas(n, s + "A", output);
        
        if (s.length() == 0) return output;
        else return new ArrayList<>();
    }
    
    /**
     * Crea una ventana
     * @param s Nombre de a ventana.
     * @param width Ancho de la ventana.
     * @param height Alto de la ventana.
     * @return Devuelve el JFrame con los datos inicializados.
     */
    public static JFrame startWindow(String s, int width, int height) {
        JFrame frame = new JFrame(s);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        return frame;
    }
    
    /**
     * Imprime en el JFrame fr las soluciones del problema. Imprime cada solución
     * en cada llamado.
     * @param fr JFrame donde se imprime todo
     * @param x Posición en x
     * @param y Posición en y
     * @param sArray Lista de Strings
     * @param pos Posición en la lista
     */
    public static void print(JFrame fr, int x, int y, ArrayList<String> sArray, int pos) {
        if (pos == sArray.size()) return;
        String s = sArray.get(pos);
        
        if (y + 50 >= fr.getHeight()) {
            print(fr, x + s.length()*20 + 30, 5, sArray, pos);
            return;
        }
        
        JPanel rect = new JPanel() {
            @Override
            public void paint(Graphics g) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == 'B') {
                        g.setColor(Color.black);
                        g.drawRect(x + (i * 20), y + 10, 40, 20);
                        
                        g.setColor(Color.blue);
                        g.drawRect(x + (i * 20), y + 30, 40, 20);
                        
                        i++;
                    } else {
                        g.setColor(Color.red);
                        g.drawRect(x + (i * 20), y + 10, 20, 40);
                    }
                }
            }
        };
        rect.setBounds(x, y, x + s.length()*20, 40);
        
        fr.add(rect);
        fr.setVisible(true);
        print(fr, x, y + 50, sArray, pos + 1);
    }
}
