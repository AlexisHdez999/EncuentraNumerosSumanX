
import java.util.*;

public class Main {

    public static void main(String[] str) {

        encuentraSuma(new int[]{4, 5, 9, 13, 1, 10}, 3, 27);
        encuentraSuma(new int[]{23, 24, 7, 2, 12, 11, 28, 31, 3, 19, 22, 30, 9, 17, 13}, 4, 83);
        encuentraSuma(new int[]{184, 180, 163, 156, 213, 205, 175, 208, 11, 36, 7, 10, 40, 30, 15, 23, 1, 39, 35, 25, 5, 32, 28, 24, 38, 21, 31, 18, 33, 6, 13, 14, 9, 20, 17, 16, 4, 26, 27, 37, 3, 12, 34, 22, 29, 2, 19, 8}, 8, 430);

    }

    public static void encuentraSuma(int[] arreglo, int no, int total) {
        ArrayList<Numeros> list = encuentra(arreglo, no, total);
        ArrayList<Numeros> nuevo = filtra(list, total);
        String s = "Suma_" + no + "numeros" + "suman" + total;
        System.out.println("tamaño de la lista después de filtar " +
                nuevo.size() + "  total " + total);
        Escritor.escritura(nuevo, s);
    }

    public static ArrayList<Numeros> filtra(ArrayList<Numeros> array, int nTarget) {
        Hashtable<Numeros, Integer> nuevoArray = new Hashtable<>();
        for (Numeros n : array) {
            if (n.getSuma() == nTarget) {
                if(!nuevoArray.containsKey(n)) {
                    nuevoArray.put(n, n.getSuma());
                }
            }
        }
        ArrayList<Numeros> array2 = new ArrayList<>(nuevoArray.keySet()) ;
        return array2;
    }

    public static ArrayList<Numeros> filtraIncompleto(ArrayList<Numeros> array, int nTarget) {
        ArrayList<Numeros> nuevoArray = new ArrayList<>();
        for (Numeros n : array) {
            int suma = n.getSuma() ;
            if (suma == nTarget) {
                nuevoArray.add(n);
            }
        }
        return nuevoArray;
    }

    public static ArrayList<Numeros> creaArrayInicial(int[] arreglo){
        ArrayList<Numeros> list = new ArrayList<>(arreglo.length);
        for (int i = 0; i < arreglo.length; i++) {
            LinkedList<Integer> a = new LinkedList<>();
            a.add(arreglo[i]);
            Numeros n = new Numeros(a, arreglo[i]);
            list.add(n);
        }
        return list ;
    }

    /**
     * Encuentra los Numeros que sumen el total dado
     * @param arreglo
     * @param contador
     * @param total
     * @return
     */
    public static ArrayList<Numeros> encuentra(int[] arreglo, int contador, int total) {

        //ordena el arreglo inicial
        Arrays.sort(arreglo);
        //crea un ArrayList de Numeros a partir del arreglo inicial
        ArrayList<Numeros> list0 = creaArrayInicial(arreglo);

        //bandera que indica si es la última iteración
        boolean fin = false ;
        //Se combina el arrayList inicial consigo mismo.
        //Esta será el arrayList combinado inicial
        ArrayList<Numeros> list = new ArrayList<>();
        //Esta será el arrayList que crecerá
        ArrayList<Numeros> l = list0 ;

        //Se divide en dos el contador de iteraciones totales porque en cada vuelta el arrayList resultante se
        // combinará con un el arrayList combinado inicial <list>
        int contador2 = contador/2 ;
        //bandera que indica si es la última iteración

        //El contador de las iteraciones comienza en 1 porque ya se hizo la primera combianción del arreglo inical
        int con = 1 ;

        while (con < contador2) {
            //ordena el arrayList resultante de la iteración anterior
            if(con != 1) {
                ordenaArray(l);
            }

            if((con+1 == contador2) && (contador2%2 ==1)){
                l = combinaArray(list0,l, total, fin);
                break ;
            }
            l = combinaArray(l,l, total,fin);
            System.out.println("imprimiendo l " + l );
            //señala el final si el contador es par

            //combina el arrayList inicial combinado con el arrayList que está creciendo
            con *= 2;
        }
        if(contador%2 ==0) {
            //Si el contador es impar entonces se combina el arrayList resultante anterior con el array Incial
            l = buscaNumerosHash(l, l, total);
            System.out.println("Tamaño de la lista antes de filtrar " + l.size() + " total " + total);
        }
        if((contador%2) == 1){
            list = combinaArray(list0,l, total,fin);
            l = buscaNumerosHash(l,list,total) ;
        }
        return l;
    }

