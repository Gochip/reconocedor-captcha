package igu;

import controlador.Controlador;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Es la ventana principal.
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private final Controlador controlador;
    private File archivoSeleccionado;

    public VentanaPrincipal(Controlador controlador) {
        initComponents();
        this.controlador = controlador;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSeleccionadas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImagenes = new javax.swing.JTable();
        btnImagenReconocer = new javax.swing.JButton();
        lblImagenReconocida = new javax.swing.JLabel();
        lblImagenSeleccionada = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSeleccionadas.setText("Seleccionar imagenes ");
        btnSeleccionadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionadasActionPerformed(evt);
            }
        });

        tblImagenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblImagenes);

        btnImagenReconocer.setText("Imagen a reconocer");
        btnImagenReconocer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenReconocerActionPerformed(evt);
            }
        });

        lblImagenReconocida.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblImagenSeleccionada.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Reconocer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSeleccionadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImagenReconocer)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(lblImagenSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(lblImagenReconocida, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionadas)
                    .addComponent(btnImagenReconocer))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagenSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImagenReconocida, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionadasActionPerformed
        JFileChooser elegirArchivo = new JFileChooser();
        //Activa para que puedan seleccionarse muchos archivos
        elegirArchivo.setMultiSelectionEnabled(true);
        //Filtro
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG , BPM", "jpg", "png", "bmp");
        elegirArchivo.setFileFilter(filtroImagen);

        if (elegirArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            //Archivos seleccionados 
            File[] archivosSeleccionados = elegirArchivo.getSelectedFiles();
            try {
                controlador.entrenar(archivosSeleccionados);
                String[][] datos = new String[archivosSeleccionados.length][archivosSeleccionados.length];
                for (int i = 0; i < archivosSeleccionados.length; i++) {
                    datos[i][0] = archivosSeleccionados[i].getName();
                }
                tblImagenes.setModel(new DefaultTableModel(datos, new String[]{"Imagen"}));
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSeleccionadasActionPerformed

    private void btnImagenReconocerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenReconocerActionPerformed
        JFileChooser elegirArchivo = new JFileChooser();
        //Filtro
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG , BPM", "jpg", "png", "bmp");
        elegirArchivo.setFileFilter(filtroImagen);

        if (elegirArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            //Archivo seleccionado
            archivoSeleccionado = elegirArchivo.getSelectedFile();
            BufferedImage im;
            try {
                im = ImageIO.read(archivoSeleccionado);
                lblImagenSeleccionada.setIcon(new ImageIcon(im, "Imagen a reconocer"));
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "No se pudo leer la imagen");
            }
        }
    }//GEN-LAST:event_btnImagenReconocerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (archivoSeleccionado != null) {
                BufferedImage imagen = controlador.reconocer(archivoSeleccionado);
                if (imagen != null) {
                    lblImagenReconocida.setIcon(new ImageIcon(imagen, "Imagen reconocida"));
                } else {
                    JOptionPane.showMessageDialog(this, "No se reconoció la imagen");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImagenReconocer;
    private javax.swing.JButton btnSeleccionadas;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagenReconocida;
    private javax.swing.JLabel lblImagenSeleccionada;
    private javax.swing.JTable tblImagenes;
    // End of variables declaration//GEN-END:variables
}
