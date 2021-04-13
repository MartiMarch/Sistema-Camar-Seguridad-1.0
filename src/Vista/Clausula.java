package Vista;

import java.awt.Color;

public class Clausula extends javax.swing.JFrame {

    private Color Gray = new Color(127, 127, 127);
    private Color myDark_Gray = new Color(83, 83, 83);
    
    public Clausula() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("SGCSR - Cláusula de compromiso");
        getContentPane().setBackground(Gray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_central = new javax.swing.JPanel();
        texto_clausula_de_compromiso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_central.setBackground(new java.awt.Color(127, 127, 127));
        panel_central.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        texto_clausula_de_compromiso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        texto_clausula_de_compromiso.setForeground(new java.awt.Color(255, 255, 255));
        texto_clausula_de_compromiso.setText("Cláusula de compromiso");

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Al aceptar la cláusula usted se compromente a cumplir los requisitos legales establecidos en la Ley Orgánica del 3/2018. \n\n- Únicamente se capturarán imágenes dentro de un recinto privado.\n- Se informará mediante un cartel que el área privada dispone de un sistema de videovigilancia.\n- El administrador de la aplicación se encuentra inscrito en el Registro General de Protección de Datos de la Agencia Española.\n- El administrador garantiza los derechos de acceso, rectificación, cancelación y oposición.\n- Los datos almacenados se conservarán como mínimo un mes.\n\nAunque la aplicación dispone de ciertas medidas de seguridad para garantizar el correcrto funcionamiento,\nel equipo de desarrollo no se responsabiliza de la mala administración de los usuarios.");
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout panel_centralLayout = new javax.swing.GroupLayout(panel_central);
        panel_central.setLayout(panel_centralLayout);
        panel_centralLayout.setHorizontalGroup(
            panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_centralLayout.createSequentialGroup()
                .addGroup(panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_centralLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(texto_clausula_de_compromiso))
                    .addGroup(panel_centralLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panel_centralLayout.setVerticalGroup(
            panel_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_centralLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(texto_clausula_de_compromiso)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_central, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_central, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panel_central;
    private javax.swing.JLabel texto_clausula_de_compromiso;
    // End of variables declaration//GEN-END:variables
}
