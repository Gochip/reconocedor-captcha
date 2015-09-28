package controlador;

import comun.Matriz;
import conversor.Conversor;
import igu.VentanaPrincipal;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private HashMap<Matriz, BufferedImage> memoriaInterna;

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
    public void entrenar(List<String> rutaImagenes) throws IOException {
        memoriaInterna = new HashMap<>();
        ArrayList<Matriz> matrices = new ArrayList<>();
        Conversor conversor = new Conversor();
        for (String rutaImagen : rutaImagenes) {
            File archivoImagen = new File(rutaImagen);
            BufferedImage bufer = ImageIO.read(archivoImagen);
            Matriz matriz = conversor.convertirAMatriz(bufer);
            memoriaInterna.put(matriz, bufer);
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
    public BufferedImage reconocer(String rutaImagen) throws IOException {
        if (reconocedor != null) {
            throw new RuntimeException("Debe entrenar la red primero");
        }
        Conversor conversor = new Conversor();
        File archivoImagen = new File(rutaImagen);
        BufferedImage bufer = ImageIO.read(archivoImagen);
        Matriz matriz = conversor.convertirAMatriz(bufer);
        Matriz matrizReconocida = reconocedor.reconocer(matriz);
        if (matrizReconocida != null) {
            return obtenerBufferedImage(matriz);
        } else {
            return null;
        }
    }

    /**
     * A partir de una matriz retorna una BufferedImage.
     * @param matriz la matriz a buscar.
     * @return el BufferedImage.
     */
    private BufferedImage obtenerBufferedImage(Matriz matriz) {
        return memoriaInterna.get(matriz);
    }
}
