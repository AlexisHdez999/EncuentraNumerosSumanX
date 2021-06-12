import java.util.* ;
import java.io.BufferedReader ;
import java.io.BufferedWriter ;
import java.io.File ;
import java.io.FileWriter ;
import java.io.PrintWriter ;
import java.io.FileNotFoundException ;
import java.io.FileReader ;
import java.io.IOException ;

/*clase para escribir archivos de texto
 *
 */

public class Escritor{

    
     /** Método para escribir un archivo. Si no se recibe un nombre de archivo, imprime la salida en un archivo llamado salida.txt
     *@param lista - Lista<String> lista ordenada que se desea escribir en un archivo
     *@param String - arreglo con el nombre del archivo que se desea escribir
     */    
    public static void escritura(ArrayList<Numeros> lista, String nombre){
	File archivo = null ;
        FileWriter escritor = null ;
	BufferedWriter escritor2 = null ;
        PrintWriter escritor3 = null ;
        try{
	    archivo = new File(nombre) ;
            escritor = new FileWriter(archivo) ;
	    	escritor2 = new BufferedWriter(escritor) ;
            escritor3 = new PrintWriter(escritor2) ;

	    Iterator<Numeros> itera = lista.iterator() ;
            while (itera.hasNext()){
		escritor3.write(itera.next() + "\n") ;		
	    }

        } catch (Exception e) {
            System.out.println("Error en la escritura del archivo");
        } finally {
	    try {
		if (escritor3 != null){
		    escritor2.close() ;
		    escritor3.close() ;
		}
	    } catch (Exception e2) {
		System.out.println("Error al cierre del archivo") ;
	    }
        }
    }
    
}
