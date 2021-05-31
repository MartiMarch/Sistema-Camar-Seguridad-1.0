package Controlador;

import ClasesAuxiliares.ArchivoConfiguracion;
import ClasesAuxiliares.Correo;
import ClasesAuxiliares.Encriptador;
import Modelo.Administrador;
import Modelo.Camara;
import Modelo.Cliente;
import Modelo.Movimiento;
import Modelo.SistemaCamarasSeguridad;
import Modelo.Video;
import Vista.AñadirCamara;
import Vista.AñadirUsuario;
import Vista.IniciarSesion;
import Vista.ModificarContraseña;
import Vista.ModificarCorreo;
import Vista.RegistroUnico;
import Vista.VAdministardor;
import java.awt.AWTException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

public class Controlador {
    private Administrador administrador = new Administrador();
    private Encriptador encriptador = new Encriptador();
    private VAdministardor v_administrador;
    private ModificarCorreo modificarCorreo = new ModificarCorreo();
    private ModificarContraseña modificarContraseña = new ModificarContraseña();
    private AñadirUsuario añadirUsuario = new AñadirUsuario();
    private Correo correo = new Correo();
    private SistemaCamarasSeguridad scs;
    private String numeroSecretoCorreo = "";
    private Movimiento movimiento;
    
    public Controlador(){}
    
