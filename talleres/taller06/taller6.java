/**
 * Implementación de ArrayList
 * @author jsdiazo, LizOriana1409
 */
class ArrList {
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private char[] text;
	
	/**
     * Método constructor.
     */
    public ArrList() {
        size = 0;
        text = new char[DEFAULT_CAPACITY];
    }
	
	/**
     * @return devuelve el tamaño de la lista.
     */
    int size() {
        return size;
    }

    /**
     * Añade un carácter en la posición dada de la lista.
     *
     * @param pos posición en la que se agrega el carácter (siendo 0 el inicio)
     * @param c carácter agregado
     */
    void add(int pos, char c) {
        char[] textnew;

        if (pos >= text.length) {
            /*Si la posición supera el tamaño actual de la lista*/
            int len = text.length;

            do {
                len += DEFAULT_CAPACITY;
            } while (len < pos);                    //Hasta que el tamaño incluya a la posición

            /*Copia los datos del arreglo*/
            textnew = new char[len];
            for (int i = 0; i < text.length; i++) textnew[i] = text[i];

            /*Agrega el dato*/
            textnew[pos] = c;

        } else if (size + 1 > text.length) {
            /*Si al agregar el dato se supera el tamaño de la lista*/
            textnew = new char[text.length + DEFAULT_CAPACITY];
            
            /*Agrega los datos hasta pos*/
            for (int i = 0; i < pos; i++) textnew[i] = text[i];
            
            /*Agrega pos*/
            textnew[pos] = c;
            
            /*Agrega los datos moviéndolos de lugar*/
            for (int i = pos + 1; i < text.length; i++) textnew[i] = text[i - 1];
            
            textnew[text.length] = text[text.length - 1];
        } else {
            /*Si agregar el elemento no perjudica el tamaño*/
            textnew = new char[text.length];
            
            /*Agrega los datos hasta pos*/
            for (int i = 0; i < pos; i++) textnew[i] = text[i];
            
            /*Agrega pos*/
            textnew[pos] = c;
            
            /*Agrega los datos moviéndolos de lugar*/
            for (int i = pos + 1; i < text.length; i++) textnew[i] = text[i - 1];
        }
        size++;
        text = textnew;
    }
    
    /**
     * Añade un carácter al final de la lista.
     * @param c carácter a añadir
     */
    void add(char c) {
        if (size == text.length) {
            char[] textnew = new char[text.length + DEFAULT_CAPACITY];
            for (int i = 0; i < text.length; i++) {
                textnew[i] = text[i];
            }
            textnew[text.length + 1] = c;
            text = textnew;
        } else {
            text[size++] = c;
        }
    }

    void delete(int pos) {
    /*Verifica si pos esta en el arreglo text*/
        if (pos <= text.length){
		char[] textnew = new char[text.length];
		/*Recorre hasta pos*/
		for (int i = 0; i < pos; i++){
			textnew[i] = text[i]
		} 
		/*Recorre hasta text.length*/
		for (int i = pos; i < text.length; i++){
			textnew[i] = text[i+1];
		}
		text = textnew;
		size--;
	} else {
		text = text;
	}
    }
    
	/**
     * @return devuelve la lista de carácteres como un string donde uno está seguido del otro.
     */
    @Override
    public String toString() {
        String ret = "";
        for(char s : this.text) {
            ret += String.valueOf(s);
        }
        return ret;
    }
}
