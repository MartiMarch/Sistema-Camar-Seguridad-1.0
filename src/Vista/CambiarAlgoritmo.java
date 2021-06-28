package Vista;

import Controlador.Controlador;
import javax.swing.WindowConstants;

public class CambiarAlgoritmo extends javax.swing.JFrame {
    private Controlador controlador;
    
    public CambiarAlgoritmo(){
        initComponents();
        
        //Ventana
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setTitle("SGCSR - Seleccionar algoritmo");
        
        //RadioButtons
        this.radioButton_EF.setFocusPainted(false);
        this.rdioButton_FO.setFocusable(false);
    }

    public void setControlador(Controlador controlador)
    {
        this.controlador = controlador;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotones = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        panelOpcionesAlgoritmo = new javax.swing.JPanel();
        rdioButton_FO = new javax.swing.JRadioButton();
        radioButton_EF = new javax.swing.JRadioButton();
        botonGurdarSeleccion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(83, 83, 83));

        panelOpcionesAlgoritmo.setBackground(new java.awt.Color(127, 127, 127));

        rdioButton_FO.setBackground(new java.awt.Color(127, 127, 127));
        grupoBotones.add(rdioButton_FO);
        rdioButton_FO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdioButton_FO.setForeground(new java.awt.Color(255, 255, 255));
        rdioButton_FO.setText("Algorimto de movimiento bsado en el flujo óptico");

        radioButton_EF.setBackground(new java.awt.Color(127, 127, 127));
        grupoBotones.add(radioButton_EF);
        radioButton_EF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioButton_EF.setForeground(new java.awt.Color(255, 255, 255));
        radioButton_EF.setText("Algoritmo de movimiento basado en la substrcción de fondo");
        radioButton_EF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton_EFActionPerformed(evt);
            }
        });

        botonGurdarSeleccion.setBackground(new java.awt.Color(83, 83, 83));
        botonGurdarSeleccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonGurdarSeleccion.setForeground(new java.awt.Color(255, 255, 255));
        botonGurdarSeleccion.setText("Guardar selección");
        botonGurdarSeleccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        botonGurdarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGurdarSeleccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesAlgoritmoLayout = new javax.swing.GroupLayout(panelOpcionesAlgoritmo);
        panelOpcionesAlgoritmo.setLayout(panelOpcionesAlgoritmoLayout);
        panelOpcionesAlgoritmoLayout.setHorizontalGroup(
            panelOpcionesAlgoritmoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcionesAlgoritmoLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(panelOpcionesAlgoritmoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdioButton_FO)
                    .addComponent(radioButton_EF))
                .addGap(21, 21, 21))
            .addGroup(panelOpcionesAlgoritmoLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(botonGurdarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOpcionesAlgoritmoLayout.setVerticalGroup(
            panelOpcionesAlgoritmoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesAlgoritmoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(rdioButton_FO)
                .addGap(12, 12, 12)
                .addComponent(radioButton_EF)
                .addGap(18, 18, 18)
                .addComponent(botonGurdarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpcionesAlgoritmo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpcionesAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioButton_EFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton_EFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButton_EFActionPerformed

    private void botonGurdarSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGurdarSeleccionActionPerformed
        boolean seleccion = true;
        if(radioButton_EF.isSelected())
        {
           seleccion = true; 
        }
        else if(rdioButton_FO.isSelected())
        {
            seleccion = false;
        }
        controlador.cambiarAlgoritmo(seleccion);
        setVisible(false);
    }//GEN-LAST:event_botonGurdarSeleccionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGurdarSeleccion;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.JPanel panelOpcionesAlgoritmo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JRadioButton radioButton_EF;
    private javax.swing.JRadioButton rdioButton_FO;
    // End of variables declaration//GEN-END:variables
}