    public void CrearBBDD()
    {
        String crear_database = "CREATE DATABASE IF NOT EXISTS sgcsr;";
        
        String crear_tablaClientes = 
           "CREATE TABLE IF NOT EXISTS clientes("
               +"nombre VARCHAR (255) NOT NULL, "
               +"password VARCHAR (255) NOT NULL, "
               +"salt VARCHAR (255), "
               +"email VARCHAR (255) NOT NULL, "
               +"PRIMARY KEY (nombre)"
               +");";       
       
        String crear_tablaVideos = 
           "CREATE TABLE IF NOT EXISTS videos("
               +"id VARCHAR(255), "
               +"year INT NOT NULL, "
               +"day INT NOT NULL, "
               +"month INT NOT NULL, "
               +"seconds INT NOT NULL, "
               +"hour INT NOT NULL, "
               +"minutes INT NOT NULL, "
               +"PRIMARY KEY (id)"
               +");";       
        
        String crear_tablaCamaras = 
           "CREATE TABLE IF NOT EXISTS camaras("
               +"url VARCHAR(255) NOT NULL, "
               +"PRIMARY KEY (url)"
               +");";
        
        String crear_tablaAlarmas =
            "CREATE TABLE IF NOT EXISTS alarmas("
                +"id VARCHAR(255), "
                +"year INT NOT NULL, "
                +"day INT NOT NULL, "
                +"month INT NOT NULL, "
                +"seconds INT NOT NULL, "
                +"PRIMARY KEY (id)"
                +");";
        
        String crear_tablaCamarasClientes =
            "CREATE TABLE IF NOT EXISTS camarasClientes("
                +"id INT NOT NULL AUTO_INCREMENT, "
                +"nombreCliente VARCHAR(255) NOT NULL, "
                +"urlCamara VARCHAR(255) NOT NULL, "
                +"nombreCamara VARCHAR(255), "
                +"estado VARCHAR(255), "
                +"PRIMARY KEY (id), "
                +"CONSTRAINT fk_cc_cliente FOREIGN KEY (nombreCliente) REFERENCES clientes (nombre), "
                +"CONSTRAINT fk_cc_camara FOREIGN KEY (urlCamara) REFERENCES camaras (url) "
                + ");";
        
        String crear_tablaAlarmasClientes =
            "CREATE TABLE IF NOT EXISTS alarmasClientes("
                +"id INT NOT NULL AUTO_INCREMENT, "
                +"nombreCliente VARCHAR(255) NOT NULL, "
                +"idAlarma VARCHAR(255) NOT NULL, "
                +"PRIMARY KEY (id),"
                +"CONSTRAINT fk_ac_cliente FOREIGN KEY (nombreCliente) REFERENCES clientes (nombre), "
                +"CONSTRAINT fk_ac_alarma FOREIGN KEY (idAlarma) REFERENCES alarmas (id) "
                + ");";
        
        try { 
            Connection conexion = conexion();
            Statement st = conexion.createStatement(); 
            st.executeUpdate(crear_database);
            st.executeUpdate(crear_tablaClientes);
            st.executeUpdate(crear_tablaVideos );
            st.executeUpdate(crear_tablaCamaras);
            st.executeUpdate(crear_tablaAlarmas);
            st.executeUpdate(crear_tablaCamarasClientes);
            st.executeUpdate(crear_tablaAlarmasClientes);
            conexion.close();
            st.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection conexion() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgcsr","root","3+UNO=cuatro");
        return conexion;
    }
    
    public void iniciarAdministrador() throws InterruptedException
    {
        ArchivoConfiguracion archivo = new ArchivoConfiguracion();
        if(archivo.consultarParametro("contraseña").equals(""))
        {
            RegistroUnico ru = new RegistroUnico(this);
            ru.setVisible(true);
            while(!ru.isRegistrado())
            {
                Thread.sleep(1);
            }
        } 
        IniciarSesion is = new IniciarSesion(this);
        is.setVisible(true);
    }
    
    public void registrarAdministrador(String contraseña, String correo, String contraseñaCorreo)
    {
        administrador = administrador.registrarAdministrador(contraseña, correo, contraseñaCorreo);
    }
    
    public boolean iniciarSesionAdministrador(String contraseña) throws AWTException, SQLException, InterruptedException, ClassNotFoundException
    {
        boolean identificacion = false;
        if(administrador.iniciarSesionAdministrador(contraseña))
        {
            identificacion = true;
            scs = new SistemaCamarasSeguridad(encriptador, administrador);
            movimiento = new Movimiento(this);
            v_administrador = new VAdministardor(this);
            v_administrador.setVisible(true);
        }
        return identificacion;
    }
    
    public ArrayList<Camara> getCamaras() throws SQLException, ClassNotFoundException
    {
        ArrayList<Camara> camaras = new ArrayList<>();
        Statement st = conexion().createStatement();
        String obtenerCamaras = "SELECT * FROM camaras";
        ResultSet rs = st.executeQuery(obtenerCamaras);
        while(rs.next())
        {
            String url = rs.getString("url");
            url = encriptador.desencriptar(url, administrador.getContraseña());
            Camara camara = new Camara(url);
            camaras.add(camara);
        }
        
        return camaras;
    }

    public ArrayList<Cliente> getClientes() throws SQLException, ClassNotFoundException
    {
        ArrayList<Cliente> clientes = new ArrayList();
        Statement st = conexion().createStatement();
        String obtenerClientes = "SELECT * FROM clientes";
        ResultSet rs = st.executeQuery(obtenerClientes);
        while(rs.next())
        {
            String nombre = encriptador.desencriptar(rs.getString("nombre"), administrador.getContraseña());
            String email = encriptador.desencriptar(rs.getString("email"), administrador.getContraseña());
            Cliente cliente = new Cliente(nombre, email);
            clientes.add(cliente);
        }
        
        return clientes;
    }
    
    public ArrayList<Video> getVideos() throws SQLException, ClassNotFoundException
    {
        return scs.obtenerVideos();
    }
    
    public Encriptador getEncriptador() 
    {
        return encriptador;
    }
    
    public void insertarCamara()
    {
        AñadirCamara ac = new AñadirCamara(this);
        ac.setVisible(true);
    }

    public Administrador getAdministrador() {
        return administrador;
    }
    
    public boolean insertarCamara(String url) throws ClassNotFoundException, SQLException, InterruptedException
    {
        boolean añadir = administrador.insertarCamara(url, movimiento);
        v_administrador.actualizarPanelCamaras();
        return añadir;
    }
    
    public void visualizarCamara(String url) throws InterruptedException
    {
        administrador.visualizarCamara(url);
    }
    
    public void eliminarCamara(String url) throws SQLException, ClassNotFoundException, InterruptedException
    {
        administrador.eliminarCamara(url, movimiento);
        v_administrador.actualizarPanelCamaras();
    }
    
    public static void main(String args[]) throws IOException, AWTException, InterruptedException, SQLException {
        Controlador c = new Controlador();
        c.CrearBBDD();
        c.iniciarAdministrador();
    }
    
    public void moodificarCorreoVisible() throws MessagingException
    {
        modificarCorreo.setControlador(this);
        modificarCorreo.setEntradaNumeroCorreo("");
        modificarCorreo.setEntradaContraseña_1("");
        modificarCorreo.setEntradaContraseña_2("");
        modificarCorreo.setEntradaCorreo("");
        enviarSecretoCorreo();
        modificarCorreo.setVisible(true);
    }
    
    public boolean modificarCorreo(String correo, String contraseña1, String contraseña2, String contraseñaEnviadaCorreo)
    {
        return administrador.modificarCorreo(correo, contraseña1, contraseña2, contraseñaEnviadaCorreo, numeroSecretoCorreo);
    }
    
    public void modificarContraseñaVisible() throws MessagingException
    {
        modificarContraseña.setControlador(this);
        modificarContraseña.setEntradNumeroCorreo("");
        modificarContraseña.setEntradaContraseña1("");
        modificarContraseña.setEntradaContraseña2("");
        enviarSecretoCorreo();
        modificarContraseña.setVisible(true);
    }
    
    public boolean modificarContraseña(String contraseña1, String contraseña2, String numeroCorreo)
    {
        return administrador.modificarContaseña(contraseña1, contraseña2, numeroCorreo, numeroSecretoCorreo);
    }
    
    public void añadirClienteVisible()
    {
        añadirUsuario.setControlador(this);
        añadirUsuario.setEntradaCorreo("");
        añadirUsuario.setEntradaNombre("");
        añadirUsuario.setEtradaContraseña1("");
        añadirUsuario.setEtradaContraseña2("");
        añadirUsuario.setVisible(true);
    }
    
    public boolean añadirCliente(String nombre, String correo, String contraseña1, String contraseña2) throws SQLException, ClassNotFoundException
    {
        boolean resultado = administrador.añadirCliente(nombre, correo, contraseña1, contraseña2);
        v_administrador.actualizarPanelClientes();
        return resultado;
    }
    
    public void eliminarCliente(String nombre) throws SQLException, ClassNotFoundException
    {
        administrador.eliminarCliente(nombre);
        v_administrador.actualizarPanelClientes();
    }
    
    public void enviarSecretoCorreo() throws MessagingException
    {
        List<Integer> digitos = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            digitos.add(i);
        }
        Collections.shuffle(digitos);
        numeroSecretoCorreo = "";
        for(int i = 0; i < 5; i++)
        {
            numeroSecretoCorreo += digitos.get(i).toString();
        }
        correo.enviarCorreo(administrador.getEmail(), administrador.getContraseñaCorreo(), administrador.getEmail(), 
            "SGCS - Cambio de correo electónico",
            "El nuevo número de seguridad que has de introducir es el siguiente: " + numeroSecretoCorreo + "\n"
        );
        JOptionPane.showMessageDialog(null, "Revisa tu correo para obtener el número de seguridad.");
    }
    
    public SistemaCamarasSeguridad getSCS()
    {
        return scs;
    }
    
    public VAdministardor getVAdministrar()
    {
        return this.v_administrador;
    }
    
    public void eliminarVideo(Video video) throws SQLException, InterruptedException, ClassNotFoundException, ParseException
    {
        administrador.eliminarVideo(video);
        v_administrador.actualizarPanelVideos();
    }
    
    public void visualizarVideo(String id) throws InterruptedException
    {
        administrador.visualizarVideo(id);
    }
}