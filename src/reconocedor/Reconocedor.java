package reconocedor;

import comun.Matriz;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Esta clase reconoce una matriz a partir de un conjunto de matrices de
 * entrenamiento.
 */
public class Reconocedor {
    private int[][]pesos;
    private ArrayList<Integer>hash;
    
    /**
     * Entrena la red a partir de las matrices.
     * @param matrices
     */
    public void entrenar(ArrayList<Matriz> matrices){
        int h = matrices.get(0).getMatriz().length;
        int w = matrices.get(0).getMatriz()[0].length;
        int[][]m,mt;
        this.pesos = new int[h][w];
        
        for (Matriz matriz : matrices) {
            m = matriz.getMatriz();
            m = matriz.generarVector(m);
            mt = matriz.transpuesta(m);
            m = matriz.producto(mt,m);
            m = matriz.restarIdentidad(m);
            this.hash.add(Arrays.deepHashCode(matriz.getMatriz()));
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    this.pesos[i][j] += m[i][j];
                }
            }
            
        }
    }

    /**
     * Reconoce una matriz a partir de otra matriz.
     * @param matriz
     * @return la matriz reconocida. En caso de no reconocer ninguna retorna
     * null.
     */
    public Matriz reconocer(Matriz matriz) {
        int[][] m = matriz.getMatriz();
        m = matriz.generarVector(m);
        int n = 0;
        while (Arrays.deepHashCode(m) != this.hash.get(n)) {
            m = matriz.producto(m, this.pesos);
            m = matriz.activar(m);
            for (int hashC : this.hash) {
                if (Arrays.deepHashCode(m) == this.hash.get(n)) {
                    return matriz;
                    }
            }
            n++;
        }
        return null;
    }
}
