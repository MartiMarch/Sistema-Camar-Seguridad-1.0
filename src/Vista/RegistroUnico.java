package Vista;

import Controlador.Controlador;
import java.awt.Color;
import javax.swing.JOptionPane;

public class RegistroUnico extends javax.swing.JFrame {
    private Color Gray = new Color(127, 127, 127);
    private Color myDark_Gray = new Color(83, 83, 83);
    private String contraseña;
    private String correo;
    private String correoContraseña;
    private Controlador controlador;
    private boolean registrado;
    
    public RegistroUnico(Controlador controlador) {
        registrado = false;
        initComponents();
        this.controlador = controlador;
        
        //Ventana
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SGCSR - Registrar al Administrador");
        getContentPane().setBackground(Gray);
     
        //Botones
        this.boton_clausula.setContentAreaFilled(false);
        this.boton_registro.setFocusPainted(false);
        
        //CheckBox
        this.checkbox_clausula.setFocusPainted(false);
    }

    public boolean isRegistrado()
    {
        return registrado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_advertenciaç = new javax.swing.JPanel();
        texto_advertencia_1 = new javax.swing.JLabel();
        texto_advertencia_2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        texto_contraseña = new javax.swing.JLabel();
        contraseña_1 = new javax.swing.JPasswordField();
        contraseña_2 = new javax.swing.JPasswordField();
        texto_correo = new javax.swing.JLabel();
        entrada_correo = new javax.swing.JTextField();
        checkbox_clausula = new javax.swing.JCheckBox();
        boton_registro = new javax.swing.JButton();
        boton_clausula = new javax.swing.JButton();
        texto_contraseñaCorreo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        entrada_contraseñaCorreo = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(127, 127, 127));

