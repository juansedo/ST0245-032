import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class ProblemasPunto2 {

    public static void main(String[] args) {
        /*Casos de prueba*/
        
        // PUNTO 2.1
        //System.out.println(keyboardError("This_is_a_[Beiju]_text"));
        //System.out.println(keyboardError("[[]][][]Happy_Birthday_to_Tsinghua_University"));
        //System.out.println(keyboardError("asd[fgh[jkl"));
        //System.out.println(keyboardError("asd[fgh[jkl["));
        //System.out.println(keyboardError("[[a[[d[f[[g[g[h[h[dgd[fgsfa[f"));
        //System.out.println(keyboardError("asd[gfh[[dfh]hgh]fdfhd[dfg[d]g[d]dg"));
        
        
        // PUNTO 2.2 (Ejecuta el programa, las pruebas se hacen en tiempo de ejecución)
        //blockProblem();
        
    }

    static String keyboardError(String str) {
        LinkedList<String> list = new LinkedList<String>();

        boolean addLast = true;
        int left = 0;
        for (int right = 0; right < str.length(); right++) {
            if (str.charAt(right) == '[') {
                if (addLast) {
                    list.addLast(str.substring(left, right));
                } else {
                    list.addFirst(str.substring(left, right));
                }

                left = right + 1;
                addLast = false;
            }

            if (str.charAt(right) == ']') {
                if (addLast) {
                    list.addLast(str.substring(left, right));
                } else {
                    list.addFirst(str.substring(left, right));
                }

                left = right + 1;
                addLast = true;
            }
        }

        if (addLast) {
            list.addLast(str.substring(left));
        } else {
            list.addFirst(str.substring(left));
        }

        String answer = "";
        for (String s : list) answer += s;

        return answer;
    }

    static void blockProblem() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Ingrese una cantidad de bloques: ");
        int n = Integer.parseInt(in.nextLine());
        Stack[] piles = new Stack[n];

        for (int i = 0; i < piles.length; i++) {
            piles[i] = new Stack();
            piles[i].add(i);
        }

        System.out.println("Pasos a ejecutar:\n");
        String input;
        while (true) {
            input = in.nextLine().trim();
            
            if (input.contains("move ")) {
                String[] text;
                input = input.substring(5);
                
                if (input.contains(" onto ")) {
                    text = input.split(" onto ");
                    int a = Integer.parseInt(text[0]), b = Integer.parseInt(text[1]);
                    
                    Buscador findA = new Buscador(a, piles);
                    Buscador findB = new Buscador(b, piles);
                    
                    //Condición sobre llamadas ilegales
                    if (a == b || findA.pos == findB.pos) continue;
                    
                    //Buscar a
                    for (int j = 0; j < findA.stack_amount; j++) {
                        int c = (int) piles[findA.pos].pop();
                        if (c != a) piles[c].add(c);
                    }
                    
                    //Poner sobre b
                    for (int j = 1; j < findB.stack_amount; j++) {
                        int c = (int) piles[findB.pos].pop();
                        if (c != b) piles[c].add(c);
                    }
                    piles[findB.pos].add(a);
                    
                } else if (input.contains(" over ")) {
                    text = input.split(" over ");
                    int a = Integer.parseInt(text[0]), b = Integer.parseInt(text[1]);
                    
                    Buscador findA = new Buscador(a, piles);
                    Buscador findB = new Buscador(b, piles);
                    
                    //Condición sobre llamadas ilegales
                    
                    if (a == b || findA.pos == findB.pos) continue;
                    
                    //Buscar a
                    for (int j = 0; j < findA.stack_amount; j++) {
                        int c = (int) piles[findA.pos].pop();
                        if (c != a) piles[c].add(c);
                    }

                    //Poner en b
                    piles[findB.pos].add(a);
                }

            } else if (input.contains("pile ")) {
                String[] text;
                input = input.substring(5);
                Stack aux = new Stack();
                Stack aux2 = new Stack();

                if (input.contains(" onto ")) {
                    text = input.split(" onto ");
                    int a = Integer.parseInt(text[0]), b = Integer.parseInt(text[1]);
                    
                    Buscador findA = new Buscador(a, piles);
                    Buscador findB = new Buscador(b, piles);
                    
                    //Condición sobre llamadas ilegales
                    if (a == b || findA.pos == findB.pos) continue;
                    
                    //Buscar a
                    //Guardamos los elementos en un stack auxiliar
                    for (int j = 0; j < findA.stack_amount; j++) {
                        aux.push(piles[findA.pos].pop());
                    }

                    //Poner sobre b
                    //Guardamos los elementos encima de b
                    for (int j = 1; j < findB.stack_amount; j++) aux2.push(piles[findB.pos].pop());
                    
                    //Ponemos el stack auxiliar
                    while (aux.size() > 0) piles[findB.pos].push(aux.pop());
                    
                    //Ponemos la pila que estaba encima de b
                    while (aux2.size() > 0) piles[findB.pos].push(aux2.pop());
                    
                }
                else if (input.contains(" over ")) {
                    text = input.split(" over ");
                    int a = Integer.parseInt(text[0]), b = Integer.parseInt(text[1]);
                    
                    Buscador findA = new Buscador(a, piles);
                    Buscador findB = new Buscador(b, piles);
                    
                    //Condición sobre llamadas ilegales
                    if (a == b || findA.pos == findB.pos) continue;
                    
                    //Buscar a
                    //Guardamos los elementos en un stack auxiliar
                    for (int j = 0; j < findA.stack_amount; j++) aux.push(piles[findA.pos].pop());
                    
                    //Poner en b
                    while (aux.size() > 0) piles[findB.pos].push(aux.pop());
                    
                }
            }
            else if (input.contains("quit")) {
                break;
            }
        }
        
        for (int i = 0; i < piles.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < piles[i].size(); j++) {
                System.out.print(piles[i].get(j));
                if (j == piles[i].size() - 1); 
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}

/**
* Clase que se refiere a un buscador de un bloque del problema 2.2,
* el cual tiene un valor para la posición del arreglo donde lo encontró (pos)
* y la cantidad de bloques que hay que quitar para llegar a este (stack_amount)
*/
class Buscador {

    int pos;
    int stack_amount;

    public Buscador(int find, Stack[] piles) {
        for (int i = 0; i < piles.length; i++) {
            this.stack_amount = piles[i].search(find);
            if (this.stack_amount > 0) {
                this.pos = i;
                break;
            }
        }
    }
}
