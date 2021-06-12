import java.util.* ;
import java.io.*;


/*clase para escribir archivos de texto
 *
 */

public class Escritor{

    
     /** Método para escribir un archivo
     *@param lista - lista de numeros a escribir
     *@param String - nombre del archivo que se desea escribir
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
		escritor3.write(itera.next() + "") ;		
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
