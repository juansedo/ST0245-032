package laboratorio4;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author juansedo, LizOriana1409
 */
public class Laboratorio4 {
    public static void main(String[] args) {
        System.out.println("Inicio:");
        
        /*Poner aquí el directorio de preferencia. El programa leerá las carpetas
        * y las acomodará en una Hash Table*/
        Directory fs = new Directory("C:/Users/juans/Documents/_Documents/PDFs", "JuanSe");
        
        /*Imprime todo el directorio en formato de árbol*/
        System.out.println(fs.toString());
        
        System.out.println("\n\n");
        
        /*Búsqueda de carpetas*/
        System.out.println(fs.search("PDFs/Otros"));
        System.out.println(fs.search("PDFs/ABC"));
        System.out.println(fs.search("Hola mundo"));
        System.out.println(fs.search("PDFs/Programación/Curso de C.pdf"));
    }
}

class File {
    File parent;
    String name;
    String user;
    double weight;
    boolean isBin;
    
    public File(File parent, String name, String user, double weight, boolean isBin) {
        this.parent = parent;
        this.name = name;
        this.user = user;
        this.weight = weight/1024;
        this.isBin = isBin;
    }
    
    public String toStringWeight() {
        return (weight < 1024)? String.format("%.02f", weight) + " KB":
               (weight < 1048576)? String.format("%.02f", weight/1024) + " MB": 
               String.format("%.02f", weight/1048576) + " GB";
    }
}

class Files extends LinkedList<File> {
    
    public String toString(int level) {
        return toString(level, 0, Operation.NONE);
    }
    
    public String toString(int level, double bound, Operation op) {
        String str = "";
        for (File f: this) {
            
            if (op.compare(f, bound)) {
                //Niveles
                for (int i = 1; i < level; i++) str += "│   ";
                
                //Líneas
                str += (f.equals(this.getLast())) ? "└── " : "├── ";
                
                //Dato
                str += "[" + f.user + " "
                        + ((f.isBin) ? "Carpeta]" : f.toStringWeight() + "]")
                        + "  " + f.name + "\n";
            }
        }
        return str;
    }
    
    public boolean contains(String str) {
        for (File f: this) {
            if (f.name.equals(str)) return true;
        }
        return false;
    }
}

class Directory extends HashMap<File, Files> {
    
    private String masterpath;
    private File masterfile;
    
    public Directory (String path, String user) {
        int test = -1, finalBin = 0;
        do {
            finalBin = test;
            test = path.indexOf("/", test + 1);
        } while (test >= 0);
        masterpath = path.substring(finalBin + 1);
        masterfile = new File(null, masterpath, user, 0, true);
        openFiles(masterfile, path, user);
    }
    
    private void openFiles(File parent, String path, String user) {
        java.io.File[] archives = new java.io.File(path).listFiles();
        
        for (java.io.File archive : archives) {
            String pathname = archive.getName();
            double pathsize = archive.length();
            
            if (archive.isFile()) {
                File element = new File(parent, pathname, user, pathsize, false);
                if (super.get(parent) == null) {
                    super.put(parent, new Files());
                }
                super.get(parent).add(element);
                
            } else if (archive.isDirectory()) {
                openBin(parent, path + "/" + pathname, user);
            }
        }
    }
    
    private void openBin(File parent, String path, String user) {
        int test = -1, finalBin = 0;
        do {
            finalBin = test;
            test = path.indexOf("/", test + 1);
        } while (test >= 0);
        
        if (!(finalBin < 0)) {
            File bin = new File(parent, path.substring(finalBin + 1), user, 0, true);
            if (super.get(parent) == null) {
                super.put(parent, new Files());
            }
            super.get(parent).add(bin);
            openFiles(bin, path, user);
        }
        else {
            System.out.println("Error");
        }
    }
    
    public Files get (String str) {
        for(File k : this.keySet()) {
            if (k.name.equals(str)) return this.get(k);
        }
        return null;
    }
    
    public String search(String str) {
        return search(str, 0, Operation.NONE);
    }
    
    public String search(String str, double bound, Operation op) {
        int posOfBar = str.indexOf("/");
        if (posOfBar >= 0) {
            String bin = str.substring(0, posOfBar);
            if (!bin.equals(masterpath))
                return str + ":\n" + "La carpeta " + bin + " no es la raíz (No such file or directory).\n";
            
            return str + ":\n" + search(masterfile, str.substring(posOfBar + 1), bound, op);
        }
        else {
            if (!str.equals(masterpath))
                return str + ":\n" + "La carpeta " + str + " no es la raíz (No such file or directory).\n";
            else
                return str + ":\n" + this.get(masterfile).toString(1, bound, op);
            
        }
    }
    
    private String search(File parent, String str, double bound, Operation op) {
        int posOfBar = str.indexOf("/");
        if (posOfBar >= 0) {
            String bin = str.substring(0, posOfBar);
            Files files = this.get(parent);
            if (files == null) return "La carpeta " + bin + " no existe en esa ruta (No such file or directory).\n";
            
            for(File f : files) {
                if (f.name.equals(bin)) {
                    return search(f, str.substring(posOfBar + 1), bound, op);
                }
            }
            return "La carpeta " + bin + " no existe en esa ruta (No such file or directory).\n";
        }
        else {
            Files files = this.get(parent);
            if (files.contains(str)) {
                files = this.get(str);
                if (files != null) return files.toString(1, bound, op);
                else return "(Vacío)";
            }
            else return "La carpeta " + str + " no existe en esa ruta (No such file or directory).\n";
        }
    }
    
    @Override
    public String toString() {
        String str = masterpath + ":\n";
        return str + toStringRecursive(masterfile, 1);
        
    }
    
    private String toStringRecursive(File parent, int level) {
        String str = "";
        Files files = this.get(parent);
        if (files == null) return "";
        for (File f: files) {
            for (int i = 1; i < level; i++) str += "│   ";
            str += (f.equals(files.getLast()))? "└── ": "├── ";
            
            if (f.isBin) {
                str += "[" + f.user + " Carpeta]" +
                       "    " + f.name + "\n" +
                       toStringRecursive(f, level + 1);
            }
            else {
                str += "[" + f.user + " " + f.toStringWeight() + "]" +
                       "    " + f.name + "\n";
            }
        }
        return str;
        
    }
}

enum Operation {
    NONE, MORE_THAN, LESS_THAN;
    
    public boolean compare(File f, double bound) {
        switch(this) {
                case MORE_THAN:
                    return f.weight > bound;
                case LESS_THAN:
                    return f.weight < bound;
            case NONE: default:
                    return true;
            }
    }
}
