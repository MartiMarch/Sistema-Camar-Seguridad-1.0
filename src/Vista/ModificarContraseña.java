package Vista;

import Controlador.Controlador;
import java.awt.Color;
import javax.swing.WindowConstants;

public class ModificarContraseña extends javax.swing.JFrame {
    private Controlador controlador;
    
    public ModificarContraseña() {
        initComponents();
        
        //Propiedades de la ventana
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SGCSR - Modificar contraseña");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(83, 83, 83));
        
        //Botones
        this.botonGuardarCambios.setFocusPainted(false);
    }

    public void setEntradNumeroCorreo(String entradNumeroCorreo)
    {
        this.entradNumeroCorreo.setText(entradNumeroCorreo);
    }

    public void setEntradaContraseña1(String entradaContraseña1)
    {
        this.entradaContraseña1.setText(entradaContraseña1);
    }

    public void setEntradaContraseña2(String entradaContraseña2)
    {
        this.entradaContraseña2.setText(entradaContraseña2);
    }
    
    public void setControlador(Controlador controlador)
    {
        this.controlador = controlador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        panelPrincipal = new javax.swing.JPanel();
        textoNuevaContraseña = new javax.swing.JLabel();
        entradaContraseña1 = new javax.swing.JPasswordField();
        separador1 = new javax.swing.JSeparator();
        entradaContraseña2 = new javax.swing.JPasswordField();
        separador2 = new javax.swing.JSeparator();
        textoNumeroCorreo = new javax.swing.JLabel();
        entradNumeroCorreo = new javax.swing.JTextField();
        separador3 = new javax.swing.JSeparator();
        botonGuardarCambios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        panelFondo.setBackground(new java.awt.Color(83, 83, 83));

        panelPrincipal.setBackground(new java.awt.Color(127, 127, 127));
        panelPrincipal.setForeground(new java.awt.Color(127, 127, 127));

        textoNuevaContraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoNuevaContraseña.setForeground(new java.awt.Color(255, 255, 255));
        textoNuevaContraseña.setText("Nueva contraseña:");

        entradaContraseña1.setBackground(new java.awt.Color(83, 83, 83));
        entradaContraseña1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaContraseña1.setForeground(new java.awt.Color(255, 255, 255));
        entradaContraseña1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador1.setBackground(new java.awt.Color(83, 83, 83));
        separador1.setForeground(new java.awt.Color(83, 83, 83));

        entradaContraseña2.setBackground(new java.awt.Color(83, 83, 83));
        entradaContraseña2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaContraseña2.setForeground(new java.awt.Color(255, 255, 255));
        entradaContraseña2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador2.setBackground(new java.awt.Color(83, 83, 83));
        separador2.setForeground(new java.awt.Color(83, 83, 83));

        textoNumeroCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoNumeroCorreo.setForeground(new java.awt.Color(255, 255, 255));
        textoNumeroCorreo.setText("Número del correo:");

        entradNumeroCorreo.setBackground(new java.awt.Color(83, 83, 83));
        entradNumeroCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradNumeroCorreo.setForeground(new java.awt.Color(255, 255, 255));
        entradNumeroCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador3.setBackground(new java.awt.Color(83, 83, 83));
        separador3.setForeground(new java.awt.Color(83, 83, 83));

        botonGuardarCambios.setBackground(new java.awt.Color(83, 83, 83));
        botonGuardarCambios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonGuardarCambios.setForeground(new java.awt.Color(255, 255, 255));
        botonGuardarCambios.setText("Guardar cambios");
        botonGuardarCambios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        botonGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarCambiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separador2)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(textoNumeroCorreo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entradNumeroCorreo))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(separador1)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(textoNuevaContraseña)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(entradaContraseña2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(entradaContraseña1))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(separador3))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNuevaContraseña)
                    .addComponent(entradaContraseña1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(entradaContraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNumeroCorreo)
                    .addComponent(entradNumeroCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarCambiosActionPerformed
        if(controlador.modificarContraseña(new String(entradaContraseña1.getPassword()), new String(entradaContraseña2.getPassword()), entradNumeroCorreo.getText()))
        {
            this.setVisible(false);
        }
    }//GEN-LAST:event_botonGuardarCambiosActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardarCambios;
    private javax.swing.JTextField entradNumeroCorreo;
    private javax.swing.JPasswordField entradaContraseña1;
    private javax.swing.JPasswordField entradaContraseña2;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    private javax.swing.JSeparator separador3;
    private javax.swing.JLabel textoNuevaContraseña;
    private javax.swing.JLabel textoNumeroCorreo;
    // End of variables declaration//GEN-END:variables
}
