package Modelo;

import ClasesAuxiliares.Encriptador;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SistemaCamarasSeguridad {
    private Encriptador encriptador;
    private Administrador administrador;
    
    public SistemaCamarasSeguridad(){}
    
    public SistemaCamarasSeguridad(Encriptador encriptador, Administrador administrador)
    {
        this.encriptador = encriptador;
        this.administrador = administrador;
    }
    
    public Connection conexion() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgcsr","root","3+UNO=cuatro");
        return conexion;
    }
    
    public ArrayList<Camara> obtenerCamras() throws SQLException, ClassNotFoundException
    {
        ArrayList<Camara> camaras = new ArrayList();
        Statement st = (Statement) conexion().createStatement();
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
    
    public ArrayList<Cliente> obtenerClientes() throws SQLException, ClassNotFoundException
    {
        ArrayList<Cliente> clientes = new ArrayList();
        Statement st = (Statement) conexion().createStatement();
        String obtenerClientes = "SELECT * FROM clientes";
        ResultSet rs = st.executeQuery(obtenerClientes);
        
        while(rs.next())
        {
            String nombre = rs.getString("nombre");
            nombre = encriptador.desencriptar(nombre, administrador.getContraseña());
            String correo = rs.getString("email");
            correo = encriptador.desencriptar(correo, administrador.getContraseña());
            Cliente cliente = new Cliente(nombre, correo);
            clientes.add(cliente);
        }
        rs.close();
        st.close();
        
        return clientes;
    }
    
    public ArrayList<Video> obtenerVideos() throws SQLException, ClassNotFoundException
    {
        ArrayList<Video> videos = new ArrayList();
        Statement st = (Statement) conexion().createStatement();
        String obtenerClientes = "SELECT * FROM videos";
        ResultSet rs = st.executeQuery(obtenerClientes);
        
        while(rs.next())
        {
            String id = rs.getString("id");
            int year = rs.getInt("year");
            int day = rs.getInt("day");
            int month = rs.getInt("month");
            int seconds = rs.getInt("seconds");
            int hora = rs.getInt("hour");
            int minutos = rs.getInt("minutes");
            Video video = new Video(id, year, day, month, hora, minutos, seconds);
            videos.add(video);
        }
        
        return videos;
    }
    
    public Registro obtenerRegistro() throws SQLException, ClassNotFoundException
    {
        Registro registro = new Registro();
        Statement STalarmas = (Statement) conexion().createStatement();
        Statement STalarmasClientes = (Statement) conexion().createStatement();
        String obtenerAlarmas = "SELECT * FROM alarmas";
        ResultSet RSalarmas = STalarmas.executeQuery(obtenerAlarmas);
        ResultSet RSalarmasClientes = null;
        while(RSalarmas.next())
        {
            String id = RSalarmas.getString("id");
            registro.addAlarma(id);
            String obtenerAlarmasClientes = "SELECT * FROM alarmasclientes WHERE id = '" + id + "'";
            RSalarmasClientes = STalarmasClientes.executeQuery(obtenerAlarmasClientes);
            while(RSalarmasClientes.next())
            {
                String nombreCliente = RSalarmasClientes.getString("nombreCliente");
                nombreCliente = encriptador.desencriptar(nombreCliente, administrador.getContraseña());
                registro.addClinete(id, nombreCliente);
            }
        } 
        RSalarmas.close();
        RSalarmasClientes.close();
        STalarmas.close();
        STalarmasClientes.close();
        
        return registro;
    }
}
