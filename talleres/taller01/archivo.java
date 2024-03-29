
/**
 *
 * @author jsdiazo, LizOriana1409
 */
 
 /**
  * PUNTO 1
  * Clase para crear un punto
  */
class Punto {
	/*Coordenadas*/
	private double x;
	private double y;
	
	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double radio() {	
		return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
	}
	
	public double angulo() {	
		/*Pendiente*/
		double m = y/x;
		
		return Math.atan(m);
	}
	
	public double distanciaAPunto(Punto p) {
		double xComponent = p.getX() - x;
		double yComponent = p.getY() - y;
		
		return Math.sqrt( Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
	}
}

/**
 * PUNTO 2
 * Clase para crear una fecha
 */
public class Fecha () {
	
	private byte dia;
	private byte mes;
	private short año;
	
	public Fecha (byte dia, byte mes, short año){
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
	
	
	public byte dia () {
		return dia;
	}
	
	public byte mes () {
		return mes;
	}
	
	public short año () {
		return año;
	}
	
	/**
	 * Devuelve:
	 * -1 si la primera es menor que la segunda
	 * 0 si son iguales 
	 * 1 si la primera es mayor que la segunda
	 */ 
	public int comparar (Fecha date) {
		int resul = 0;
		
		if (año < date.año()) {
			resul = -1;
			return resul;
		} else if (año > date.año() ){
			resul = 1;
			return resul;
		} else {
			if (mes < date.mes() ){
				resul = -1;
				return resul;
			} else if (mes > date.mes() ){
				resul = 1;
				return resul;
			} else {
				if (dia < date.dia() ){
					resul = -1;
					return resul;
				} else if (dia > date.dia() ){
					resul = 1;
					return resul;
				} else {
					return resul;
				}
			}
		}
		return resul;
	}
	
	public String toString () {
		return dia + "-" + mes + "-" + año + "-";
	}
}

/**
 * PUNTO 3
 * Clase para crear un contador
 */
class Contador {
	/*ID y cuenta total*/
	private String id;
	private int cuenta;
	
	public Contador (String id) {
		this.id = id;
		cuenta = 0;
	}
	
	public String getID() {
		return id;
	}
	
	public int incrementos() {
		return cuenta;
	}
	
	public void incrementar() {
		cuenta += 1;
	}
	
	/**
	 * Devuelve el contador en el formato "Contador: 27"
	 */
        @Override
	public String toString() {
		return id + ": " + cuenta;
	}
}
