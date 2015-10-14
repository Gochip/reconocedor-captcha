package reconocedor;

import comun.Matriz;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Esta clase reconoce una matriz a partir de un conjunto de matrices de
 * entrenamiento.
 */
public class Reconocedor {

    private double[][] pesos;
    /*
     Es un arreglo donde se mantiene un hash de cada matriz.
     */
    private ArrayList<Integer> hash;
    private ArrayList<Matriz> matrices;

    public Reconocedor() {
        hash = new ArrayList<>();
        matrices = new ArrayList<>();
    }

    /**
     * Entrena la red a partir de las matrices.
     *
     * @param matrices
     */
    public void entrenar(ArrayList<Matriz> matrices) {
        int h = matrices.get(0).getMatriz().length;
        int w = matrices.get(0).getMatriz()[0].length;
        double[][] m, mt;
        this.pesos = new double[h * h][w * w];

        for (Matriz matriz : matrices) {
            m = matriz.getMatriz();
            m = matriz.generarVector(m);
            this.hash.add(Arrays.deepHashCode(m));
            this.matrices.add(matriz);
            mt = matriz.transpuesta(m);
            m = matriz.producto(mt, m);
            m = matriz.restarIdentidad(m);
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    this.pesos[i][j] += m[i][j];
                }
            }

        }
    }

    /**
     * Reconoce una matriz a partir de otra matriz.
     *
     * @param matriz
     * @return la matriz reconocida. En caso de no reconocer ninguna retorna
     * null.
     */
    public Matriz reconocer(Matriz matriz) {
        int cantidad = 1000;
        double[][] m = matriz.getMatriz();
        m = matriz.generarVector(m);
        for (int i = 0; i < cantidad; i++) {
            int hashMatrizAReconocer = Arrays.deepHashCode(m);
            for (int j = 0; j < hash.size(); j++){
                int h = this.hash.get(j);
                Matriz ma = this.matrices.get(j);
                if (hashMatrizAReconocer == h) {
                    return ma;
                }
            }
            m = matriz.producto(m, this.pesos);
            m = matriz.activar(m);
            
        }
        return null;
    }
}
