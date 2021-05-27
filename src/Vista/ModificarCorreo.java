package Vista;

import Controlador.Controlador;
import java.awt.Color;
import javax.swing.WindowConstants;

public class ModificarCorreo extends javax.swing.JFrame {

    private Controlador controlador;
    
    public ModificarCorreo() {
        initComponents();
        
        //Propiedades de la ventana
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SGCSR - Modificar correo");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(83, 83, 83));
     
        //Botones
        this.boton_guardarCambios.setFocusPainted(false);
    }

    public void setEntradaContraseña_1(String entradaContraseña_1)
    {
        this.entradaContraseña_1.setText(entradaContraseña_1);
    }

    public void setEntradaContraseña_2(String entradaContraseña_2)
    {
        this.entradaContraseña_2.setText(entradaContraseña_2);
    }

    public void setEntradaCorreo(String entradaCorreo)
    {
        this.entradaCorreo.setText(entradaCorreo);
    }
    
    public void setControlador(Controlador controlador)
    {
        this.controlador = controlador;
    }
    
    public void setEntradaNumeroCorreo(String numeroCorreo)
    {
        this.entradaNumeroCorreo.setText(numeroCorreo);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrinciapl = new javax.swing.JPanel();
        textoCorreo = new javax.swing.JLabel();
        separador1 = new javax.swing.JSeparator();
        entradaCorreo = new javax.swing.JTextField();
        separador2 = new javax.swing.JSeparator();
        textoContraseña = new javax.swing.JLabel();
        separador3 = new javax.swing.JSeparator();
        entradaContraseña_2 = new javax.swing.JPasswordField();
        entradaContraseña_1 = new javax.swing.JPasswordField();
        boton_guardarCambios = new javax.swing.JButton();
        textoNumeroCorreo = new javax.swing.JLabel();
        entradaNumeroCorreo = new javax.swing.JTextField();
        separador4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrinciapl.setBackground(new java.awt.Color(127, 127, 127));

        textoCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoCorreo.setForeground(new java.awt.Color(255, 255, 255));
        textoCorreo.setText("Correo:");

        separador1.setBackground(new java.awt.Color(83, 83, 83));
        separador1.setForeground(new java.awt.Color(83, 83, 83));

        entradaCorreo.setBackground(new java.awt.Color(83, 83, 83));
        entradaCorreo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        entradaCorreo.setForeground(new java.awt.Color(255, 255, 255));
        entradaCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador2.setBackground(new java.awt.Color(83, 83, 83));
        separador2.setForeground(new java.awt.Color(83, 83, 83));

        textoContraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoContraseña.setForeground(new java.awt.Color(255, 255, 255));
        textoContraseña.setText("Contraseña:");

        separador3.setBackground(new java.awt.Color(83, 83, 83));
        separador3.setForeground(new java.awt.Color(83, 83, 83));

        entradaContraseña_2.setBackground(new java.awt.Color(83, 83, 83));
        entradaContraseña_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaContraseña_2.setForeground(new java.awt.Color(255, 255, 255));
        entradaContraseña_2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        entradaContraseña_1.setBackground(new java.awt.Color(83, 83, 83));
        entradaContraseña_1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaContraseña_1.setForeground(new java.awt.Color(255, 255, 255));
        entradaContraseña_1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        boton_guardarCambios.setBackground(new java.awt.Color(83, 83, 83));
        boton_guardarCambios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_guardarCambios.setForeground(new java.awt.Color(255, 255, 255));
        boton_guardarCambios.setText("Guardar cambios");
        boton_guardarCambios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        boton_guardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardarCambiosActionPerformed(evt);
            }
        });

        textoNumeroCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textoNumeroCorreo.setForeground(new java.awt.Color(255, 255, 255));
        textoNumeroCorreo.setText("Número del correo:");

        entradaNumeroCorreo.setBackground(new java.awt.Color(83, 83, 83));
        entradaNumeroCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entradaNumeroCorreo.setForeground(new java.awt.Color(255, 255, 255));
        entradaNumeroCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        separador4.setBackground(new java.awt.Color(83, 83, 83));
        separador4.setForeground(new java.awt.Color(83, 83, 83));

        javax.swing.GroupLayout panelPrinciaplLayout = new javax.swing.GroupLayout(panelPrinciapl);
        panelPrinciapl.setLayout(panelPrinciaplLayout);
        panelPrinciaplLayout.setHorizontalGroup(
            panelPrinciaplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrinciaplLayout.createSequentialGroup()
                .addGroup(panelPrinciaplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrinciaplLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelPrinciaplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(entradaContraseña_2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrinciaplLayout.createSequentialGroup()
                                .addComponent(textoNumeroCorreo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(entradaNumeroCorreo))
                            .addComponent(separador2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separador1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrinciaplLayout.createSequentialGroup()
                                .addComponent(textoCorreo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(entradaCorreo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrinciaplLayout.createSequentialGroup()
                                .addComponent(textoContraseña)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(entradaContraseña_1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(separador3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separador4, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(panelPrinciaplLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(boton_guardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelPrinciaplLayout.setVerticalGroup(
            panelPrinciaplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrinciaplLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrinciaplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCorreo)
                    .addComponent(entradaCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrinciaplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoContraseña)
                    .addComponent(entradaContraseña_1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(entradaContraseña_2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrinciaplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNumeroCorreo)
                    .addComponent(entradaNumeroCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton_guardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrinciapl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrinciapl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_guardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardarCambiosActionPerformed
        if(controlador.modificarCorreo(entradaCorreo.getText(), entradaContraseña_1.getPassword().toString(), entradaContraseña_2.getPassword().toString(), entradaNumeroCorreo.getText()))
        {
            
        }
    }//GEN-LAST:event_boton_guardarCambiosActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ModificarCorreo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_guardarCambios;
    private javax.swing.JPasswordField entradaContraseña_1;
    private javax.swing.JPasswordField entradaContraseña_2;
    private javax.swing.JTextField entradaCorreo;
    private javax.swing.JTextField entradaNumeroCorreo;
    private javax.swing.JPanel panelPrinciapl;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    private javax.swing.JSeparator separador3;
    private javax.swing.JSeparator separador4;
    private javax.swing.JLabel textoContraseña;
    private javax.swing.JLabel textoCorreo;
    private javax.swing.JLabel textoNumeroCorreo;
    // End of variables declaration//GEN-END:variables
}
