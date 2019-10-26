import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author juansedo, LizOriana1409
 */
public class Main {
    public static void main(String [] args) {
        try {    
            TrainingDataset ds = new TrainingDataset(new File("./src/testing.csv"));
            
            TreeDraw t = new TreeDraw(ds.getRoot(), ds.getWidth());
            t.show();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
