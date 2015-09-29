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
        int[][]m,pesos;
        for (Matriz matriz : matrices) {
            m = matriz.getMatriz();
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    
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
    public Matriz reconocer(Matriz matriz){
        return null;
    }
    
    public static int[][] generarVector(int[][] m) {
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
}
