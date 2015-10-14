package comun;

import java.util.Arrays;

/**
 *
 */
public class Matriz {

    private double[][] matriz;

    public Matriz() {

    }

    public Matriz(double[][] matriz) {
        this.matriz = matriz;
    }

    public double[][] getMatriz() {
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
     *
     * @param m .
     * @return Retorna una matriz de 1xn.
     */
    public double[][] generarVector(double[][] m) {
        int w = m.length * m[0].length;
        double[][] resp = new double[1][w];
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
     *
     * @param A.
     * @return la transpuesta.
     */
    public double[][] transpuesta(double[][] A) {
        double[][] resp = new double[A[0].length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                resp[j][i] = A[i][j];
            }
        }
        return resp;
    }

    /**
     * Realiza el producto entre dos matrices A,B.
     *
     * @param A
     * @param B
     * @return Retorna una matriz de nxm.
     */
    public double[][] producto(double[][] A, double[][] B) {
        double result[][] = new double[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Le resta la matriz identidad a una matriz dada.
     *
     * @param A
     * @return Retorna con la diagonal principal en cero.
     */
    public double[][] restarIdentidad(double[][] A) {
        double I[][] = new double[A.length][A[0].length];
        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j < I[i].length; j++) {
                if (i == j) {
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
     *
     * @param A
     * @return Retorna una matriz de 1 y -1.
     */
    public double[][] activar(double[][] A) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(matriz[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("HASH: ").append(hashCode());
        return sb.toString();
    }
}
