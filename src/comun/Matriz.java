package comun;

import java.util.Arrays;

/**
 *
 */
public class Matriz {
    private int[][] matriz;

    public Matriz (){
        
    }
    
    public Matriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int[][] getMatriz() {
        return matriz;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Arrays.deepHashCode(this.matriz);
        return hash;
    }
    
    /**
     * Genera una matriz de 1xn (Vector) a partir de una matriz.
     * @param m .
     * @return Retorna una matriz de 1xn.
     */
    public int[][] generarVector(int[][] m) {
        int w = m.length * m[0].length;
        int[][] resp = new int[1][w];
        int i = 0;
        do {
            for (int j = 0; j < m.length; j++) {
                for (int k = 0; k < m[j].length; k++) {
                    resp[0][i] = m[j][k];
                    i++;
                }
            }
        } while (i < w);
        return resp;
    }
    
    /**
     * Genera la transpuesta de una matriz.
     * @param A.
     * @return la transpuesta.
     */
    public int[][] transpuesta(int[][]A){
        int[][]resp = new int[A[0].length][A.length];
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                resp[j][i] = A[i][j];
            }
        }
        return resp;
    }
    
    /**
     * Realiza el producto entre dos matrices A,B.
     * @param A
     * @param B
     * @return Retorna una matriz de nxm.
     */
    public int[][] producto(int[][] A, int[][] B) {
        int result[][] = new int[A.length][B[0].length];
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k]*B[k][j];
                }
            }
        }
        return result;
    }
    
    /**
     * Le resta la matriz identidad a una matriz dada.
     * @param A
     * @return Retorna con la diagonal principal en cero.
     */
    public int[][]restarIdentidad(int[][]A){
        int I[][] = new int[A.length][A[0].length];
        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j < I[i].length; j++) {
                if(i==j){
                    I[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = A[i][j] - I[i][j];
            }
        }
        return A;
    }
    
    /**
     * Función de activación.
     * @param A
     * @return Retorna una matriz de 1 y -1.
     */
    public int[][] activar(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] < 0) {
                    A[i][j] = -1;
                }
                if (A[i][j] >= 0) {
                    A[i][j] = 1;
                }
            }
        }
        return A;
    }
}
