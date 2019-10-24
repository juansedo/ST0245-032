import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase que representa los datos sobre una planta de café
 * como ph, temperatura del suelo, iluminación, entre otros.
 * @author juansedo, LizOriana1409
 */
public final class Data {

    //Información de la planta
    private final double ph;
    private final double sTemperature;
    private final double sMoisture;
    private final double illuminance;
    private final double eTemperature;
    private final double eHumidity;
    private boolean label;

    /**
     * Constructor de la clase dato
     * @param ph Nivel de ph
     * @param sTemp Temperatura en el suelo
     * @param sMois Nivel de humedad del suelo
     * @param illum Iluminación
     * @param eTemp Temperatura del ambiente
     * @param eHum Nivel de humedad del ambiente
     * @param label <tt>yes</tt> si el dato tiene roya, <>no</tt> en caso contrario
     */
    public Data(double ph, double sTemp, double sMois, double illum, double eTemp, double eHum, boolean label) {
        this.ph = ph;
        sTemperature = sTemp;
        sMoisture = sMois;
        illuminance = illum;
        eTemperature = eTemp;
        eHumidity = eHum;
        this.label = label;
    }
    
    /**
     * @return El ph del dato.
     */
    public double getPh() {
        return ph;
    }

    /**
     * @return La temperatura del suelo.
     */
    public double getsTemperature() {
        return sTemperature;
    }

    /**
     * @return La humedad del suelo.
     */
    public double getsMoisture() {
        return sMoisture;
    }

    /**
     * @return La iluminación.
     */
    public double getIlluminance() {
        return illuminance;
    }

    /**
     * @return La temperatura ambiente.
     */
    public double geteTemperature() {
        return eTemperature;
    }

    /**
     * @return La humedad ambiente.
     */
    public double geteHumidity() {
        return eHumidity;
    }

    /**
     * @return La etiqueta de si tiene o no tiene roya.
     */
    public boolean getLabel() {
        return label;
    }
    
    /**
     * Permite definir la etiqueta de un dato
     * para saber si tiene roya o no a través
     * de un árbol de decisión.
     * @param ds Dataset con árbol de decisión.
     * @throws Exception Método {@link #getValue}.
     */
    public void setLabel(Dataset ds) throws Exception {
        if(ds.getRoot() != null) setLabel(ds.getRoot());
        else System.out.println("Dataset no considerado de ejemplo");
    }
    
    /**
     * Método recursivo auxiliar para {@link setLabel}
     * @param n Nodo del árbol actual.
     * @throws Exception Método {@link #getValue}
     */
    private void setLabel(Node n) throws Exception {
        //Si hay un nodo nulo, se llegó a la hoja
        if(n.yes == null) this.label = n.answer;
        else {
            //Se define el lado por el que se continua
            Node op = (n.compare(this.getValue(n.reason)))? n.yes: n.no;
            setLabel(op);
        }
    }
    
    /**
     * Devuelve el valor de un valor del dato específico.
     * Mirar {@link DataType} para más información.
     * @param t Tipo de dato.
     * @return El valor del dato pedido.
     * @throws Exception El dato pedido no existe.
     */
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

/**
 * Clase que representa un conjunto de datos de la clase {@link Data}.
 * Contiene operaciones para añadir datos y crear su propio árbol de
 * decisión en el caso en que se considere a este conjunto como uno
 * de entrenamiento.
 * @author juansedo, LizOriana1409
 */
final class Dataset {
    private LinkedList<Data> data;  //Lista con los datos
    private Node rootDT;    //Nodo raíz del árbol de entrenamiento
    
    /**
     * Constructor para la clase Dataset.
     * @param f Archivo .csv con los datos de las plantas de café.
     * @param isTrainingData Define si el Dataset es un conjunto de datos
     * de entrenamiento.
     * @throws FileNotFoundException Cuando no se encuentra el archivo.
     */
    public Dataset(File f, boolean isTrainingData) throws FileNotFoundException {
        Scanner in = new Scanner(f);
        data = new LinkedList<>();
        
        //Ignora la primera linea, donde está la descripción de cada columna
        in.nextLine();
        while (in.hasNextLine()) {
            addData(in.nextLine());
        }
        
        //Si es Dataset de entrenamiento, se crea el árbol
        if (isTrainingData) rootDT = createTree(data);
    }
    
