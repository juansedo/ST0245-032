/**
 * Implementación de ArrayList
 * @author jsdiazo, LizOriana1409
 */
class ArrList {
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private char[] text;

    public ArrList() {
        size = 0;
        text = new char[DEFAULT_CAPACITY];
    }

    int size() {
        return size;
    }

    void add(int pos, char str) {
        if (pos >= text.length) {
            int len = text.length;

            do {
                len += DEFAULT_CAPACITY;
            } while (len < pos);
            
            char[] textnew = new char[len];
            for (int i = 0; i < text.length; i++) {
                textnew[i] = text[i];
            }
            textnew[pos] = str;
            text = textnew;
        } else {
            if (size + 1 > text.length) {
                char[] textnew = new char[text.length + DEFAULT_CAPACITY];
                for (int i = 0; i < pos; i++) {
                    textnew[i] = text[i];
                }
                textnew[pos] = str;
                for (int i = pos + 1; i < text.length; i++) {
                    textnew[i] = text[i-1];
                }
                textnew[text.length] = text[text.length-1];
                text = textnew;
            } else {
                char[] textnew = new char[text.length];
                for (int i = 0; i < pos; i++) {
                    textnew[i] = text[i];
                }
                textnew[pos] = str;
                for (int i = pos + 1; i < text.length; i++) {
                    textnew[i] = text[i-1];
                }
                text = textnew;
            }
            size++;
        }
    }

    void add(char str) {
        if (size == text.length) {
            char[] textnew = new char[text.length + DEFAULT_CAPACITY];
            for (int i = 0; i < text.length; i++) {
                textnew[i] = text[i];
            }
            textnew[text.length + 1] = str;
            text = textnew;
        } else {
            text[size++] = str;
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
    
    @Override
    public String toString() {
        String ret = "";
        for(char s : this.text) {
            ret += String.valueOf(s);
        }
        return ret;
    }
}
