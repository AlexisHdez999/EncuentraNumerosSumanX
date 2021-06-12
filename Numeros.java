import java.util.*;

public class Numeros {

    private LinkedList<Integer> lista;
    private int suma;

    public Numeros() {
    }

    public Numeros(LinkedList<Integer> lista, int suma) {
        this.lista = lista;
        this.suma = suma;
    }

    public LinkedList<Integer> getLista() {
        return lista;
    }

    public void agregarNumero(int num) {
        lista.add(num);
        suma = suma + num;
    }

    public String toString() {
        String s = "";
        for (int n : lista) {
            s += n + ", ";
        }
        String s2 = s.substring(0, s.length() - 2);
        return s2 + "\n";
    }


    public boolean equals(Object no) {
        Numeros n = (Numeros) no;
        LinkedList<Integer> l = n.getLista();
        for (int i : l) {
            if (lista.contains(i)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public int sumaElementos() {
        int sumaA = 0;
        for (Integer i : lista) {
            sumaA = sumaA + i;
        }
        suma = sumaA;
        return sumaA;
    }

    public int getSuma() {
        return suma;
    }
}