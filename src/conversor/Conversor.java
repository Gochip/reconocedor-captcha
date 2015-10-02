package conversor;

import comun.Matriz;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Convierte un BufferedImage a una matriz. Asumente que la imagen está en
 * blanco y negro.
 */
public class Conversor {

    public static final int NEGRO = 1;
    public static final int BLANCO = -1;
    
    
    public static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    } 

    public Matriz convertirAMatriz(BufferedImage imagenR) {
        BufferedImage imagen = resize(imagenR, 64,64);
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
