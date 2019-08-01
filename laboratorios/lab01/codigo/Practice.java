package practice;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Practice {
    
    public static void main(String[] args) {
        
        ArrayList<String> sArray = new ArrayList<>();
        formas(6, "", sArray);
        
        JFrame frame = new JFrame("Hola mundo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        print(frame, 0, sArray, 0);
    }
    
    public static void formas(int n, String s, ArrayList<String> output) {
        if (s.length() == n) {
            output.add(s);
        }
        if (s.length() + 1 < n) {
            formas(n, s + "BB", output);
        }
        if (s.length() < n) {
            formas(n, s + "A", output);
        }
    }
    
    public static void print(JFrame fr, int y, ArrayList<String> sArray, int pos) {
        if (pos == sArray.size()) return;
        
        fr.add( new Rect(0, y, sArray.get(pos)) );
        fr.setVisible(true);
        
        print(fr, y+30, sArray, pos + 1);
    }
}

class Rect extends JPanel {
    
    private int x, y;
    private String s;
    
    public Rect(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }
    
    @Override
    public void paint(Graphics g) {
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                g.setColor(Color.black);
                g.drawRect(10 + (i*10), 10 + y, 20, 10);
                
                g.setColor(Color.blue);
                g.drawRect(10 + (i*10), 20 + y, 20, 10);
                
                i++;
            } else {
                g.setColor(Color.red);
                g.drawRect(10 + (i*10), 10 + y, 10, 20);
            }
        }
    }
}
