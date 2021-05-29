package Vista;

import Controlador.Controlador;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;

public class AñadirUsuario extends javax.swing.JFrame {
    private Controlador controlador;
    
    public AñadirUsuario() {
        initComponents();
        
        //Propiedades de la ventana
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SGCSR - Añadir usuario");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(83, 83, 83));
        
        //Botones
        this.botonAñadirUsuario.setFocusable(false);
    }

    public void setEntradaCorreo(String entradaCorreo)
    {
        this.entradaCorreo.setText(entradaCorreo);
    }

    public void setEntradaNombre(String entradaNombre)
    {
        this.entradaNombre.setText(entradaNombre);
    }

    public void setEtradaContraseña1(String entradaContraseña1)
    {
        this.entradaContraseña1.setText(entradaContraseña1);
    }

    public void setEtradaContraseña2(String entradaContraseña2)
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
        textoNombre = new javax.swing.JLabel();
        entradaNombre = new javax.swing.JTextField();
        separador1 = new javax.swing.JSeparator();
        separador2 = new javax.swing.JSeparator();
        textoCorreo = new javax.swing.JLabel();
        entradaCorreo = new javax.swing.JTextField();
        separador3 = new javax.swing.JSeparator();
        textoContraseña = new javax.swing.JLabel();
        entradaContraseña1 = new javax.swing.JPasswordField();
        entradaContraseña2 = new javax.swing.JPasswordField();
        separador4 = new javax.swing.JSeparator();
        botonAñadirUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(127, 127, 127));

        panelFondo.setBackground(new java.awt.Color(83, 83, 83));

        panelPrincipal.setBackground(new java.awt.Color(127, 127, 127));
        panelPrincipal.setForeground(new java.awt.Color(255, 255, 255));

        textoNombre.setBackground(new java.awt.Color(255, 255, 255));
        textoNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoNombre.setForeground(new java.awt.Color(255, 255, 255));
        textoNombre.setText("Nombre:");

        entradaNombre.setBackground(new java.awt.Color(83, 83, 83));
        entradaNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaNombre.setForeground(new java.awt.Color(255, 255, 255));
        entradaNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador1.setBackground(new java.awt.Color(83, 83, 83));
        separador1.setForeground(new java.awt.Color(83, 83, 83));

        separador2.setBackground(new java.awt.Color(83, 83, 83));
        separador2.setForeground(new java.awt.Color(83, 83, 83));

        textoCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoCorreo.setForeground(new java.awt.Color(255, 255, 255));
        textoCorreo.setText("Correo:");

        entradaCorreo.setBackground(new java.awt.Color(83, 83, 83));
        entradaCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaCorreo.setForeground(new java.awt.Color(255, 255, 255));
        entradaCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador3.setBackground(new java.awt.Color(83, 83, 83));
        separador3.setForeground(new java.awt.Color(83, 83, 83));

        textoContraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoContraseña.setForeground(new java.awt.Color(255, 255, 255));
        textoContraseña.setText("Contrseña:");

        entradaContraseña1.setBackground(new java.awt.Color(83, 83, 83));
        entradaContraseña1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaContraseña1.setForeground(new java.awt.Color(255, 255, 255));
        entradaContraseña1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        entradaContraseña2.setBackground(new java.awt.Color(83, 83, 83));
        entradaContraseña2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaContraseña2.setForeground(new java.awt.Color(255, 255, 255));
        entradaContraseña2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador4.setBackground(new java.awt.Color(83, 83, 83));
        separador4.setForeground(new java.awt.Color(83, 83, 83));

        botonAñadirUsuario.setBackground(new java.awt.Color(83, 83, 83));
        botonAñadirUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonAñadirUsuario.setForeground(new java.awt.Color(255, 255, 255));
        botonAñadirUsuario.setText("Añadir usuario");
        botonAñadirUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        botonAñadirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrincipalLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelPrincipalLayout.createSequentialGroup()
                                    .addComponent(textoCorreo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(entradaCorreo))
                                .addComponent(separador1)
                                .addGroup(panelPrincipalLayout.createSequentialGroup()
                                    .addComponent(textoNombre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(entradaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(separador2)))
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(separador3)
                                .addGroup(panelPrincipalLayout.createSequentialGroup()
                                    .addComponent(textoContraseña)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(entradaContraseña2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(entradaContraseña1)))
                                .addComponent(separador4))))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(botonAñadirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombre)
                    .addComponent(entradaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCorreo)
                    .addComponent(entradaCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoContraseña)
                    .addComponent(entradaContraseña1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entradaContraseña2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAñadirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelFondoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelFondoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAñadirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAñadirUsuarioActionPerformed
        try {
            if(controlador.añadirUsuario(entradaNombre.getText(), entradaCorreo.getText(), new String(entradaContraseña1.getPassword()), new String(entradaContraseña2.getPassword())))
            {
                this.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AñadirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AñadirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonAñadirUsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAñadirUsuario;
    private javax.swing.JPasswordField entradaContraseña1;
    private javax.swing.JPasswordField entradaContraseña2;
    private javax.swing.JTextField entradaCorreo;
    private javax.swing.JTextField entradaNombre;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    private javax.swing.JSeparator separador3;
    private javax.swing.JSeparator separador4;
    private javax.swing.JLabel textoContraseña;
    private javax.swing.JLabel textoCorreo;
    private javax.swing.JLabel textoNombre;
    // End of variables declaration//GEN-END:variables
}
