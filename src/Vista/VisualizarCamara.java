package Vista;

import java.awt.Dimension;
import org.opencv.core.Core;

public class VisualizarCamara extends javax.swing.JFrame {
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_java451");
        System.loadLibrary("opencv_videoio_ffmpeg451_64");
    }
    private int altura = 0;
    private int anchura = 0;
   
    public VisualizarCamara(String url) throws InterruptedException {
        initComponents();
        
        //Ventana
        this.setTitle("SGCSR - Visualizando la cámara");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Paneles
        SalidaCamara sc = new SalidaCamara(url);
        Thread.sleep(2000);
        altura = sc.getAltura();
        anchura = sc.getAnchura();
        sc.setSize(new Dimension(anchura, altura));
        sc.setVisible(true);
        add(sc);
        this.setSize(new Dimension(anchura+15, altura+30));
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(127, 127, 127));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