    /**
     * Ordena el arrayList dado
     * @param l el arrayList a ordenar
     * @return el arrayList ordenado
     */
    public static ArrayList<Numeros> ordenaArray(ArrayList<Numeros> l){
        Collections.sort(l, (a, b) -> {
            int sumaA = a.getSuma();
            int sumaB = b.getSuma();
            if (sumaA > sumaB) {
                return 1;
            } else {
                if (sumaA == sumaB) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return l ;
    }

    /**
     * Combina dos arrayList
     * @param a1 el primer array List
     * @param a2 el segundo arrayList
     * @param total la suma total requerida de los numeros
     * @param fin señala si es la última combinación
     * @return regresa un arraylist que combina los arrayList recibidos
     */
    public static ArrayList<Numeros> combinaArray(ArrayList<Numeros> a1, ArrayList<Numeros> a2,  int total, boolean fin){
        ArrayList<Numeros> a3 = new ArrayList<>() ;
        for(int i = 0 ; i < a1.size() ; i++){
            Numeros nI = a1.get(i);
            for(int j=0 ; j <a2.size(); j++){
                Numeros nJ = a2.get(j) ;
                if(!nI.elementoComun(nJ)){
                    int sumaTotal = nI.getSuma() + nJ.getSuma();
                    if(fin == false){
                        if( sumaTotal >= total) {
                            break;
                        }
                    } else {
                        if (sumaTotal > total) {
                            break;
                        }
                        if(sumaTotal < total){
                            continue ;
                        }
                    }
                    LinkedList<Integer> lI = nI.getLista();
                    LinkedList<Integer> lJ = nJ.getLista();
                    LinkedList<Integer> nuevaL = new LinkedList<>(lI) ;
                    nuevaL.addAll(lJ) ;
                    Numeros nuevo = new Numeros(nuevaL, sumaTotal) ;
                    a3.add(nuevo) ;
                }
            }
        }
        return a3 ;
    }

    public static ArrayList<Numeros> buscaNumerosHash(ArrayList<Numeros> array, ArrayList<Numeros> arrayCreciente, int suma){
        ArrayList<Numeros> nuevo = new ArrayList<>() ;
        Hashtable<Integer, Numeros> ht= new Hashtable<>() ;
        for(Numeros n : arrayCreciente){
            ht.put(n.getSuma(),n) ;
        }
        for(int i = 0 ; i < array.size() ;i++){
            Numeros nArray = array.get(i) ;
            int restoSuma = suma - nArray.getSuma() ;

            if(ht.containsKey(restoSuma)){
                Numeros nRecuperado = ht.get(restoSuma) ;

                if(!nArray.elementoComun(nRecuperado)){
                    nuevo.add(Numeros.crearNuevo(nRecuperado, nArray)) ;
                }
            }
        }
        return nuevo ;
    }

    public static ArrayList<Numeros> combinaArray(ArrayList<Numeros> array, int total, boolean fin){
        ArrayList<Numeros> nuevo = new ArrayList<>() ;
        int r = 0 ;
        int l = array.size()-1 ;
        while(r < l){
            Numeros nR = array.get(r) ;
            Numeros nL = array.get(l) ;
            int suma2 = nR.getSuma() + nL.getSuma() ;

            if(fin == true){
                if(suma2 == total) {
                    r++;
                    l--;
                    if(!nR.elementoComun(nL)) {
                        nuevo.add(Numeros.crearNuevo(nR, nL));
                    }
                } else{
                    if(suma2 < total){
                        r++ ;
                    } else{
                        l-- ;
                    }
                }

            } else {
                if(suma2 < total){
                    nuevo.add(Numeros.crearNuevo(nR, nL)) ;
                    r++ ;
                } else {
                    if(suma2 > total){
                        l-- ;
                    } else {
                        l-- ;
                    }
                }
            }
        }
        return nuevo ;
    }
}