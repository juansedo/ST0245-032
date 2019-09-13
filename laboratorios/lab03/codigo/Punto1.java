import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Punto1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in, "ISO-8859-1");
        //LOS ARCHIVOS DEBEN ESTAR EN LA CARPETA DEL CÓDIGO FUENTE
        File[] archives = new File(".\\").listFiles();
        ArrayList<Dato> datos = new ArrayList<>();
        
        System.out.println("Archivos disponibles: ");
        for (int i = 0; i < archives.length; i++) {
            if (archives[i].isFile()) {
                String pathname = archives[i].getName();
                int dotPos = pathname.lastIndexOf(".");
                String ext = pathname.substring(dotPos);
                
                if (ext.equals(".csv")) {
                    Scanner file = new Scanner(archives[i].getAbsoluteFile());
                    file.nextLine();
                    while (file.hasNextLine()) {
                        Dato d = new Dato(file.nextLine().split(","));
                        if (!d.getName().equals("")) datos.add(d);
                    }
                }
            }
        }
        
        System.out.println("Escriba nombre del curso:");
        String curso = in.nextLine();
        
        curso = (curso.contains("FUNDAMENTOS"))? "FUNDAMENTOS DE PROGRAMACIÓN" :
                (curso.contains("1"))? "ESTRUCTURA DATOS Y ALGORÍTMOS 1": "ESTRUCTURA DATOS Y ALGORÍTMOS 2";
        
        System.out.println("NOMBRE                " + curso.toUpperCase());
        
        String actualName = "";
        for (int i = 0; i < datos.size(); i++) {
            Dato d = datos.get(i);
            if (d.getName().equals(actualName)) continue;
            else if(d.getTopicName().equals(curso)) {
                actualName = d.getName();
                System.out.println(d.toStringCurso());
            }
        }
        
        System.out.println("\n\nEscriba el semestre:");
        String semestre = in.nextLine();
        System.out.println("Escriba nombre del estudiante:");
        String estudiante = in.nextLine();
        while (estudiante.length() < 22) estudiante += " ";
        
        System.out.println("NOMBRE                " + "FUND. DE PROGRAMACIÓN  " + "DATOS Y ALGORÍTMOS 1   " + "DATOS Y ALGORÍTMOS 2   ");
        String actualTopicID = "";
        System.out.print(estudiante);
        estudiante = estudiante.trim();
        
        for (int i = 0; i < datos.size(); i++) {
            Dato d = datos.get(i);
            if (d.getTopicID().equals(actualTopicID));
            else if(d.getName().equals(estudiante) && d.getSemester().equals(semestre)) {
                actualTopicID = d.getTopicID();
                System.out.print(d.getDefinitive() + "                     ");
            }
        }
        System.out.println();
    }
}

/**
 * Clase que representa un dato de la lista
 * @author juansedo, LizOriana1409
 */
class Dato {

    private String name;
    private String id;
    private String topic_id;
    private String semester;
    private String group;
    private String evaluation;
    private String percent;
    private String description;
    private String topic_name;
    private String note;
    private String definitive;
    
    public Dato (String [] s) {
        String [] fields = new String [14];
        for (int i = 0; i < fields.length; i++) {
            if (i >= s.length) {
                fields[i] = "";
                continue;
            }
            fields[i] = s[i].replace("\"", "");
        }
        name = fields[0];
        id = fields[1];
        topic_id = fields[2];
        semester = fields[3];
        group = fields[4];
        evaluation = fields[7];
        percent = fields[8];
        description = fields[9];
        topic_name = fields[10];
        note = fields[12];
        definitive = fields[13];
    }

    public String getName() {
        return name;
    }
    
    public String getID() {
        return id;
    }

    public String getTopicID() {
        return topic_id;
    }

    public String getSemester() {
        return semester;
    }

    public String getGroup() {
        return group;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public String getPercent() {
        return percent;
    }

    public String getDescription() {
        return description;
    }

    public String getTopicName() {
        return topic_name;
    }

    public String getNote() {
        return note;
    }

    public String getDefinitive() {
        return definitive;
    }
    
    public String toStringCurso() {
        String NAME = fillBlanks(name, 20);
        String DEFINITIVE = fillBlanks(definitive, 10);
        
        return NAME + "  " + DEFINITIVE;
    }
    
    private String fillBlanks(String str, int n) {
        while (str.length() < n) str += " ";
        return str;
    }    
}
