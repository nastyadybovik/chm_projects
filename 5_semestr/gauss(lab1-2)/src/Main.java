// Метод Гаусса с выбором главного элемента по всей матрице

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        //Гаусс
        Matrix B = new Matrix();
        B.printMatrix();
        Gauss g = new Gauss(B);
        g.algorithm();
        B.printMatrix();
        B.printAnswer();

        //Определитель
        Matrix A = new Matrix();
        Determinate d = new Determinate(A);
        d.algorithm();
        System.out.println("Determinate: "+d.getDeterminate());

        //Обратная матрица
        Inverse inv = new Inverse();
        inv.algorythm();
        System.out.println("Inverse matrix:");
        double[][] inverse = inv.getInverseMatrix();
        int n = inv.getN();
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.format("%10.5f",inverse[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }
}