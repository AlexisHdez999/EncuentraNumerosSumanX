import java.util.*;

/**
 * clase para encontrar grupos de números de tamaño n, que sumen X partiendo de un conjunto de números
 */
public class Buscador {

    /**
     * método que encuentra los grupos de numeros que suman X cantidad
     * @param arreglo el conjunto de números disponibles
     * @param no el tamaño de los grupos que se desean formar
     * @param total La suma de los numeros de cada grupo
     */
    public void encuentraSuma(int[] arreglo, int no, int total) {
        ArrayList<Numeros> list = encuentra(arreglo, no, total);
        ArrayList<Numeros> nuevo = filtraIncompleto(list);
        String s = "Suma_" + no + "numeros" + "suman" + total  ;
        Escritor.escritura(nuevo, s);
    }


    //método para filtrar los grupos repetidos
    private ArrayList<Numeros> filtraIncompleto(ArrayList<Numeros> array) {
        Hashtable<Numeros, Integer> nuevoHash = new Hashtable<>();
        for (Numeros n : array) {
            nuevoHash.put(n, n.getSuma());
        }
        ArrayList<Numeros> array2 = new ArrayList<>(nuevoHash.keySet());
        return array2;
    }


    //método que crea una lista de objetos Numeros partiendo de un arreglo inicial
    private ArrayList<Numeros> creaArrayInicial(int[] arreglo) {
        ArrayList<Numeros> list = new ArrayList<>(arreglo.length);
        for (int i = 0; i < arreglo.length; i++) {
            LinkedList<Integer> a = new LinkedList<>();
            a.add(arreglo[i]);
            Numeros n = new Numeros(a, arreglo[i]);
            list.add(n);
        }
        return list;
    }


    //Encuentra los Numeros que sumen cierta cantidad
    //Recibe un arreglo inicial, el tamaño de los grupos, la suma
    private ArrayList<Numeros> encuentra(int[] arreglo, int contador, int total) {

        //ordena el arreglo inicial
        Arrays.sort(arreglo);
        //crea un ArrayList de Numeros a partir del arreglo inicial
        ArrayList<Numeros> list0 = creaArrayInicial(arreglo);

        //Esta será el arrayList que crecerá
        ArrayList<Numeros> l = list0;

        //Se divide en dos el contador para obtener una lista que tenga grupos de la mitad de tamaño
        //de los que realmente se necesitan
        int contador2 = contador / 2;

        //contador de vueltas
        int con = 1;

        while (con < contador2) {
            //ordena el arrayList resultante de la iteración anterior
            if (con != 1) {
                ordenaArray(l);
            }

            if ((con + 1 == contador2) && (contador2 % 2 == 1)) {
                l = combinaArray(list0, l, total);
                break;
            }
            l = combinaArray(l, l, total);

            //en cada vuelta se duplica el tamaño de los Numeros del arrayList
            con *= 2;
        }

        //para grupos parres
        if (contador % 2 == 0) {
            ordenaArray(l);
            l = combinaArrayFinalPar(l,total);
        }
        //para grupos de tamño impar
        if ((contador % 2) == 1) {
            ordenaArray(l);
            l = combinaArrayFinal(list0, l, total);
        }
        return l;
    }


    //Ordena un arraylist dado de acuerdo a la suma de los Numeros que contiene
    private ArrayList<Numeros> ordenaArray(ArrayList<Numeros> l) {
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
        return l;
    }


  //Hace combinaciones de las dos listas dadas sin tomar en cuenta los Numeros que sumen más que el total requerido
    private ArrayList<Numeros> combinaArray(ArrayList<Numeros> a1, ArrayList<Numeros> a2, int total) {
        ArrayList<Numeros> a3 = new ArrayList<>();
        for (int i = 0; i < a1.size(); i++) {
            Numeros nI = a1.get(i);
            for (int j = 0; j < a2.size(); j++) {
                Numeros nJ = a2.get(j);
                if (!nI.elementoComun(nJ)) {
                    int sumaTotal = nI.getSuma() + nJ.getSuma();
                    if (sumaTotal >= total) {
                        break;
                    }
                    Numeros nuevo = Numeros.crearNuevo(nI, nJ);
                    a3.add(nuevo);
                }
            }
        }
        a3 = filtraIncompleto(a3) ;
        return a3;
    }


    //Hace combinaciones de dos listas dadas, para obtener numeros que sumen un total dado
    private ArrayList<Numeros> combinaArrayFinal(ArrayList<Numeros> array, ArrayList<Numeros> array2, int total) {
        ArrayList<Numeros> nuevo = new ArrayList<>();
        for (Numeros n : array) {
            int r = 0;
            int l = array2.size() - 1;
            while (r < l) {
                Numeros nR = array2.get(r);
                Numeros nL = array2.get(l);
                int suma2 = nR.getSuma() + nL.getSuma() + n.getSuma();
                if (suma2 == total) {
                    int r2 = r+1 ;
                    int l2 = l-1 ;
                    do {
                        if (!nR.elementoComun(nL)) {
                            Numeros temp = Numeros.crearNuevo(nR, nL);
                            if (!temp.elementoComun(n)) {
                                Numeros temp2 = Numeros.crearNuevo(temp, n);
                                nuevo.add(temp2);
                            }
                        }

                        if(array2.get(r2).getSuma() == nR.getSuma()){
                            nR = array2.get(r2) ;
                            r2++ ;
                            continue ;
                        }
                        nR = array2.get(r) ;

                        if(array2.get(l2).getSuma() == nL.getSuma()){
                            nL = array2.get(l2) ;
                            l2--;
                            continue;
                        }
                        break ;
                    } while(suma2 == total) ;
                    r++;
                    l--;

                } else {
                    if (suma2 < total) {
                        r++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return nuevo;
    }


    //Hace combinaciones de una lista consigo misma, para obtener números que sumen un total dado
    private ArrayList<Numeros> combinaArrayFinalPar(ArrayList<Numeros> array2, int total) {
        ArrayList<Numeros> nuevo = new ArrayList<>();
        int r = 0;
        int l = array2.size() - 1;
        while (r < l) {
            Numeros nR = array2.get(r);
            Numeros nL = array2.get(l);
            int suma2 = nR.getSuma() + nL.getSuma();

            if (suma2 == total) {
                //para asegurarse de que se encuentren todas las combinaciones posibles
                int r2 = r+1 ;
                int l2 = l-1 ;
                do {
                    if (!nR.elementoComun(nL)) {
                        Numeros temp = Numeros.crearNuevo(nR, nL);
                        nuevo.add(temp);
                    }

                    if(array2.get(r2).getSuma() == nR.getSuma()){
                        nR = array2.get(r2) ;
                        r2++ ;
                        continue ;
                    }
                    nR = array2.get(r) ;

                    if(array2.get(l2).getSuma() == nL.getSuma()){
                        nL = array2.get(l2) ;
                        l2--;
                        continue;
                    }
                    break ;
                } while(suma2 == total) ;
                r++;
                l--;
            } else {
                if (suma2 < total) {
                    r++;
                } else {
                    l--;
                }
            }
        }
        return nuevo;
    }

}