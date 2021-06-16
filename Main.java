
/**
 * Clase principal
 */
public class Main {

    public static void main(String[] str) {
        //ejemplos
        Buscador buscador = new Buscador() ;
        buscador.encuentraSuma(new int[]{4, 5, 9, 13, 1,6, 10,2,3}, 2, 7);
        buscador.encuentraSuma(new int[]{23, 24, 7, 2, 12, 11, 28, 31, 3, 19, 22, 30, 9, 17, 13}, 7, 83);
        buscador.encuentraSuma(new int[]{184, 180, 163, 156, 213, 205, 175, 208, 11, 36, 7, 10, 40, 30, 15, 23, 1, 39, 35, 25, 5, 32, 28, 24, 38, 21, 31, 18, 33, 6, 13, 14, 9, 20, 17, 16, 4, 26, 27, 37, 3, 12, 34, 22, 29, 2, 19, 8}, 8, 430);
    }

}