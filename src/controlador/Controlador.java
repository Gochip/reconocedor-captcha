package controlador;

import comun.Matriz;
import conversor.Conversor;
import igu.VentanaPrincipal;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import reconocedor.Reconocedor;

/**
 * Controlador principal de la aplicación.
 */
public class Controlador {

    /**
     * El reconocedor de imágenes.
     */
    private Reconocedor reconocedor;
    /**
     * Memoria interna para guardar el mapeo entre el bufferedImage y la matriz.
     */
    private ArrayList<BufferedImage> memoriaInternaBufferedImage;
    private ArrayList<Matriz> memoriaInternaMatrices;

    /**
     * Inicia la aplicación.
     */
    public void inicarAplicacion() {
        VentanaPrincipal vp = new VentanaPrincipal(this);
        vp.setVisible(true);
    }

    /**
     * Entrena la red neuronal de Hopfield a partir de las imágenes recibidas.
     *
     * @param rutaImagenes
     * @throws java.io.IOException cuando ocurre un error al leer las imágenes.
     */
    public void entrenar(File[] rutaImagenes) throws IOException {
        memoriaInternaBufferedImage = new ArrayList<>();
        memoriaInternaMatrices = new ArrayList<>();
        ArrayList<Matriz> matrices = new ArrayList<>();
        Conversor conversor = new Conversor();
        for (File archivoImagen : rutaImagenes) {
            BufferedImage bufer = ImageIO.read(archivoImagen);
            Matriz matriz = conversor.convertirAMatriz(bufer);
            memoriaInternaBufferedImage.add(bufer);
            memoriaInternaMatrices.add(matriz);
            matrices.add(matriz);
        }
        reconocedor = new Reconocedor();
        reconocedor.entrenar(matrices);
    }

    /**
     * A partir de una imagen confusa retorna la imagen más similar.
     *
     * @param rutaImagen
     * @return la imagen reconocida, null en caso que no exista.
     * @throws java.io.IOException cuando ocurre un error al leer las imágenes.
     */
    public BufferedImage reconocer(File archivoImagen) throws IOException {
        if (reconocedor == null) {
            throw new RuntimeException("Debe entrenar la red primero");
        }
        Conversor conversor = new Conversor();
        BufferedImage bufer = ImageIO.read(archivoImagen);
        Matriz matriz = conversor.convertirAMatriz(bufer);
        Matriz matrizReconocida = reconocedor.reconocer(matriz);
        if (matrizReconocida != null) {
            return obtenerBufferedImage(matrizReconocida);
        } else {
            return null;
        }
    }

    /**
     * A partir de una matriz retorna una BufferedImage.
     *
     * @param matriz la matriz a buscar.
     * @return el BufferedImage.
     */
    private BufferedImage obtenerBufferedImage(Matriz matriz) {
        int hashBuscado = matriz.getMatriz().hashCode();
        for (int k = 0; k < memoriaInternaMatrices.size(); k++) {
            int hash = memoriaInternaMatrices.get(k).getMatriz().hashCode();
            if (hash == hashBuscado) {
                return memoriaInternaBufferedImage.get(k);
            }
        }
        return null;
    }
}
