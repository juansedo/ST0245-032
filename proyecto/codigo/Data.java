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
     private double soil_temperature;
     private double soil_moisture;
     private double iluminance;
     private double env_temperature;
     private double env_humidity;
     private boolean label;
     
     public Data(){
          ph = 0;
          soil_temperature = 0;
          soil_moisture = 0;
          iluminance = 0;
          env_temperature = 0;
          env_humidity = 0;
          label = false;
     }
     
     public Data(double ph, double stemp, double mois, double ilum, double etemp, double ehum, boolean label){
          this.ph = ph;
          soil_temperature = stemp;
          soil_moisture = mois;
          iluminance = ilum;
          env_temperature = etemp;
          env_humidity = ehum;
          this.label = label;
     }
     
     public void defineLabel (Dataset dataset){
          
     }
     
     public double getPh() {
          return ph;
     }

     public void setPh(double ph) {
          this.ph = ph;
     }

     public double getSoil_temperature() {
          return soil_temperature;
     }

     public void setSoil_temperature(double soil_temperature) {
          this.soil_temperature = soil_temperature;
     }

     public double getSoil_moisture() {
          return soil_moisture;
     }

     public void setSoil_moisture(double soil_moisture) {
          this.soil_moisture = soil_moisture;
     }

     public double getIluminance() {
          return iluminance;
     }

     public void setIluminance(double iluminance) {
          this.iluminance = iluminance;
     }

     public double getEnv_temperature() {
          return env_temperature;
     }

     public void setEnv_temperature(double env_temperature) {
          this.env_temperature = env_temperature;
     }

     public double getEnv_humidity() {
          return env_humidity;
     }

     public void setEnv_humidity(double env_humidity) {
          this.env_humidity = env_humidity;
     }

     public boolean getLabel() {
          return label;
     }
}


class Dataset{
     
     private LinkedList<Data> data = new LinkedList<>();
     Node rootDT;
     
     public Dataset(File f, boolean example) throws FileNotFoundException{
          Scanner in = new Scanner(f);
          
          in.nextLine();
          while(in.hasNextLine()) {
               String [] str = in.nextLine().split(",");
               Data d = new Data(Integer.parseInt(str[0]), Integer.parseInt(str[1]),
                                 Integer.parseInt(str[2]), Integer.parseInt(str[3]),
                                 Integer.parseInt(str[4]), Integer.parseInt(str[5]), 
                                 str[6].contains("yes"));
               data.add(d);
          }
          
          if (example) {
               rootDT = new Node();
               rootDT = createTree(rootDT, data);
          } else {
               
          }
          
     }
     
     private void addData(String str){
     
     }
     
     public void addData (Data d){
          
     }
     
     private Node createTree (Node n, LinkedList<Data> dataset) {
          //CASO BASE: Un unico dato en stack
          //Se crea una hoja
          
          
          int S_total = dataset.size();
          int yes = 0; 
          int no = 0;
          
          for(Data d: dataset) {
               if(d.getLabel()) yes++;
               else no++;
          }
          
          double entropy_g = -(yes/S_total)*Math.log(yes/S_total)/Math.log(2)
                             -(no/S_total)*Math.log(no/S_total)/Math.log(2);
          
          Data testGain = new Data();
          //Pasar por los datos del stack
          for(Data d: dataset) {
              testGain.setPh(testGain.getPh() + d.getPh()); //Completar con la ecuacion del documento ac9... ecuacion 3 
              testGain.setSoil_temperature(testGain.getSoil_temperature() + d.getSoil_temperature());
              testGain.setSoil_moisture(testGain.getSoil_moisture() + d.getSoil_moisture());
              testGain.setIluminance(testGain.getIluminance() + d.getIluminance());
              testGain.setEnv_temperature(testGain.getEnv_temperature() + d.getEnv_temperature());
              testGain.setEnv_humidity(testGain.getEnv_humidity() + d.getEnv_humidity());
          }
          testGain.setPh(-(yes/S_total)*testGain.getPh() - (no/S_total)*testGain.getPh());
          testGain.setSoil_temperature((yes/S_total)*testGain.getSoil_temperature() - (no/S_total)*testGain.getSoil_temperature());
          testGain.setSoil_moisture((yes/S_total)*testGain.getSoil_moisture() - (no/S_total)*testGain.getSoil_moisture());
          testGain.setIluminance((yes/S_total)*testGain.getIluminance() - (no/S_total)*testGain.getIluminance());
          testGain.setEnv_temperature((yes/S_total)*testGain.getEnv_temperature() - (no/S_total)*testGain.getEnv_temperature());
          testGain.setEnv_humidity((yes/S_total)*testGain.getEnv_humidity() - (no/S_total)*testGain.getEnv_humidity());
          
          
          
          
          
          //Obtener ganancia de informacion
          
          //CASO BASE: Ganancia no muy alta (gain < 0.8)
          //Media de yes or no
          
          
          
          
          Node m = new Node(5);
          
          return m;
     }
}


class Node {

     public double value;
     public boolean answer;
     public Node yes;
     public Node no;
     
     public Node (){
          value = 0;
     }
     
     public Node (double value){
          this.value = value;
     }
     
     public Node (boolean answer){
          this.answer = answer;
     }
     
     public boolean compare (double n){
          return n >= value;
     }
}
