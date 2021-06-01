package Vista;

import ComponentesBasicos.JBButton;
import ComponentesBasicos.JBScrollPane;
import ComponentesBasicos.JBSeparator;
import ComponentesBasicos.JBTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AlarmasRecibidas extends javax.swing.JFrame {
    ArrayList<String> clientes;
    
    public AlarmasRecibidas(ArrayList<String> clientes) {
        initComponents();
        this.clientes = clientes;
        
        //Propiedades de la ventana
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SGCSR - Alarmas recibidas");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(83, 83, 83));
        
        //Mostrar los noimbres de todos los clientes
        actualizarPanelNombresClientes();
    }

    public void actualizarPanelNombresClientes()
    {
        
        ArrayList<JPanel> filas = rellenarFilasNombresClientes();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(127,127,127));
        panel.setLayout(new BoxLayout( panel, BoxLayout.Y_AXIS));
        for(int i = 0; i < filas.size(); ++i)
        {
            filas.get(i).setMaximumSize(filas.get(i).getPreferredSize());
            filas.get(i).setAlignmentX(LEFT_ALIGNMENT);
            panel.add(filas.get(i));
        }
        panel.revalidate();
        panel.repaint();
        JBScrollPane scrollPane = new JBScrollPane(panel);
        listaClientes.removeAll();
        listaClientes.revalidate();
        listaClientes.repaint();
        listaClientes.setLayout(new BorderLayout());
        listaClientes.add(scrollPane);
    }
    
    public ArrayList<JPanel> rellenarFilasNombresClientes()
    {
        ArrayList<JPanel> filas = new ArrayList();
        JPanel panelFila;

        for(int i = 0; i < clientes.size(); ++i)
        {
            panelFila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
            panelFila.setBackground(new Color(127, 127, 127));
            
            //Nombre del cliente
            panelFila.add(Box.createRigidArea(new Dimension(10, 0)));
            JBTextField fechaAlarma = new JBTextField(clientes.get(i));
            panelFila.add(fechaAlarma);
            
            filas.add(panelFila);
            
            //Separador
            panelFila = new JPanel();
            JBSeparator s = new JBSeparator();
            panelFila.setBackground(new Color(127,127,127));
            panelFila.add(s);
            
            filas.add(panelFila);
        }
        
        return filas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        listaClientes = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(127, 127, 127));

        listaClientes.setBackground(new java.awt.Color(127, 127, 127));
        listaClientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(83, 83, 83), 2, true));
        listaClientes.setMinimumSize(new java.awt.Dimension(0, 315));

        javax.swing.GroupLayout listaClientesLayout = new javax.swing.GroupLayout(listaClientes);
        listaClientes.setLayout(listaClientesLayout);
        listaClientesLayout.setHorizontalGroup(
            listaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        listaClientesLayout.setVerticalGroup(
            listaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel listaClientes;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
