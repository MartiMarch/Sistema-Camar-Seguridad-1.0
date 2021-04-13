package Controlador;

import ClasesAuxiliares.ArchivoConfiguracion;
import ClasesAuxiliares.Encriptador;
import Modelo.Administrador;
import Vista.IniciarSesion;
import Vista.RegistroUnico;
import Vista.VAdministardor;
import java.awt.AWTException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador {
    private Administrador administrador = new Administrador();
    private Encriptador encriptador = new Encriptador();
    private VAdministardor v_administrador;
    
    public Controlador()
    {
    }
    
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
               +"ruta VARCHAR(255), "
               +"PRIMARY KEY (id)"
               +");";       
        
        String crear_tablaCamaras = 
           "CREATE TABLE IF NOT EXISTS camaras("
               +"url VARCHAR(255) NOT NULL, "
               +"PRIMARY KEY (url)"
               +");";
        
        try { 
            Connection conexion = conexion();
            Statement st = conexion.createStatement(); 
            st.executeUpdate(crear_database);
            st.executeUpdate(crear_tablaClientes);
            st.executeUpdate(crear_tablaVideos );
            st.executeUpdate(crear_tablaCamaras);
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
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tfg_aplicacion","root","3+UNO=cuatro");
        return conexion;
    }
    
    public void iniciarAdministrador()
    {
        ArchivoConfiguracion archivo = new ArchivoConfiguracion();
        if(archivo.consultarParametro("contraseña").equals(""))
        {
            RegistroUnico ru = new RegistroUnico(this);
            ru.setVisible(true);
        }
        else
        {
            IniciarSesion is = new IniciarSesion(this);
            is.setVisible(true);
        }
    }
    
    public void registrarAdministrador(String contraseña, String correo, String contraseñaCorreo)
    {
        administrador = administrador.registrarAdministrador(contraseña, correo, contraseñaCorreo);
    }
    
    public boolean iniciarSesionAdministrador(String contraseña) throws AWTException, SQLException, InterruptedException
    {
        boolean identificacion = false;
        if(administrador.iniciarSesionAdministrador(contraseña))
        {
            identificacion = true;
            v_administrador = new VAdministardor(this);
            v_administrador.setVisible(true);
        }
        
        return identificacion;
    }
    
    public static void main(String args[]) throws IOException, AWTException, InterruptedException, SQLException {
        Controlador c = new Controlador();
        c.CrearBBDD();
        c.iniciarAdministrador();
        
    }
}
