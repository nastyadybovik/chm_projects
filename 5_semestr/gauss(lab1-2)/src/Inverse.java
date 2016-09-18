import java.io.IOException;

/**
 * Created by Настенька on 10/13/2015.
 */
public class Inverse {
    private Matrix inverseMatrix;

    public Inverse() throws IOException{
        inverseMatrix = new Matrix();
    }

    public double[][] getInverseMatrix() {
        return inverseMatrix.getMatrix();
    }

    public int getN(){
        return inverseMatrix.getN();
    }

    public void algorythm() throws IOException{
        int n = inverseMatrix.getN();
        //задаём единичную матрицу
        double[][] func = new double[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                func[i][j] = (i==j)?1:0;
            }
        }

        for(int i=0; i<n; i++){
            Matrix tmp = new Matrix();
            tmp.setFunction(func[i]);
            tmp.printMatrix();
            Gauss g = new Gauss(tmp);
            g.algorithm();
            double[] answer = tmp.getAnswer();
            inverseMatrix.setColumn(answer, i);
        }
    }
}
