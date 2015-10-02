package conversor;

import comun.Matriz;
import java.awt.image.BufferedImage;

/**
 * Convierte un BufferedImage a una matriz. Asumente que la imagen está en
 * blanco y negro.
 */
public class Conversor {

    public static final int NEGRO = 1;
    public static final int BLANCO = -1;

    public Matriz convertirAMatriz(BufferedImage imagen) {
        int altura = imagen.getHeight();
        int ancho = imagen.getWidth();
        int[][] matriz = new int[altura][ancho];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (imagen.getRGB(j, i) == -1) {
                    matriz[i][j] = BLANCO;
                } else {
                    matriz[i][j] = NEGRO;
                }
            }
        }
        Matriz m = new Matriz(matriz);
        return m;
    }
}
