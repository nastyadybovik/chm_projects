import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by Настенька on 10/7/2015.
 */
public class Matrix {
    private int n;
    private double[][] matrix;
    private int[] arguments;
    private double[] answer;

    public Matrix () throws IOException{
        readData();
        arguments=new int [n];
        answer=new double[n];
        for(int i=0; i<n; i++)
            arguments[i]=i;
    }

    public void readData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        n = Integer.parseInt(br.readLine());
        matrix=new double[n][n+1];
        String temp;                    //строка м-цы
        StringTokenizer tok;
        for(int i=0; i<n; i++) {
            temp = br.readLine();
            tok = new StringTokenizer(temp);
            for (int j=0; j<n+1; j++) {
                matrix[i][j]=Double.parseDouble(tok.nextToken());
            }
        }
        br.close();
    }

    public int getN() {
        return n;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public int[] getArguments() {
        return arguments;
    }

    public void setArguments(int[] arguments) {
        this.arguments = arguments;
    }

    public void setAnswer(double[] answer) {
        this.answer = answer;
    }

    public double[] getAnswer() {
        return answer;
    }

    public void setFunction(double[] f){
        for(int i=0; i<n; i++){
            matrix[i][n] = f[i];
        }
    }

    public void setColumn(double[] column, int col){
        for(int i=0; i<n; i++){
            matrix[i][col]= column[i];
        }
    }

    public void printMatrix () {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n+1; j++) {
                System.out.format("%10.5f",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public void printAnswer () {
        for(int i=0; i<n; i++) {
            System.out.print("x[" + (i + 1) + "]=");
            System.out.format("%10.5f", answer[i]);
            System.out.println();
        }
    }

}