    /**
     * Agrega un dato al conjunto de datos. Esto lo hace
     * por medio de un String con el siguiente formato:
     * <br>
     * ph,stemp,smois,illum,etemp,ehum,label
     * <br>
     * Si no se tiene el formato, no se agrega y la consola marca un error.
     * @param toSplit String con formato de .csv
     * @return El dato que fue agregado.
     */
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
    
    /**
     * Método que crea un árbol sobre un nodo de la clase {@link Node} a partír
     * de una LinkedList de datos entregada.
     * Este método hace recursión para definir las demás ramas del árbol.
     * @param dataset Arreglo de datos. 
     * @return Nodo raíz del árbol.
     */
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
    
    /**
     * @return Nodo raíz del árbol 
     */
    public Node getRoot() {
        return rootDT;
    }
}

/**
 * Clase que representa un nodo de un árbol de decisión.
 * @author juansedo, LizOriana1409
 */
class Node {
    /**
     * Tipo de dato que se evalúa en el nodo
     */
    public DataType reason;
    /**
     * Valor con el que se evalúa
     */
    public double value;
    /**
     * Etiqueta a asginar en caso de ser una hoja
     */
    public boolean answer;
    /**
     * Nodos hijos
     */
    public Node yes, no;
    
    /**
     * Constructor de la clase Node.
     * @param value Valor a guardar.
     * @param t Tipo de dato que se evalúa.
     */
    public Node(double value, DataType t) {
        this.value = value;
        this.reason = t;
    }

    /**
     * Constructor de la clase Node (se usa si el nodo es una hoja).
     * @param answer Etiqueta a asignar.
     */
    public Node(boolean answer) {
        this.answer = answer;
    }
    
    /**
     * @param n Valor a evaluar.
     * @return true si el valor dado es mayor que el valor del nodo y false en
     * caso contrario.
     */
    public boolean compare(double n) {
        return n > value;
    }
}

/**
 * Clase con métodos para hacer cálculos necesarios para la ganancia
 * @author juansedo, LizOriana1409
 */
class Gain {
    /**
     * Genera el valor de entropía de una lista de datos de la clase
     * {@link Data}.
     * @param dataset Conjunto de datos para obtener su entropía
     * @return Entropía del conjunto de datos.
     * @throws Exception Cuando no hay datos en el conjunto que se entregó.
     */
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
    
    /**
     * Genera el valor de entropía parcial de un dato específico.
     * Hace parte de la fórmula de la ganancia de información y es el valor
     * que se le restará a la entropía general. Entre más pequeño este valor,
     * mejor es la calidad de la variable.
     * @param dataset Conjunto de datos para dividir y obtener su entropía parcial.
     * @param t Tipo de dato que se analizará.
     * @return Un arreglo de double que incluye el valor mínimo de entropía y el valor
     * con el que se separaron los datos para lograrlo.
     * @throws Exception Puede ser el error de {@link Data#getValue} o el de {@link #generalEntropy}
     */
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

/**
 * Enumerador que define la información que tiene todo dato.
 * <ul>
 *  <li>{@link #PH}: Nivel de PH</li>
 *  <li>{@link #STEMP}: Temperatura del suelo</li>
 *  <li>{@link #SMOIS}: Humedad del suelo</li>
 *  <li>{@link #ILLUM}: Iluminación</li>
 *  <li>{@link #ETEMP}: Temperatura ambiente</li>
 *  <li>{@link #EHUM}: Humedad ambiente</li>
 * </ul>
 * @author juansedo, LizOriana1409
 */
enum DataType {
    PH, STEMP,
    SMOIS, ILLUM,
    ETEMP, EHUM
}
