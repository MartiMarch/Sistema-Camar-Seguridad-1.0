package Modelo;

import ClasesAuxiliares.ArchivoConfiguracion;
import ClasesAuxiliares.Encriptador;
import Vista.VisualizarCamara;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class Administrador{
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_java451");
        System.loadLibrary("opencv_videoio_ffmpeg451_64");
    }
    
    private String contraseña;
    private String email;
    private String contraseñaCorreo;
    private String ruta;
    private ArchivoConfiguracion archivo = new ArchivoConfiguracion();
    private Encriptador encriptador = new Encriptador();
    
    public Administrador(){}
    
    public Administrador(String contraseña, String email, String contraseñaCorreo, String ruta)
    {
        this.contraseña = contraseña + archivo.consultarParametro("salt");
        this.email = email;
        this.contraseñaCorreo = contraseñaCorreo;
        this.ruta = ruta;
    }
    
    public Administrador registrarAdministrador(String contraseña, String correo, String contraseñaCorreo)
    {
        Administrador administrador = new Administrador(contraseña, correo, contraseñaCorreo, "C:\\SGCSR");
        ArrayList<String> hash = encriptador.generarHash(contraseña);
        contraseña += hash.get(1);
        
        this.contraseña = contraseña;
        this.email = correo;
        this.contraseñaCorreo = contraseñaCorreo;
        this.ruta = "C:\\SGCSR";
        
        archivo.guardarParametro("contraseña", hash.get(0));
        archivo.guardarParametro("salt", hash.get(1));
        correo = encriptador.encriptar(correo, contraseña);
        archivo.guardarParametro("email", correo);
        contraseñaCorreo = encriptador.encriptar(contraseñaCorreo, contraseña);
        archivo.guardarParametro("contraseñaCorreo", contraseñaCorreo);
        archivo.guardarParametro("ruta", "C:\\SGCSR");
        
        return administrador;
    }
    
    public boolean iniciarSesionAdministrador(String contraseña)
    {
        boolean identificado = false;

        ArchivoConfiguracion archivo = new ArchivoConfiguracion();
        String contraseñaCifrada = archivo.consultarParametro("contraseña");
        String salt = archivo.consultarParametro("salt");
        contraseña += salt;
        Encriptador encriptador = new Encriptador();
        if(contraseña.equals(encriptador.desencriptar(contraseñaCifrada, contraseña)))
        {
            String email = archivo.consultarParametro("email");
            email = encriptador.desencriptar(email, contraseña);
            String contraseñaCorreo = archivo.consultarParametro("contraseñaCorreo");
            contraseñaCorreo = encriptador.desencriptar(contraseñaCorreo, contraseña);
            String ruta = archivo.consultarParametro("ruta");
         
            this.contraseña = contraseña;
            this.email = email;
            this.contraseñaCorreo = contraseñaCorreo;
            this.ruta = ruta;
            
            identificado = true;
        }
        
        return identificado;
    }
    
    public boolean insertarCamara(String url) throws ClassNotFoundException, SQLException
    {
        boolean insertado = true;
        
        Pattern rtsp_patron = Pattern.compile("rtsp://((([a-zA-Z0-9_]+):([a-zA-Z0-9_]+)@)?)(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3}).(\\d{1,3})(.*)");
        Pattern http_patron = Pattern.compile("http://(.*)");
        Pattern https_patron = Pattern.compile("https://(.*)");
        Matcher matcher_rstp = rtsp_patron.matcher(url);
        Matcher matcher_http = http_patron.matcher(url);
        Matcher matcher_https = https_patron.matcher(url);
        if(!matcher_rstp.matches() && !matcher_http.matches() && !matcher_https.matches())
        {
            JOptionPane.showMessageDialog(null, "La URL introducida no tiene el formato esperado.");
            insertado = false;
        }
        
        if(insertado)
        {
            VideoCapture camara = new VideoCapture(url);
            if(camara.isOpened())
            {
                camara.release();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ninguna cámara tiene asociada la URL introducida.");
                insertado = false;
            }
        }
        
        if(insertado)
        {
            Statement st = conexion().createStatement();
            String existeCamara = "SELECT * FROM camaras WHERE url = '" + url + "'";
            ResultSet rs = st.executeQuery(existeCamara);
            int numeroCamaras = 0;
            while(rs.next())
            {
                ++numeroCamaras;
            }
            if(numeroCamaras==0)
            {
                url = encriptador.encriptar(url, contraseña);
                String insertarCamara = "INSERT INTO camaras VALUES ('" + url + "')";
                st.executeUpdate(insertarCamara);
            }
            else
            {
                insertado = false;
            }
            rs.close();
            st.close();
        }
        
        return insertado;
    }
    
    public void eliminarCamara(String url) throws SQLException, ClassNotFoundException
    {
        url = encriptador.encriptar(url, contraseña);
        Statement st = conexion().createStatement();
        String eliminarCamara = "DELETE FROM camaras WHERE url = '" + url + "'";
        st.executeUpdate(eliminarCamara);
        st.close();
    }
    
    public void visualizarCamara(String url) throws InterruptedException
    {
        VisualizarCamara vc = new VisualizarCamara(url);
        vc.setVisible(true);
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseñaCorreo() {
        return contraseñaCorreo;
    }

    public String getRuta() {
        return ruta;
    }
    public Connection conexion() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgcsr","root","3+UNO=cuatro");
        return conexion;
    }
}
