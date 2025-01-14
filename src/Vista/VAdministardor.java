package Vista;

import ComponentesBasicos.JBButton;
import ComponentesBasicos.JBIconButton;
import ComponentesBasicos.JBScrollPane;
import ComponentesBasicos.JBSeparator;
import ComponentesBasicos.JBTextField;
import Controlador.Controlador;
import Modelo.Camara;
import Modelo.Cliente;
import Modelo.Video;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class VAdministardor extends javax.swing.JFrame {
   
    //El controlador y otros
    private Controlador controlador;
    
    //La vista
    private ImageIcon imageicon;
    private TrayIcon trayicon;
    private SystemTray systemtray;
    
    public VAdministardor(Controlador controlador) throws AWTException, SQLException, InterruptedException, ClassNotFoundException {
        initComponents();
    
        //informacion memoria
        this.controlador = controlador;
        
        //Ventana
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setTitle("SGCSR - Usuario");
        this.imageicon = new ImageIcon(this.getClass().getResource("/Imagenes/icono_temp.jpg"));
        this.setIconImage(this.imageicon.getImage());
        this.minimizarIcono();
        if(SystemTray.isSupported())
        {
            this.systemtray.add(trayicon);
        }
        
        //Botones
        this.boton_añadirCamara.setFocusable(false);  
        this.boton_realizar_busqueda.setFocusable(false);
        this.botonAñadirUsuario.setFocusable(false);
        
        //JPaneles
        subapartados.setFocusable(false);
        actualizarPanelCamaras();
        actualizarPanelClientes();
        actualizarPanelVideos();
        actualizarPanelRegistro();
    }
    
    private void minimizarIcono()
    {
        trayicon = new TrayIcon(this.imageicon.getImage(), "Icono random", popup);
        trayicon.setImageAutoSize(true);
        this.systemtray = SystemTray.getSystemTray();
    }
    
    public void actualizarPanelCamaras() throws SQLException, InterruptedException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = rellenarFilasCamaras();
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
        panel_lista_camaras.removeAll();
        panel_lista_camaras.revalidate();
        panel_lista_camaras.repaint();
        panel_lista_camaras.setLayout(new BorderLayout());
        panel_lista_camaras.add(scrollPane);
    }
    
    public ArrayList<JPanel> rellenarFilasCamaras() throws SQLException, InterruptedException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = new ArrayList<JPanel>();
        ArrayList<Camara> camaras = this.controlador.getCamaras();
        JPanel panel_fila;
        
        for(int i = 0; i < camaras.size(); ++i)
        {   
            panel_fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
            panel_fila.setBackground(new Color(127,127,127));

            panel_fila.add(Box.createRigidArea(new Dimension(5, 0)));
            Pattern patron = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3}).(\\d{1,3})");
            Matcher matcher = patron.matcher(camaras.get(i).getURL());
            String ip = "";
            while(matcher.find())
            {
                ip += matcher.group();
            }
            JBTextField nombre_camara = new JBTextField(ip);
            nombre_camara.setPreferredSize(new Dimension(120, 30));
            panel_fila.add(nombre_camara);

            //Boton - visualizar
            panel_fila.add(Box.createRigidArea(new Dimension(10, 0)));
            JBButton boton_visualizar = new JBButton("Visualizar");
            boton_visualizar.addActionListener(new ListenerVisualizarCamara(camaras.get(i).getURL(), controlador));
            panel_fila.add(boton_visualizar);

            //Boton - eliminar
            panel_fila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBIconButton eliminar = new JBIconButton(new ImageIcon(System.getProperty("user.dir") + "\\src\\Imagenes\\boton_eliminar.png"));
            eliminar.addActionListener(new ListenerEliminarCamara(camaras.get(i).getURL(), controlador));
            panel_fila.add(eliminar);
            filas.add(panel_fila);

            //Separador
            panel_fila = new JPanel();
            JBSeparator s = new JBSeparator();
            panel_fila.setBackground(new Color(127,127,127));
            panel_fila.add(s);

            filas.add(panel_fila);   
        }
        return filas;
    }
    
    class ListenerVisualizarCamara implements ActionListener{
        private String url;
        private Controlador controlador;
        
        public ListenerVisualizarCamara(String url, Controlador controlador) throws InterruptedException
        {
            this.url = url;
            this.controlador = controlador;
        }
         
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try {
                controlador.visualizarCamara(url);
            } catch (InterruptedException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class ListenerEliminarCamara implements ActionListener{
        private String url;
        private Controlador controlador;
        
        public ListenerEliminarCamara(String url, Controlador controlador)
        {
            this.controlador = controlador;
            this.url = url;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try {
                controlador.eliminarCamara(url);
            } catch (SQLException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    public void actualizarPanelClientes() throws SQLException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = rellenarFilasClientes();
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
        panel_lista_clientes.removeAll();
        panel_lista_clientes.revalidate();
        panel_lista_clientes.repaint();
        panel_lista_clientes.setLayout(new BorderLayout());
        panel_lista_clientes.add(scrollPane);
    }
    
    public ArrayList<JPanel> rellenarFilasClientes() throws SQLException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = new ArrayList();
        ArrayList<Cliente> clientes = controlador.getClientes();
        JPanel panelFila;
        
        for(int i = 0; i < clientes.size(); ++i)
        {
            
            panelFila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
            panelFila.setBackground(new Color(127, 127, 127));
            
            //Nombre del cliente
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBTextField nombreCliente = new JBTextField(clientes.get(i).getNombre());
            panelFila.add(nombreCliente);
            
            //Correo del cliente
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBTextField correoCliente = new JBTextField(clientes.get(i).getCorreo());
            panelFila.add(correoCliente);
            
            //Boton eliminar usuario
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBIconButton eliminar = new JBIconButton(new ImageIcon(System.getProperty("user.dir") + "\\src\\Imagenes\\boton_eliminar.png"));
            eliminar.addActionListener(new ListenerEliminarCliente(clientes.get(i).getNombre()));
            panelFila.add(eliminar);
            
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
    
    class ListenerEliminarCliente implements ActionListener
    {
        private String nombre;
        
        public ListenerEliminarCliente(String nombre)
        {
            this.nombre = nombre;
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                controlador.eliminarCliente(nombre);
            } catch (SQLException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void actualizarPanelVideos() throws SQLException, InterruptedException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = rellenarFilasVideos();
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
        panel_lista_videos.removeAll();
        panel_lista_videos.revalidate();
        panel_lista_videos.repaint();
        panel_lista_videos.setLayout(new BorderLayout());
        panel_lista_videos.add(scrollPane);
    }
    
    public ArrayList<JPanel> rellenarFilasVideos() throws SQLException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = new ArrayList();
        ArrayList<Video> videos = controlador.getVideos();
        JPanel panelFila;
        
        for(int i = 0; i < videos.size(); ++i)
        {
            panelFila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
            panelFila.setBackground(new Color(127, 127, 127));
            
            //La fecha del video
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            String fecha = videos.get(i).getDia() + "-" + videos.get(i).getMes() + "-" + videos.get(i).getAño() + " " + videos.get(i).getHora() + ":" + videos.get(i).getMinutos();
            JBTextField fechaDMA = new JBTextField(fecha);
            panelFila.add(fechaDMA);
            
            //Botono visualizar video
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBButton boton = new JBButton("Visualizar");
            boton.addActionListener(new ListenerVisualizarVideo(videos.get(i).getId()));
            panelFila.add(boton);
            
            //Boton eliminar usuario
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBIconButton eliminar = new JBIconButton(new ImageIcon(System.getProperty("user.dir") + "\\src\\Imagenes\\boton_eliminar.png"));
            eliminar.addActionListener(new ListenerEliminarVideo(videos.get(i)));
            panelFila.add(eliminar);
            
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
    
    public void actualizarVideosFechas(ArrayList<Video> videos)
    {
        ArrayList<JPanel> filas = new ArrayList();
        JPanel panelFila;
        for(int i = 0; i < videos.size(); ++i)
        {
            panelFila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
            panelFila.setBackground(new Color(127, 127, 127));
            
            //La fecha del video
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            String fecha = videos.get(i).getDia() + "-" + videos.get(i).getMes() + "-" + videos.get(i).getAño() + " " + videos.get(i).getHora() + ":" + videos.get(i).getMinutos();
            JBTextField fechaDMA = new JBTextField(fecha);
            panelFila.add(fechaDMA);
            
            //Botono visualizar video
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBButton boton = new JBButton("Visualizar");
            boton.addActionListener(new ListenerVisualizarVideo(videos.get(i).getId()));
            panelFila.add(boton);
            
            //Boton eliminar usuario
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBIconButton eliminar = new JBIconButton(new ImageIcon(System.getProperty("user.dir") + "\\src\\Imagenes\\boton_eliminar.png"));
            eliminar.addActionListener(new ListenerEliminarVideo(videos.get(i)));
            panelFila.add(eliminar);
            
            filas.add(panelFila);
            
            //Separador
            panelFila = new JPanel();
            JBSeparator s = new JBSeparator();
            panelFila.setBackground(new Color(127,127,127));
            panelFila.add(s);
            
            filas.add(panelFila);
        }
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
        panel_lista_videos.removeAll();
        panel_lista_videos.revalidate();
        panel_lista_videos.repaint();
        panel_lista_videos.setLayout(new BorderLayout());
        panel_lista_videos.add(scrollPane);
    }
    
    class ListenerEliminarVideo implements ActionListener
    {
        private Video video;
        
        public ListenerEliminarVideo(Video video)
        {
            this.video = video;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.eliminarVideo(video);
            } catch (SQLException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class ListenerVisualizarVideo implements ActionListener
    {
        private String id;
        
        public ListenerVisualizarVideo(String id)
        {
            this.id = id;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.visualizarVideo(id);
            } catch (InterruptedException ex) {
                Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void actualizarPanelRegistro() throws SQLException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = rellenarFilasRegistro();
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
        panel_lista_registro.removeAll();
        panel_lista_registro.revalidate();
        panel_lista_registro.repaint();
        panel_lista_registro.setLayout(new BorderLayout());
        panel_lista_registro.add(scrollPane);
    }
    
    public ArrayList<JPanel> rellenarFilasRegistro() throws SQLException, ClassNotFoundException
    {
        ArrayList<JPanel> filas = new ArrayList();
        HashMap<String, ArrayList<String>> registro = controlador.getSCS().obtenerRegistro().getHashMap();
        JPanel panelFila;

        for(Map.Entry<String, ArrayList<String>> i : registro.entrySet())
        {
            panelFila = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
            panelFila.setBackground(new Color(127, 127, 127));
            String id = i.getKey();
            ArrayList<String> nomberesClientes = i.getValue();
            
            //La fecha de la alarma
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBTextField fechaAlarma = new JBTextField(id);
            panelFila.add(fechaAlarma);
            
            //Botono visualizar nombre de clientes que han recibido la alarma
            panelFila.add(Box.createRigidArea(new Dimension(40, 0)));
            JBButton boton = new JBButton("Clientes que han recibido la alarma");
            boton.setPreferredSize(new Dimension(250, 31));
            boton.addActionListener(new ListenerVisualizarClientesAlarmas(nomberesClientes));
            panelFila.add(boton);
            
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
    
    public class ListenerVisualizarClientesAlarmas implements ActionListener
    {
        private ArrayList<String> clientes;
                
        public ListenerVisualizarClientesAlarmas(ArrayList<String> clientes)
        {
            this.clientes = clientes;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.setVisibleClientesAlarmas(clientes);
        }
    }
                        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new java.awt.PopupMenu();
        popup_Mostrar = new java.awt.MenuItem();
        popup_Salir = new java.awt.MenuItem();
        jProgressBar1 = new javax.swing.JProgressBar();
        panel_fondo = new javax.swing.JPanel();
        subapartados = new javax.swing.JTabbedPane();
        panel_principal_camaras = new javax.swing.JPanel();
        panel_lista_camaras = new javax.swing.JPanel();
        boton_añadirCamara = new javax.swing.JButton();
        panel_principal_videos = new javax.swing.JPanel();
        panel_lista_videos = new javax.swing.JPanel();
        texto_fechaInicial = new javax.swing.JLabel();
        texto_fechaFinal = new javax.swing.JLabel();
        entrada_fechaInicial_dia = new javax.swing.JTextField();
        entrada_fechaInicial_mes = new javax.swing.JTextField();
        entrada_fecahInicial_año = new javax.swing.JTextField();
        entrada_fechaFinal_dia = new javax.swing.JTextField();
        entrada_fechaFinal_mes = new javax.swing.JTextField();
        entrada_fechaFinall_año = new javax.swing.JTextField();
        boton_realizar_busqueda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panel_principal_usuarios = new javax.swing.JPanel();
        panel_lista_clientes = new javax.swing.JPanel();
        botonAñadirUsuario = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panel_prinicpal_registro = new javax.swing.JPanel();
        panel_lista_registro = new javax.swing.JPanel();
        menu = new javax.swing.JMenuBar();
        menu_ajustes = new javax.swing.JMenu();
        menuItem_correoTelefono = new javax.swing.JMenuItem();
        menuItem_modificarContraseña = new javax.swing.JMenuItem();
        menuItem_modificarRuta = new javax.swing.JMenuItem();
        menuItem_minimizar = new javax.swing.JMenuItem();
        menuItem_salir = new javax.swing.JMenuItem();

        popup.setLabel("popupMenu1");
        popup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupActionPerformed(evt);
            }
        });

        popup_Mostrar.setLabel("Mostrar");
        popup_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popup_MostrarActionPerformed(evt);
            }
        });
        popup.add(popup_Mostrar);

        popup_Salir.setLabel("Salir");
        popup_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popup_SalirActionPerformed(evt);
            }
        });
        popup.add(popup_Salir);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(127, 127, 127));

        panel_fondo.setBackground(new java.awt.Color(83, 83, 83));
        panel_fondo.setPreferredSize(new java.awt.Dimension(791, 707));

        subapartados.setBackground(new java.awt.Color(83, 83, 83));
        subapartados.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        subapartados.setForeground(new java.awt.Color(255, 255, 255));
        subapartados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        panel_principal_camaras.setBackground(new java.awt.Color(127, 127, 127));
        panel_principal_camaras.setForeground(new java.awt.Color(255, 255, 255));
        panel_principal_camaras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        panel_lista_camaras.setBackground(new java.awt.Color(127, 127, 127));
        panel_lista_camaras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(83, 83, 83), 2, true));
        panel_lista_camaras.setAutoscrolls(true);
        panel_lista_camaras.setPreferredSize(new java.awt.Dimension(730, 550));

        javax.swing.GroupLayout panel_lista_camarasLayout = new javax.swing.GroupLayout(panel_lista_camaras);
        panel_lista_camaras.setLayout(panel_lista_camarasLayout);
        panel_lista_camarasLayout.setHorizontalGroup(
            panel_lista_camarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_lista_camarasLayout.setVerticalGroup(
            panel_lista_camarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        boton_añadirCamara.setBackground(new java.awt.Color(83, 83, 83));
        boton_añadirCamara.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_añadirCamara.setForeground(new java.awt.Color(255, 255, 255));
        boton_añadirCamara.setText("Añadir cámara");
        boton_añadirCamara.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        boton_añadirCamara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_añadirCamaraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_principal_camarasLayout = new javax.swing.GroupLayout(panel_principal_camaras);
        panel_principal_camaras.setLayout(panel_principal_camarasLayout);
        panel_principal_camarasLayout.setHorizontalGroup(
            panel_principal_camarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_camarasLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(boton_añadirCamara, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(583, Short.MAX_VALUE))
            .addGroup(panel_principal_camarasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_camaras, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_principal_camarasLayout.setVerticalGroup(
            panel_principal_camarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_camarasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_camaras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(boton_añadirCamara, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subapartados.addTab("Cámaras", panel_principal_camaras);

        panel_principal_videos.setBackground(new java.awt.Color(127, 127, 127));
        panel_principal_videos.setForeground(new java.awt.Color(255, 255, 255));
        panel_principal_videos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        panel_lista_videos.setBackground(new java.awt.Color(127, 127, 127));
        panel_lista_videos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(83, 83, 83), 2, true));
        panel_lista_videos.setForeground(new java.awt.Color(255, 255, 255));
        panel_lista_videos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panel_lista_videos.setPreferredSize(new java.awt.Dimension(730, 550));

        javax.swing.GroupLayout panel_lista_videosLayout = new javax.swing.GroupLayout(panel_lista_videos);
        panel_lista_videos.setLayout(panel_lista_videosLayout);
        panel_lista_videosLayout.setHorizontalGroup(
            panel_lista_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_lista_videosLayout.setVerticalGroup(
            panel_lista_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        texto_fechaInicial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_fechaInicial.setForeground(new java.awt.Color(255, 255, 255));
        texto_fechaInicial.setText("Fecha inicial (dd-mm-aaaa)");

        texto_fechaFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto_fechaFinal.setForeground(new java.awt.Color(255, 255, 255));
        texto_fechaFinal.setText("Fecha final (dd-mm-aaaa)");

        entrada_fechaInicial_dia.setBackground(new java.awt.Color(83, 83, 83));
        entrada_fechaInicial_dia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entrada_fechaInicial_dia.setForeground(new java.awt.Color(255, 255, 255));
        entrada_fechaInicial_dia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        entrada_fechaInicial_mes.setBackground(new java.awt.Color(83, 83, 83));
        entrada_fechaInicial_mes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entrada_fechaInicial_mes.setForeground(new java.awt.Color(255, 255, 255));
        entrada_fechaInicial_mes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        entrada_fecahInicial_año.setBackground(new java.awt.Color(83, 83, 83));
        entrada_fecahInicial_año.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entrada_fecahInicial_año.setForeground(new java.awt.Color(255, 255, 255));
        entrada_fecahInicial_año.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        entrada_fechaFinal_dia.setBackground(new java.awt.Color(83, 83, 83));
        entrada_fechaFinal_dia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entrada_fechaFinal_dia.setForeground(new java.awt.Color(255, 255, 255));
        entrada_fechaFinal_dia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        entrada_fechaFinal_mes.setBackground(new java.awt.Color(83, 83, 83));
        entrada_fechaFinal_mes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entrada_fechaFinal_mes.setForeground(new java.awt.Color(255, 255, 255));
        entrada_fechaFinal_mes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        entrada_fechaFinall_año.setBackground(new java.awt.Color(83, 83, 83));
        entrada_fechaFinall_año.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        entrada_fechaFinall_año.setForeground(new java.awt.Color(255, 255, 255));
        entrada_fechaFinall_año.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        boton_realizar_busqueda.setBackground(new java.awt.Color(83, 83, 83));
        boton_realizar_busqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boton_realizar_busqueda.setForeground(new java.awt.Color(255, 255, 255));
        boton_realizar_busqueda.setText("Realizar búsqueda");
        boton_realizar_busqueda.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(125, 125, 125), new java.awt.Color(125, 125, 125), new java.awt.Color(125, 125, 125), new java.awt.Color(125, 125, 125)));
        boton_realizar_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_realizar_busquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_principal_videosLayout = new javax.swing.GroupLayout(panel_principal_videos);
        panel_principal_videos.setLayout(panel_principal_videosLayout);
        panel_principal_videosLayout.setHorizontalGroup(
            panel_principal_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_videosLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel_principal_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto_fechaInicial)
                    .addGroup(panel_principal_videosLayout.createSequentialGroup()
                        .addComponent(entrada_fechaInicial_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entrada_fechaInicial_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entrada_fecahInicial_año, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74)
                .addGroup(panel_principal_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principal_videosLayout.createSequentialGroup()
                        .addComponent(entrada_fechaFinal_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entrada_fechaFinal_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entrada_fechaFinall_año, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(boton_realizar_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(texto_fechaFinal))
                .addContainerGap(134, Short.MAX_VALUE))
            .addGroup(panel_principal_videosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_videos, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_principal_videosLayout.setVerticalGroup(
            panel_principal_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_videosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_videos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_principal_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto_fechaInicial)
                    .addComponent(texto_fechaFinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_principal_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entrada_fechaInicial_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entrada_fechaInicial_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entrada_fecahInicial_año, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entrada_fechaFinal_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_principal_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(entrada_fechaFinal_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(entrada_fechaFinall_año, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(boton_realizar_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subapartados.addTab("Vídeos", panel_principal_videos);

        panel_principal_usuarios.setBackground(new java.awt.Color(127, 127, 127));
        panel_principal_usuarios.setForeground(new java.awt.Color(255, 255, 255));
        panel_principal_usuarios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        panel_lista_clientes.setBackground(new java.awt.Color(127, 127, 127));
        panel_lista_clientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(83, 83, 83), 2, true));
        panel_lista_clientes.setAutoscrolls(true);
        panel_lista_clientes.setPreferredSize(new java.awt.Dimension(730, 550));

        javax.swing.GroupLayout panel_lista_clientesLayout = new javax.swing.GroupLayout(panel_lista_clientes);
        panel_lista_clientes.setLayout(panel_lista_clientesLayout);
        panel_lista_clientesLayout.setHorizontalGroup(
            panel_lista_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_lista_clientesLayout.setVerticalGroup(
            panel_lista_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        botonAñadirUsuario.setBackground(new java.awt.Color(83, 83, 83));
        botonAñadirUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonAñadirUsuario.setForeground(new java.awt.Color(255, 255, 255));
        botonAñadirUsuario.setText("Añadir usuario");
        botonAñadirUsuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127), new java.awt.Color(127, 127, 127)));
        botonAñadirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_principal_usuariosLayout = new javax.swing.GroupLayout(panel_principal_usuarios);
        panel_principal_usuarios.setLayout(panel_principal_usuariosLayout);
        panel_principal_usuariosLayout.setHorizontalGroup(
            panel_principal_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_usuariosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(botonAñadirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(583, Short.MAX_VALUE))
            .addGroup(panel_principal_usuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_principal_usuariosLayout.setVerticalGroup(
            panel_principal_usuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_usuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(botonAñadirUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal_usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal_usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        subapartados.addTab("Clientes", jPanel1);

        panel_prinicpal_registro.setBackground(new java.awt.Color(127, 127, 127));
        panel_prinicpal_registro.setForeground(new java.awt.Color(255, 255, 255));

        panel_lista_registro.setBackground(new java.awt.Color(127, 127, 127));
        panel_lista_registro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(83, 83, 83), new java.awt.Color(83, 83, 83), new java.awt.Color(83, 83, 83), new java.awt.Color(83, 83, 83)));
        panel_lista_registro.setForeground(new java.awt.Color(255, 255, 255));
        panel_lista_registro.setPreferredSize(new java.awt.Dimension(730, 550));

        javax.swing.GroupLayout panel_lista_registroLayout = new javax.swing.GroupLayout(panel_lista_registro);
        panel_lista_registro.setLayout(panel_lista_registroLayout);
        panel_lista_registroLayout.setHorizontalGroup(
            panel_lista_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
        );
        panel_lista_registroLayout.setVerticalGroup(
            panel_lista_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_prinicpal_registroLayout = new javax.swing.GroupLayout(panel_prinicpal_registro);
        panel_prinicpal_registro.setLayout(panel_prinicpal_registroLayout);
        panel_prinicpal_registroLayout.setHorizontalGroup(
            panel_prinicpal_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_prinicpal_registroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_registro, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_prinicpal_registroLayout.setVerticalGroup(
            panel_prinicpal_registroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_prinicpal_registroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_lista_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_prinicpal_registro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_prinicpal_registro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        subapartados.addTab("Registro", jPanel2);

        javax.swing.GroupLayout panel_fondoLayout = new javax.swing.GroupLayout(panel_fondo);
        panel_fondo.setLayout(panel_fondoLayout);
        panel_fondoLayout.setHorizontalGroup(
            panel_fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subapartados, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panel_fondoLayout.setVerticalGroup(
            panel_fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subapartados, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        menu.setBackground(new java.awt.Color(83, 83, 83));
        menu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menu.setForeground(new java.awt.Color(255, 255, 255));

        menu_ajustes.setBackground(new java.awt.Color(83, 83, 83));
        menu_ajustes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menu_ajustes.setForeground(new java.awt.Color(255, 255, 255));
        menu_ajustes.setText("Ajustes");

        menuItem_correoTelefono.setBackground(new java.awt.Color(83, 83, 83));
        menuItem_correoTelefono.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_correoTelefono.setText("Modificar correo");
        menuItem_correoTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menuItem_correoTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_correoTelefonoActionPerformed(evt);
            }
        });
        menu_ajustes.add(menuItem_correoTelefono);

        menuItem_modificarContraseña.setBackground(new java.awt.Color(83, 83, 83));
        menuItem_modificarContraseña.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_modificarContraseña.setText("Modificar contraseña");
        menuItem_modificarContraseña.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menuItem_modificarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_modificarContraseñaActionPerformed(evt);
            }
        });
        menu_ajustes.add(menuItem_modificarContraseña);

        menuItem_modificarRuta.setBackground(new java.awt.Color(83, 83, 83));
        menuItem_modificarRuta.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_modificarRuta.setText("Seleccionar algoritmo");
        menuItem_modificarRuta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menuItem_modificarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_modificarRutaActionPerformed(evt);
            }
        });
        menu_ajustes.add(menuItem_modificarRuta);

        menuItem_minimizar.setBackground(new java.awt.Color(83, 83, 83));
        menuItem_minimizar.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_minimizar.setText("Minimizar");
        menuItem_minimizar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menuItem_minimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_minimizarActionPerformed(evt);
            }
        });
        menu_ajustes.add(menuItem_minimizar);

        menuItem_salir.setBackground(new java.awt.Color(83, 83, 83));
        menuItem_salir.setForeground(new java.awt.Color(255, 255, 255));
        menuItem_salir.setText("Salir");
        menuItem_salir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menuItem_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_salirActionPerformed(evt);
            }
        });
        menu_ajustes.add(menuItem_salir);

        menu.add(menu_ajustes);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void popup_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popup_MostrarActionPerformed
        this.setVisible(true);
    }//GEN-LAST:event_popup_MostrarActionPerformed

    private void popup_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popup_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_popup_SalirActionPerformed

    private void menuItem_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_minimizarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_menuItem_minimizarActionPerformed

    private void menuItem_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItem_salirActionPerformed

    private void menuItem_correoTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_correoTelefonoActionPerformed
        try {
            controlador.moodificarCorreoVisible();
        } catch (MessagingException ex) {
            Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem_correoTelefonoActionPerformed

    private void menuItem_modificarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_modificarRutaActionPerformed
        controlador.setVisibleCambiarAlgoritmo();
    }//GEN-LAST:event_menuItem_modificarRutaActionPerformed

    private void boton_añadirCamaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_añadirCamaraActionPerformed
        controlador.insertarCamara();
    }//GEN-LAST:event_boton_añadirCamaraActionPerformed

    private void menuItem_modificarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_modificarContraseñaActionPerformed
        try {
            controlador.modificarContraseñaVisible();
        } catch (MessagingException ex) {
            Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem_modificarContraseñaActionPerformed

    private void popupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_popupActionPerformed

    private void botonAñadirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAñadirUsuarioActionPerformed
        controlador.añadirClienteVisible();
    }//GEN-LAST:event_botonAñadirUsuarioActionPerformed

    private void boton_realizar_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_realizar_busquedaActionPerformed
        try {
            controlador.buscarVideo(entrada_fechaInicial_dia.getText(), entrada_fechaInicial_mes.getText(), entrada_fecahInicial_año.getText(), entrada_fechaInicial_dia.getText(), entrada_fechaInicial_mes.getText(), entrada_fechaFinall_año.getText());
        } catch (ParseException ex) {
            Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VAdministardor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_boton_realizar_busquedaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAñadirUsuario;
    private javax.swing.JButton boton_añadirCamara;
    private javax.swing.JButton boton_realizar_busqueda;
    private javax.swing.JTextField entrada_fecahInicial_año;
    private javax.swing.JTextField entrada_fechaFinal_dia;
    private javax.swing.JTextField entrada_fechaFinal_mes;
    private javax.swing.JTextField entrada_fechaFinall_año;
    private javax.swing.JTextField entrada_fechaInicial_dia;
    private javax.swing.JTextField entrada_fechaInicial_mes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem menuItem_correoTelefono;
    private javax.swing.JMenuItem menuItem_minimizar;
    private javax.swing.JMenuItem menuItem_modificarContraseña;
    private javax.swing.JMenuItem menuItem_modificarRuta;
    private javax.swing.JMenuItem menuItem_salir;
    private javax.swing.JMenu menu_ajustes;
    private javax.swing.JPanel panel_fondo;
    private javax.swing.JPanel panel_lista_camaras;
    private javax.swing.JPanel panel_lista_clientes;
    private javax.swing.JPanel panel_lista_registro;
    private javax.swing.JPanel panel_lista_videos;
    private javax.swing.JPanel panel_principal_camaras;
    private javax.swing.JPanel panel_principal_usuarios;
    private javax.swing.JPanel panel_principal_videos;
    private javax.swing.JPanel panel_prinicpal_registro;
    private java.awt.PopupMenu popup;
    private java.awt.MenuItem popup_Mostrar;
    private java.awt.MenuItem popup_Salir;
    private javax.swing.JTabbedPane subapartados;
    private javax.swing.JLabel texto_fechaFinal;
    private javax.swing.JLabel texto_fechaInicial;
    // End of variables declaration//GEN-END:variables
}