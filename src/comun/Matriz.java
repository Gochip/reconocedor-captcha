package comun;

import java.util.Arrays;

/**
 *
 */
public class Matriz {
    private int[][] matriz;

    public int[][] getMatriz() {
        return matriz;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Arrays.deepHashCode(this.matriz);
        return hash;
    }
}
