/**
 * Created by Настенька on 10/13/2015.
 */
public class Determinate {
    private Matrix mainMatrix;
    private double determinate;

    public Determinate(Matrix mainMatrix) {
        this.mainMatrix = mainMatrix;
    }

    public Matrix getMainMatrix() {
        return mainMatrix;
    }

    public double getDeterminate() {
        return determinate;
    }

    private boolean swapRows (int index1, int index2) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        if (index1==index2) return false;
        double temp;
        for (int i=0; i<n+1; i++) {
            temp=matrix[index1][i];
            matrix[index1][i]=matrix[index2][i];
            matrix[index2][i]=temp;
        }
        mainMatrix.setMatrix(matrix);
        return true;
    }

    private boolean swapColumns (int index1, int index2) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        if (index1==index2) return false;
        double temp;
        for (int i=0; i<n; i++) {
            temp=matrix[i][index1];
            matrix[i][index1]=matrix[i][index2];
            matrix[i][index2]=temp;
        }
        int[] arguments = mainMatrix.getArguments();
        int t=arguments[index1];
        arguments[index1]=arguments[index2];
        arguments[index2]=t;
        mainMatrix.setMatrix(matrix);
        mainMatrix.setArguments(arguments);
        return true;
    }

    private Pair findMaxElement (int number) {
        // найти максимальный элемент подмассиве ниже элемента matrix[number][number]
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        Pair index=new Pair(number, number);
        double max=matrix[number][number];
        for (int i=number; i<n; i++) {
            for(int j=number; j<n; j++) {
                if (matrix[i][j]>max) {
                    max=matrix[i][j];
                    index.i=i;
                    index.j=j;
                }
            }
        }
        return index;
    }

    private Pair findMaxElementRow (int number){
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        //найти максимальный элемент по строке
        Pair index = new Pair(number, number);
        double max=matrix[number][number];
        for (int i=number; i<n; i++) {
            if (matrix[i][number]>max) {
                max=matrix[i][number];
                index.i=i;
                index.j=number;
            }
        }
        return index;
    }

    private Pair findMaxElementColumn (int number){
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        //найти максимальный элемент по столбцу
        Pair index = new Pair(number, number);
        double max=matrix[number][number];
        for (int i=number; i<n; i++) {
            if (matrix[number][i]>max) {
                max=matrix[number][i];
                index.i=number;
                index.j=i;
            }
        }
        return index;
    }

    private double divisionRow (int index) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        // деление строки номер index на элемент matrix[index][index]
        double number=matrix[index][index];
        for (int i=0; i<n+1; i++) {
            matrix[index][i]=matrix[index][i]/number;
        }
        mainMatrix.setMatrix(matrix);
        return number;
    }

    private void subtractRow (int index1, int index2, double number) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        // вычесть из строки номер index1 строку номер index2, умноженную на number
        for (int i=0; i<n+1; i++) {
            matrix[index1][i]=matrix[index1][i]-matrix[index2][i]*number;
        }
        mainMatrix.setMatrix(matrix);
    }

    public void algorithm () {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();

        Pair index=new Pair(-1, -1);
        determinate=1;
        for (int i=0; i<n; i++) {
            index=findMaxElement(i);
            System.out.println("Max element: "+matrix[i][index.j]);
            if(swapColumns(i, index.j)){
                determinate*=-1;
            }
            if( swapRows(i, index.i)){
                determinate*=-1;
            }
            determinate*=divisionRow(i);
            for(int j=i+1; j<n; j++) {
                subtractRow(j, i, matrix[j][i]);
            }
            mainMatrix.printMatrix();
        }
        for(int i=n-2; i>=0; i--) {
            for(int j=1; j<n-i; j++) {
                subtractRow(i, n-j, matrix[i][n-j]);
            }
        }
        mainMatrix.setMatrix(matrix);
    }
}
