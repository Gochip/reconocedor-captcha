package reconocedor;

import comun.Matriz;
import java.util.ArrayList;

/**
 * Esta clase reconoce una matriz a partir de un conjunto de matrices de
 * entrenamiento.
 */
public class Reconocedor {
    /**
     * Entrena la red a partir de las matrices.
     * @param matrices 
     */
    public void entrenar(ArrayList<Matriz> matrices){
        
    }

    /**
     * Reconoce una matriz a partir de otra matriz.
     * @param matriz
     * @return la matriz reconocida. En caso de no reconocer ninguna retorna
     * null.
     */
    public Matriz reconocer(Matriz matriz){
        return null;
    }
    
    /**
     * Genera una matriz de 1xn (Vector) a partir de una matriz.
     * @param m Recibe una matriz de enteros contenido en el ArrayList
     *          que recibe el método entrenar().
     * @return Retorna una matriz de 1xn.
     */
    public int[][] generarVector(int[][] m) {
        int w = m.length * m[0].length;
        int[][] resp = new int[1][w];
        int i = 0;
        do{
        for (int j = 0; j < m.length; j++) {
            for (int k = 0; k < m[j].length; k++) {
                resp[0][i] = m[j][k];
                i++;
            }
        }
        }while(i<w);
        return resp;
    }
    
    /**
     * Genera la transpuesta a partir de una matriz de 1xn.
     * @param A Recibe la matriz generada por el método generarVector().
     * @return Retorna una matriz de nx1.
     */
    public static int[][] transpuesta(int[][]A){
        int[][]resp = new int[A[0].length][A.length];
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                resp[j][i] = A[i][j];
            }
        }
        
        return resp;
    }
    
    /**
     * Realiza el producto entre dos matrices A,B y le resta 
     * la matriz identidad I.
     * @param A
     * @param B
     * @return Retorna una matriz de nxm.
     */
    public static int[][] producto(int[][] A, int[][] B) {
        int suma = 0;
        int result[][] = new int[A.length][B[0].length];
        int I[][] = new int[A.length][B[0].length];

        
        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j < I[i].length; j++) {
                if(i==j){
                    I[i][j] = 1;
                }
            }
        }
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += (A[i][k]*B[k][j])-I[i][j];
                }
            }
        }
        return result;
    }
}
