import java.util.*;

public class Main {

    public static void main(String[] str) {

        encuentraSuma(new int[]{4, 5, 9, 13, 1, 10}, 3, 23);
        encuentraSuma(new int[]{23, 24, 7, 2, 12, 11, 28, 31, 3, 19, 22, 30, 9, 17, 13}, 4, 96);
        encuentraSuma(new int[]{184, 180, 163, 156, 213, 205, 175, 208, 11, 36, 7, 10, 40, 30, 15, 23, 1, 39, 35, 25, 5, 32, 28, 24, 38, 21, 31, 18, 33, 6, 13, 14, 9, 20, 17, 16, 4, 26, 27, 37, 3, 12, 34, 22, 29, 2, 19, 8}, 6, 288);

    }

    public static void encuentraSuma(int[] arreglo, int no, int total) {
        ArrayList<Numeros> list = encuentra(arreglo, no - 1, total);
        ArrayList<Numeros> nuevo = filtra(list, total);
        String s = "Suma_" + no + "numeros" + "suman" + total;
        Escritor.escritura(nuevo, s);
    }

    public static ArrayList<Numeros> filtra(ArrayList<Numeros> array, int nTarget) {
        ArrayList<Numeros> nuevoArray = new ArrayList<>();
        for (Numeros n : array) {
            LinkedList<Integer> list = n.getLista();
            int suma = 0;
            for (Integer i : list) {
                suma = suma + i;
            }

            if (suma == nTarget) {
                if (!nuevoArray.contains(n)) {
                    nuevoArray.add(n);
                }
            }
        }
        return nuevoArray;
    }


    public static ArrayList<Numeros> filtraIncompleto(ArrayList<Numeros> array, int nTarget) {
        ArrayList<Numeros> nuevoArray = new ArrayList<>();
        for (Numeros n : array) {
            LinkedList<Integer> list = n.getLista();
            int suma = 0;
            for (Integer i : list) {
                suma = suma + i;
            }

            if (suma <= nTarget) {
                nuevoArray.add(n);
            }
        }
        return nuevoArray;
    }

    public static ArrayList<Numeros> encuentra(int[] arreglo, int contador, int total) {
        int con = 0;
        ArrayList<Numeros> list = new ArrayList<>(arreglo.length);
        for (int i = 0; i < arreglo.length; i++) {
            LinkedList<Integer> a = new LinkedList<>();
            a.add(arreglo[i]);
            Numeros n = new Numeros(a, arreglo[i]);
            list.add(n);
        }

        Arrays.sort(arreglo);
        while (con < contador) {
            ArrayList<Numeros> l = new ArrayList<>();
            Collections.sort(list, (a, b) -> {
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

            System.out.println("ya se ordenó la lista vuelta " + con);
            for (int i = 0; i < arreglo.length; i++) {
                for (int j = 0; j < list.size(); j++) {
                    Numeros num = list.get(j);
                    LinkedList<Integer> a1 = num.getLista();
                    LinkedList<Integer> a2 = new LinkedList<>(a1);
                    if (!a2.contains(arreglo[i])) {
                        Numeros n = new Numeros(a2, num.getSuma());
                        n.agregarNumero(arreglo[i]);
                        if (n.getSuma() > total) {
                            break;
                        }
                        l.add(n);
                    }
                }
            }

            list = l;
            con++;
            System.out.println("vamos en la repetición  " + con + " con el total " + total);
            System.out.println("el tamaño de la lista es " + list.size());

        }

        return list;
    }


}