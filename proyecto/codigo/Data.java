
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Data {

    private double ph;
    private double sTemperature;
    private double sMoisture;
    private double illuminance;
    private double eTemperature;
    private double eHumidity;
    private boolean label;

    public Data() {
        ph = 0;
        sTemperature = 0;
        sMoisture = 0;
        illuminance = 0;
        eTemperature = 0;
        eHumidity = 0;
        label = false;
    }
    
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

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getsTemperature() {
        return sTemperature;
    }

    public void setsTemperature(double sTemperature) {
        this.sTemperature = sTemperature;
    }

    public double getsMoisture() {
        return sMoisture;
    }

    public void setsMoisture(double sMoisture) {
        this.sMoisture = sMoisture;
    }

    public double getIlluminance() {
        return illuminance;
    }

    public void setIlluminance(double illuminance) {
        this.illuminance = illuminance;
    }

    public double geteTemperature() {
        return eTemperature;
    }

    public void seteTemperature(double eTemperature) {
        this.eTemperature = eTemperature;
    }

    public double geteHumidity() {
        return eHumidity;
    }

    public void seteHumidity(double eHumidity) {
        this.eHumidity = eHumidity;
    }

    public boolean getLabel() {
        return label;
    }

    public void setLabel(boolean label) {
        this.label = label;
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

class Dataset {

    private LinkedList<Data> data;
    Node rootDT;

    public Dataset(File f, boolean example) throws FileNotFoundException {
        Scanner in = new Scanner(f);
        
        data = new LinkedList<>();
        in.nextLine();
        
        while (in.hasNextLine()) {
            addData(in.nextLine());
        }
        if (example) {
            rootDT = new Node();
            rootDT = createTree(data);
        }
    }
    
    public final Data addData (String toSplit) {
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
            System.out.println("Error agregando -> " + str);
            System.out.println("Problema con formato y comas.");
            return null;
        }
    }

    public final Data addData(Data d) {
        data.add(d);
        return d;
    }

    private Node createTree(LinkedList<Data> dataset) {
        
        if(dataset.size() == 1) {
            return new Node(data.getFirst().getLabel());
        }
        
        try {
            if(dataset.isEmpty()) throw new Exception("Dataset vacío");
            double entropy_g = Gain.generalEntropy(dataset);
            if (entropy_g == 0) return new Node(data.getFirst().getLabel());
            
            double min_ent = Double.MAX_VALUE;
            double node_value = Double.MAX_VALUE;
            DataType sel_type = DataType.PH;
            
            for(DataType t : DataType.values()) {
                double [] test = Gain.partialEntropy(dataset, t);
                if (test[0] < min_ent) {
                    min_ent = test[0];
                    node_value = test[1];
                    sel_type = t;
                }
            }
            
            System.out.println(min_ent);
            
            //Nodo creado
            Node n = new Node(node_value, sel_type);
            
            //Crear las ramas
            LinkedList<Data> s1 = new LinkedList<>(), s2 = new LinkedList<>();
            for (Data d : dataset) {
                if (n.compare(d.getValue(sel_type))) s1.add(d);
                else s2.add(d);
            }
            
            Node m = createTree(s1);
            n.yes = (m != null)? m: new Node(true);
            
            m = createTree(s2);
            n.no = (m != null)? m: new Node(false);
            
            return n;
            
            /*
            testGain.setPh(entropy_g - Gain.partialEntropy(dataset, DataType.PH));
            testGain.setsTemperature(entropy_g - Gain.partialEntropy(dataset, DataType.STEMP));
            testGain.setsMoisture(entropy_g - Gain.partialEntropy(dataset, DataType.SMOIS));
            testGain.setIlluminance(entropy_g - Gain.partialEntropy(dataset, DataType.ILLUM));
            testGain.seteTemperature(entropy_g - Gain.partialEntropy(dataset, DataType.ETEMP));
            testGain.seteHumidity(entropy_g - Gain.partialEntropy(dataset, DataType.EHUM));
            */
            
            
            /*
            System.out.println("PH: " + testGain.getPh());
            System.out.println("STEMP: " + testGain.getsTemperature());
            System.out.println("SMOIS: " + testGain.getsMoisture());
            System.out.println("ILLUM: " + testGain.getIlluminance());
            System.out.println("ETEMP: " + testGain.geteTemperature());
            System.out.println("EHUM: " + testGain.geteHumidity());
            */
            
            
            
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Deprecated
    public double MinValue(DataType t) throws Exception {
        double min = Double.MAX_VALUE;
        for (Data d : this.data) {
            double test = d.getValue(t);
            min = (test < min)? test: min;
        }
        return min;
    }
}

class Node {
    
    public DataType reason;
    public double value;
    public boolean answer;
    public Node yes;
    public Node no;

    public Node() {
        value = 0;
    }

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
        
        if(S_total == 0) throw new Exception("Conjunto vacío");
        
        double yes = 0, no = 0;
        
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
