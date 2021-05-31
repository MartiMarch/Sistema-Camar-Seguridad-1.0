package Vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicSliderUI;

public class ReproducirVideo extends javax.swing.JFrame {

    private final JFXPanel jfxPanel = new JFXPanel();
    private MediaPlayer video;
    private Media media;
    private MediaView mediaView;
    private double tamaño_slide = 0;
    private boolean sonido = true;
    private File file;
    private String ruta;
    
    public ReproducirVideo(String ruta) {
        Platform.setImplicitExit(false);
        initComponents();
        this.ruta = ruta;
        
        //Ventana
        crearEscena(this);
        setTitle("SGCSR - Reproduciendo video");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
       
        //Paneles
        this.panel_central.setLayout(new BorderLayout());        
        this.panel_central.add(jfxPanel, BorderLayout.CENTER);
        
        //Botones
        this.boton_pausar.setFocusPainted(false);
        this.boton_reanudar.setFocusPainted(false);
        this.boton_pausar.setContentAreaFilled(false);
        this.boton_reanudar.setContentAreaFilled(false);
        this.boton_silenciar.setFocusPainted(false);
        this.boton_silenciar.setContentAreaFilled(false);
        
        //Slide
        this.slide.setOpaque(false);
        
        //Listeners
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                video.dispose();
                dispose();
            }
        });
    }
    
    public void crearEscena(JFrame ventana)
    {   
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                file = new File(ruta);
                media = new Media(file.toURI().toString());
                video = new MediaPlayer(media);
                mediaView = new MediaView();
                esperarEscena(ventana);
                jfxPanel.setScene(new Scene(new Group(new MediaView(video))));
                video.setVolume(1);
                video.setCycleCount(MediaPlayer.INDEFINITE);
                video.play();
            }
        });
    }
   
    public void esperarEscena(JFrame ventana)
    {
        video.setOnReady(new Runnable() {
            @Override
            public void run() {
                String duracion = Double.toString(media.getDuration().toSeconds());
                ventana.setSize(media.getWidth()+10, media.getHeight()+10);
                tamaño_slide = Double.parseDouble(duracion);
                slide.setMinimum(0);
                slide.setMaximum((int) tamaño_slide);
                video.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                    slide.setValue((int) newValue.toSeconds());
                });
            }
        });  
    }
    
    @Override
    public synchronized void addWindowStateListener(WindowStateListener l) {
        super.addWindowStateListener(l); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void addWindowFocusListener(WindowFocusListener l) {
        super.addWindowFocusListener(l); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void addWindowListener(WindowListener l) {
        super.addWindowListener(l); //To change body of generated methods, choose Tools | Templates.
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_central = new javax.swing.JPanel();
        boton_reanudar = new javax.swing.JToggleButton();
        boton_pausar = new javax.swing.JToggleButton();
        slide = new javax.swing.JSlider();
        boton_silenciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_central.setBackground(new java.awt.Color(127, 127, 127));

        boton_reanudar.setBackground(new java.awt.Color(83, 83, 83));
        boton_reanudar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_reanudar.setForeground(new java.awt.Color(255, 255, 255));
        boton_reanudar.setText("Reanudar");
        boton_reanudar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 2, true));
        boton_reanudar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reanudarActionPerformed(evt);
            }
        });

        boton_pausar.setBackground(new java.awt.Color(83, 83, 83));
        boton_pausar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_pausar.setForeground(new java.awt.Color(255, 255, 255));
        boton_pausar.setText("Pausar");
        boton_pausar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 2, true));
        boton_pausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_pausarActionPerformed(evt);
            }
        });

        slide.setBackground(new java.awt.Color(127, 127, 127));
        slide.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        slide.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        slide.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slideStateChanged(evt);
            }
        });
        slide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slideMouseClicked(evt);
            }
        });

        boton_silenciar.setBackground(new java.awt.Color(127, 127, 127));
        boton_silenciar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_silenciar.setForeground(new java.awt.Color(255, 255, 255));
        boton_silenciar.setText("Silenciar");
        boton_silenciar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 2, true));
        boton_silenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_silenciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_centralLayout = new javax.swing.GroupLayout(panel_central);
        panel_central.setLayout(panel_centralLayout);
        panel_centralLayout.setHorizontalGroup(
            panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_centralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boton_pausar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_reanudar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_silenciar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );
        panel_centralLayout.setVerticalGroup(
            panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_centralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_silenciar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boton_pausar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_reanudar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(514, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_central, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_central, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_reanudarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reanudarActionPerformed
        video.play();
    }//GEN-LAST:event_boton_reanudarActionPerformed

    private void boton_pausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_pausarActionPerformed
        video.pause();
    }//GEN-LAST:event_boton_pausarActionPerformed

    private void slideStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slideStateChanged
        
    }//GEN-LAST:event_slideStateChanged

    private void slideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slideMouseClicked
        BasicSliderUI ui = (BasicSliderUI)slide.getUI();
        int value = ui.valueForXPosition(evt.getX());
        video.seek(Duration.seconds(value));
        slide.setValue(value);
    }//GEN-LAST:event_slideMouseClicked

    private void boton_silenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_silenciarActionPerformed
        if(sonido == true)
        {
            video.setVolume(0);
            sonido = false;
        }
        else
        {
            video.setVolume(1);
            sonido = true;
        }
    }//GEN-LAST:event_boton_silenciarActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton boton_pausar;
    private javax.swing.JToggleButton boton_reanudar;
    private javax.swing.JButton boton_silenciar;
    private javax.swing.JPanel panel_central;
    private javax.swing.JSlider slide;
    // End of variables declaration//GEN-END:variables
}