        panel_advertenciaç.setBackground(new java.awt.Color(127, 127, 127));
        panel_advertenciaç.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51), 3));

        texto_advertencia_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_advertencia_1.setForeground(new java.awt.Color(255, 255, 255));
        texto_advertencia_1.setText("¡IMPORTANTE! Para poder utilizar la aplicación es necesario");

        texto_advertencia_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_advertencia_2.setForeground(new java.awt.Color(255, 255, 255));
        texto_advertencia_2.setText("que registres a un administrador.");

        javax.swing.GroupLayout panel_advertenciaçLayout = new javax.swing.GroupLayout(panel_advertenciaç);
        panel_advertenciaç.setLayout(panel_advertenciaçLayout);
        panel_advertenciaçLayout.setHorizontalGroup(
            panel_advertenciaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_advertenciaçLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_advertenciaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_advertencia_1)
                    .addComponent(texto_advertencia_2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_advertenciaçLayout.setVerticalGroup(
            panel_advertenciaçLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_advertenciaçLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(texto_advertencia_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(texto_advertencia_2)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(127, 127, 127));

        texto_contraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_contraseña.setForeground(new java.awt.Color(255, 255, 255));
        texto_contraseña.setText("Contraseña:");

        contraseña_1.setBackground(new java.awt.Color(83, 83, 83));
        contraseña_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        contraseña_1.setForeground(new java.awt.Color(255, 255, 255));
        contraseña_1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        contraseña_2.setBackground(new java.awt.Color(83, 83, 83));
        contraseña_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        contraseña_2.setForeground(new java.awt.Color(255, 255, 255));
        contraseña_2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        texto_correo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_correo.setForeground(new java.awt.Color(255, 255, 255));
        texto_correo.setText("Correo:");

        entrada_correo.setBackground(new java.awt.Color(83, 83, 83));
        entrada_correo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        entrada_correo.setForeground(new java.awt.Color(255, 255, 255));
        entrada_correo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        checkbox_clausula.setBackground(new java.awt.Color(127, 127, 127));
        checkbox_clausula.setForeground(new java.awt.Color(255, 255, 255));
        checkbox_clausula.setText("Acepto los requerimientos legales.");
        checkbox_clausula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_clausulaActionPerformed(evt);
            }
        });

        boton_registro.setBackground(new java.awt.Color(83, 83, 83));
        boton_registro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_registro.setForeground(new java.awt.Color(255, 255, 255));
        boton_registro.setText("Registrarse");
        boton_registro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        boton_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_registroActionPerformed(evt);
            }
        });

        boton_clausula.setForeground(new java.awt.Color(255, 255, 51));
        boton_clausula.setText("Leer claúsula");
        boton_clausula.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        boton_clausula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_clausulaActionPerformed(evt);
            }
        });

        texto_contraseñaCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_contraseñaCorreo.setForeground(new java.awt.Color(255, 255, 255));
        texto_contraseñaCorreo.setText("Contrseña del correo:");

        jSeparator1.setBackground(new java.awt.Color(83, 83, 83));
        jSeparator1.setForeground(new java.awt.Color(83, 83, 83));

        jSeparator2.setBackground(new java.awt.Color(83, 83, 83));
        jSeparator2.setForeground(new java.awt.Color(83, 83, 83));

        jSeparator3.setBackground(new java.awt.Color(83, 83, 83));
        jSeparator3.setForeground(new java.awt.Color(83, 83, 83));

        jSeparator4.setBackground(new java.awt.Color(83, 83, 83));
        jSeparator4.setForeground(new java.awt.Color(83, 83, 83));

        entrada_contraseñaCorreo.setBackground(new java.awt.Color(83, 83, 83));
        entrada_contraseñaCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entrada_contraseñaCorreo.setForeground(new java.awt.Color(255, 255, 255));
        entrada_contraseñaCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(texto_correo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(entrada_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contraseña_2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(texto_contraseña)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(contraseña_1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(texto_contraseñaCorreo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(entrada_contraseñaCorreo))
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boton_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkbox_clausula))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton_clausula)))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_contraseña)
                    .addComponent(contraseña_1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contraseña_2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entrada_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_correo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entrada_contraseñaCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_contraseñaCorreo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_clausula)
                    .addComponent(boton_clausula))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_advertenciaç, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_advertenciaç, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_clausulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_clausulaActionPerformed
        Clausula c = new Clausula();
        c.setVisible(true);
    }//GEN-LAST:event_boton_clausulaActionPerformed

    private void boton_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_registroActionPerformed
        String password_1 = String.valueOf(contraseña_1.getPassword());
        String password_2 = String.valueOf(contraseña_2.getPassword());
        if(password_1.equals(password_2))
        {
            if(checkbox_clausula.isSelected())
            {
                contraseña = String.valueOf(contraseña_1.getPassword());
                correo = entrada_correo.getText();
                correoContraseña = String.valueOf(entrada_contraseñaCorreo.getPassword());
                controlador.registrarAdministrador(contraseña, correo, correoContraseña);
                registrado = true;
                this.setVisible(false);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Para poder registrate has de aceptrar la cláusula.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Las contraseñas introducidas no coinciden.");
        }
    }//GEN-LAST:event_boton_registroActionPerformed

    private void checkbox_clausulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_clausulaActionPerformed

    }//GEN-LAST:event_checkbox_clausulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_clausula;
    private javax.swing.JButton boton_registro;
    private javax.swing.JCheckBox checkbox_clausula;
    private javax.swing.JPasswordField contraseña_1;
    private javax.swing.JPasswordField contraseña_2;
    private javax.swing.JPasswordField entrada_contraseñaCorreo;
    private javax.swing.JTextField entrada_correo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel panel_advertenciaç;
    private javax.swing.JLabel texto_advertencia_1;
    private javax.swing.JLabel texto_advertencia_2;
    private javax.swing.JLabel texto_contraseña;
    private javax.swing.JLabel texto_contraseñaCorreo;
    private javax.swing.JLabel texto_correo;
    // End of variables declaration//GEN-END:variables

}