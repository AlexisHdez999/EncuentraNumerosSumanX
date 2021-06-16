
import java.util.*;


/**
 * Clase que guarda una lista de numeros y la suma de estos números
 */
public class Numeros {
    //lista de numeros
    private LinkedList<Integer> lista;
    //la suma de la lista anterior
    private int suma;

    /**
     * contructor
     * @param lista La lista de nuemros inicial
     * @param suma, la suma de la lista inicial
     */
    public Numeros(LinkedList<Integer> lista, int suma) {
        this.lista = lista;
        this.suma = suma;
    }

    public void setSuma(int s){
        suma = suma +s ;
    }

    /**
     * Regresa la lista de numeros
     * @return la lista de numeros
     */
    public LinkedList<Integer> getLista() {
        return lista;
    }

    /**
     * Agrega un número a la lista y suma el número
     * @param num El número a agregar
     */
    public void agregarNumero(int num) {
        lista.add(num);
        suma = suma + num;
    }

    /**
     * Devuelve la representación en cadena de la lista de numeros
     * @return una cadena con los numeros de la lista
     */
    public String toString() {
        String s = "";
        for (int n : lista) {
            s += n + ", " ;
        }
        String s2 = s.substring(0, s.length() - 2);
        return s2 + "= "+ suma  + "\n";
    }

    /**
     * Compara con otro numero que no tengan los mismos elementos
     * @param no un objeto Numero
     * @return true si contienen los mismos numeros en sus listas internas
     */
    public boolean equals(Object no) {
        Numeros n = (Numeros) no;
        LinkedList<Integer> l = n.getLista();
        if(l.size() != lista.size()){
            return false ;
        }
        for (int i : l) {
            if (lista.contains(i)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve false si hay un elemento en común
     * @param obj un objeto Numero
     * @return true si existe al menos un elemento en común entre las lista que guardan los objetos Numeros
     */
    public boolean elementoComun(Object obj) {
        Numeros n = (Numeros) obj;
        LinkedList<Integer> l = n.getLista();
        for (int i : l) {
            if (lista.contains(i)) {
                return true ;
            }
        }
        return false;
    }

    /**
     * Devuelve la suma de los elementos de la lista
     * @return La suma de los elementos de la lista
     */
    public int getSuma() {
        return suma;
    }

    public int hashCode(){
        int i= 17 ;
        Collections.sort(lista);
        i += Objects.hashCode(lista) ;
        i += (suma * 31 );
        //System.out.println("el hashcode es  " + i );
        return i ;
    }

    public static Numeros crearNuevo(Numeros n1, Numeros n2){
        LinkedList<Integer> a = new LinkedList<>(n1.getLista()) ;
        a.addAll(n2.getLista()) ;
        int sum = n1.getSuma() + n2.getSuma() ;
        return new Numeros(a,sum) ;
    }
}