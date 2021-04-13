package Vista;

import Controlador.Controlador;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AñadirCamara extends javax.swing.JFrame {
    private Controlador controlador;
    
    public AñadirCamara(Controlador controlador) {
        initComponents();
        this.controlador = controlador;
        
        //Ventana
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("SGCSR - Añadir cámara");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Botones
        this.boton_añadir_camara.setFocusPainted(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        panel_principal = new javax.swing.JPanel();
        texto_url = new javax.swing.JLabel();
        entradaUrl = new javax.swing.JTextField();
        boton_añadir_camara = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_principal.setBackground(new java.awt.Color(127, 127, 127));
        panel_principal.setForeground(new java.awt.Color(255, 255, 255));
        panel_principal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        texto_url.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_url.setForeground(new java.awt.Color(255, 255, 255));
        texto_url.setText("URL:");

        entradaUrl.setBackground(new java.awt.Color(83, 83, 83));
        entradaUrl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaUrl.setForeground(new java.awt.Color(255, 255, 255));
        entradaUrl.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        boton_añadir_camara.setBackground(new java.awt.Color(83, 83, 83));
        boton_añadir_camara.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_añadir_camara.setForeground(new java.awt.Color(255, 255, 255));
        boton_añadir_camara.setText("Añadir cámara");
        boton_añadir_camara.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        boton_añadir_camara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_añadir_camaraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_principalLayout = new javax.swing.GroupLayout(panel_principal);
        panel_principal.setLayout(panel_principalLayout);
        panel_principalLayout.setHorizontalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principalLayout.createSequentialGroup()
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(texto_url)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entradaUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_principalLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(boton_añadir_camara, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panel_principalLayout.setVerticalGroup(
            panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principalLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_url)
                    .addComponent(entradaUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(boton_añadir_camara, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       
    private void boton_añadir_camaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_añadir_camaraActionPerformed
        try {
            if(controlador.insertarCamara(entradaUrl.getText()))
            {
                this.setVisible(false);
                this.dispose();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AñadirCamara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AñadirCamara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AñadirCamara.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_boton_añadir_camaraActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_añadir_camara;
    private javax.swing.JTextField entradaUrl;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel panel_principal;
    private javax.swing.JLabel texto_url;
    // End of variables declaration//GEN-END:variables
}
