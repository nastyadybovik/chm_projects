/**
 * Created by ��������� on 10/13/2015.
 */
public class Gauss {
    private Matrix mainMatrix;

    public Gauss(Matrix matrix) {
        mainMatrix = matrix;
    }

    public Matrix getMatrix() {
        return mainMatrix;
    }

    private void swapRows (int index1, int index2) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        if (index1==index2) return;
        double temp;
        for (int i=0; i<n+1; i++) {
            temp=matrix[index1][i];
            matrix[index1][i]=matrix[index2][i];
            matrix[index2][i]=temp;
        }
        mainMatrix.setMatrix(matrix);
    }

    private void swapColumns (int index1, int index2) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        if (index1==index2) return;
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
    }

    private Pair findMaxElement (int number) {
        // ����� ������������ ������� ���������� ���� �������� matrix[number][number]
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
        //����� ������������ ������� �� ������
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
        //����� ������������ ������� �� �������
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

    private void divisionRow (int index) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        // ������� ������ ����� index �� ������� matrix[index][index]
        double number=matrix[index][index];
        for (int i=0; i<n+1; i++) {
            matrix[index][i]=matrix[index][i]/number;
        }
        mainMatrix.setMatrix(matrix);
    }

    private void subtractRow (int index1, int index2, double number) {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        // ������� �� ������ ����� index1 ������ ����� index2, ���������� �� number
        for (int i=0; i<n+1; i++) {
            matrix[index1][i]=matrix[index1][i]-matrix[index2][i]*number;
        }
        mainMatrix.setMatrix(matrix);
    }

    public void algorithm () {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();

        Pair index=new Pair(-1, -1);
        for (int i=0; i<n; i++) { // �������� �������� � ����� ������ ����
            index=findMaxElementColumn(i);
            swapColumns(i, index.j);
            swapRows(i, index.i);
            divisionRow(i);
            for(int j=i+1; j<n; j++) {
                subtractRow(j, i, matrix[j][i]);
            }
        }
        for(int i=n-2; i>=0; i--) {
            for(int j=1; j<n-i; j++) {
                subtractRow(i, n-j, matrix[i][n-j]);
            }
        }
        mainMatrix.setMatrix(matrix);
        getAnswer();
    }

    private void getAnswer () {
        int n = mainMatrix.getN();
        double[][] matrix = mainMatrix.getMatrix();
        int[] arguments = mainMatrix.getArguments();
        double[] answer = mainMatrix.getAnswer();
        for(int i=0; i<n; i++) {
            answer[arguments[i]] = matrix[i][n];
        }
        mainMatrix.setAnswer(answer);
    }
}
