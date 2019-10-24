
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Main {
    public static void main(String [] args) {
        
        System.out.println("Inicio del programa:");
        try {
            Dataset ds = new Dataset(new File("./src/data_set.csv"), true);
            System.out.println("Árbol creado");
            
            Data d = new Data(6.8, 23.5, 28.62, 3069, 36, 40, false);
            
            System.out.println("Antes: " + d.getLabel());
            
            d.setLabel(ds);
            
            System.out.println("Después: " + d.getLabel());
           
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
