
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author juansedo, LizOriana1409
 */
public final class Data {

    private final double ph;
    private final double sTemperature;
    private final double sMoisture;
    private final double illuminance;
    private final double eTemperature;
    private final double eHumidity;
    private boolean label;

    public Data(double ph, double sTemp, double sMois, double illum, double eTemp, double eHum, boolean label) {
        this.ph = ph;
        sTemperature = sTemp;
        sMoisture = sMois;
        illuminance = illum;
        eTemperature = eTemp;
        eHumidity = eHum;
        this.label = label;
    }
    
    public double getPh() {
        return ph;
    }

    public double getsTemperature() {
        return sTemperature;
    }

    public double getsMoisture() {
        return sMoisture;
    }

    public double getIlluminance() {
        return illuminance;
    }

    public double geteTemperature() {
        return eTemperature;
    }

    public double geteHumidity() {
        return eHumidity;
    }

    public boolean getLabel() {
        return label;
    }
    
    public void setLabel(Dataset ds) throws Exception {
        if(ds.getRoot() != null) setLabel(ds, ds.getRoot());
        else System.out.println("Dataset no considerado de ejemplo");
    }
    
    public void setLabel(Dataset ds, Node n) throws Exception {
        if(n.yes == null) this.label = n.answer;
        else {
            Node op = (n.compare(this.getValue(n.reason)))? n.yes: n.no;
            setLabel(ds, op);
        }
    }
    
    public double getValue(DataType t) throws Exception {
        switch (t) {
            case PH:
                return getPh();
            case STEMP:
                return getsTemperature();
            case SMOIS:
                return getsMoisture();
            case ILLUM:
                return getIlluminance();
            case ETEMP:
                return geteTemperature();
            case EHUM:
                return geteHumidity();
            default:
                throw new Exception("No existe el tipo de dato mencionado");
        }
    }
}

final class Dataset {

    private LinkedList<Data> data;
    private Node rootDT;
    
    public Dataset(File f, boolean example) throws FileNotFoundException {
        Scanner in = new Scanner(f);
        data = new LinkedList<>();
        
        //Ignora la primera linea, donde está la descripción de cada columna
        in.nextLine();
        
        while (in.hasNextLine()) {
            addData(in.nextLine());
        }
        
        //Si es Dataset de entrenamiento, se crea el árbol
        if (example) {
            rootDT = createTree(data);
        }
    }
    
    public Data addData (String toSplit) {
        String[] str = toSplit.split(",");
        try {
            Data d = new Data(Double.parseDouble(str[0]), Double.parseDouble(str[1]),
                    Double.parseDouble(str[2]), Double.parseDouble(str[3]),
                    Double.parseDouble(str[4]), Double.parseDouble(str[5]),
                    str[6].contains("yes"));
            data.add(d);
            return d;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Error agregando -> " + toSplit);
            System.out.println("Problema con formato y comas.");
            return null;
        }
    }
    
    private Node createTree(LinkedList<Data> dataset) {
        
        //Un único elemento define el nodo
        if(dataset.size() == 1) {
            return new Node(dataset.getFirst().getLabel());
        }
        
        try {
            //Sin elementos, arrojamos nulo
            if(dataset.isEmpty()) throw new Exception("Dataset vacío");
            double entropy_g = Gain.generalEntropy(dataset);
            
            //Entropía = 0 representa homogeneidad en los datos
            if (entropy_g == 0) return new Node(dataset.getFirst().getLabel());
            
            //Se buscan el mínimo de entropía en los datos y
            //el valor del nodo y el tipo de dato que lo logran
            double min_ent = Double.MAX_VALUE;
            double node_value = Double.MAX_VALUE;
            DataType sel_type = DataType.PH;
            
            for(DataType t : DataType.values()) {
                //test[0] -> Mínimo de entropia, test[1] -> Valor con el que se logra
                double [] test = Gain.partialEntropy(dataset, t);
                if (test[0] < min_ent) {
                    min_ent = test[0];
                    node_value = test[1];
                    sel_type = t;
                }
            }
            
            //Nodo creado
            Node n = new Node(node_value, sel_type);
            
            //Se crean los subconjuntos
            LinkedList<Data> s1 = new LinkedList<>(), s2 = new LinkedList<>();
            for (Data d : dataset) {
                if (n.compare(d.getValue(sel_type))) s1.add(d);
                else s2.add(d);
            }
            
            //Se crean los nodos hijos
            Node m = createTree(s1);
            n.yes = (m != null)? m: new Node(true);
            
            m = createTree(s2);
            n.no = (m != null)? m: new Node(false);
            
            return n;
        }
        catch(Exception e) {
            return null;
        }
    }
    
    public Node getRoot() {
        return rootDT;
    }
}

class Node {
    public DataType reason;
    public double value;
    public boolean answer;
    public Node yes, no;

    public Node(double value, DataType t) {
        this.value = value;
        this.reason = t;
    }

    public Node(boolean answer) {
        this.answer = answer;
    }

    public boolean compare(double n) {
        return n > value;
    }
}

class Gain {
    static double generalEntropy(LinkedList<Data> dataset) throws Exception {
        int S_total = dataset.size();
        
        //Sin datos no se calcula entropía
        if(S_total == 0) throw new Exception("Conjunto vacío");
        
        double yes = 0, no = 0;
        
        //Se averigua cantidad de yes y no
        for (Data d : dataset) {
            if (d.getLabel()) yes++;
            else no++;
        }
        
        if (yes == 0 || no == 0) return 0;
        double a = -(yes /S_total) * Math.log(yes / S_total) / Math.log(2);
        double b = -(no / S_total) * Math.log(no / S_total) / Math.log(2);
        return a + b;
    }
    
    static double [] partialEntropy(LinkedList<Data> dataset, DataType t) throws Exception {
        LinkedList<Data> s1 = new LinkedList<>(), s2 = new LinkedList<>();
        
        double min = Double.MAX_VALUE;
        double measure_d = 0;
        
        for (Data data: dataset) {
            double measure = data.getValue(t);
            
            //Separación de los grupos
            for (Data d : dataset) {
                double test = d.getValue(t);
                if (test <= measure) s1.add(d);
                else s2.add(d);
            }
            
            //Entropía de clasificaciones
            double entropy_s1 = Gain.generalEntropy(s1);
            double entropy_s2 = (!s2.isEmpty())? Gain.generalEntropy(s2) : 0;
            
            //Suma de las entropías
            double test = (s1.size()*entropy_s1 + s2.size()*entropy_s2) / dataset.size();
            
            if (test < min)  {
                min = test;
                measure_d = measure;
            }
            
            //Resetear las listas
            s1.clear();
            s2.clear();
        }
        
        return new double[]{min, measure_d};
    }
}

enum DataType {
    PH, STEMP,
    SMOIS, ILLUM,
    ETEMP, EHUM
}
