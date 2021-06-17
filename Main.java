
import java.util.*;

public class Main {

    public static void main(String[] str) {

       // encuentraSuma(new int[]{4, 5, 9, 13, 1, 10}, 3, 27);
        encuentraSuma(new int[]{23, 24, 7, 2, 12, 11, 28, 31, 3, 19, 22, 30, 9, 17, 13}, 7, 83);
        encuentraSuma(new int[]{184, 180, 163, 156, 213, 205, 175, 208, 11, 36, 7, 10, 40, 30, 15, 23, 1, 39, 35, 25, 5, 32, 28, 24, 38, 21, 31, 18, 33, 6, 13, 14, 9, 20, 17, 16, 4, 26, 27, 37, 3, 12, 34, 22, 29, 2, 19, 8}, 7, 430);
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

    public static ArrayList<Numeros> filtraIncompleto(ArrayList<Numeros> array) {
        Hashtable<Numeros, Integer> ht = new Hashtable() ;
        for(Numeros n : array){
            ht.put(n, n.getSuma()) ;
        }
        ArrayList<Numeros> nuevoArray = new ArrayList<>(ht.keySet());
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

        //Esta será el arrayList que crecerá
        ArrayList<Numeros> l = list0 ;

        //Se divide en dos el contador de iteraciones totales porque se necesita contruir grupos la mitad del tamaño real esperado
        int contador2 = contador/2 ;

        //El contador de las iteraciones comienza en 1 porque ya se hizo la primera combianción del arreglo inical
        int con = 1 ;

        while (con < contador2) {
            //ordena el arrayList resultante de la iteración anterior
            if(con != 1) {
                l = ordenaArray(l);
            }

            if((con+1 == contador2) && (contador2%2 ==1)){
                l = combinaArray(list0,l, total);
                break ;
            }
            l = combinaArray(l,l, total);

            con *= 2;
        }
        //para grupos pares
        if(contador%2 ==0) {
            l = buscaNumerosHash(l, l, total);
        }
        ArrayList<Numeros> list ;
        //para numeros impares

        if((contador%2) == 1){
            l= ordenaArray(l) ;
            list = combinaArray(list0,l, total);
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
     * @return regresa un arraylist que combina los arrayList recibidos
     */
    public static ArrayList<Numeros> combinaArray(ArrayList<Numeros> a1, ArrayList<Numeros> a2,  int total){
        ArrayList<Numeros> a3 = new ArrayList<>() ;
        for(int i = 0 ; i < a1.size() ; i++){
            Numeros nI = a1.get(i);
            for(int j=0 ; j <a2.size(); j++){
                Numeros nJ = a2.get(j) ;
                int sumaTotal = nI.getSuma() + nJ.getSuma();
                if( sumaTotal < total) {
                    if(!nI.elementoComun(nJ)){
                        Numeros nuevo = Numeros.crearNuevo(nI, nJ);
                        a3.add(nuevo) ;
                    }
                }
            }
        }
        a3 = filtraIncompleto(a3) ;

        return a3 ;
    }

    public static ArrayList<Numeros> buscaNumerosHash(ArrayList<Numeros> array, ArrayList<Numeros> arrayCreciente, int suma){
        ArrayList<Numeros> nuevo = new ArrayList<>() ;
        Hashtable<Integer, ArrayList<Numeros>> ht= new Hashtable<>() ;
        for(Numeros n : arrayCreciente){
            if(!ht.containsKey(n.getSuma())){
                ArrayList<Numeros> a2 = new ArrayList<Numeros>() ;
                a2.add(n) ;
                ht.put(n.getSuma(), a2) ;
            } else {
                ArrayList<Numeros> a = ht.get(n.getSuma()) ;
                a.add(n) ;

            }
        }

        for(int i = 0 ; i < array.size() ;i++){
            Numeros nArray = array.get(i) ;
            int restoSuma = suma - nArray.getSuma() ;

            if(ht.containsKey(restoSuma)){
                ArrayList<Numeros> arrayNum = ht.get(restoSuma) ;
                for(Numeros nRecuperado : arrayNum ){
                    if(!nArray.elementoComun(nRecuperado)){
                        nuevo.add(Numeros.crearNuevo(nRecuperado, nArray)) ;

                    }
                }
            }
        }
        return nuevo ;
    }

}