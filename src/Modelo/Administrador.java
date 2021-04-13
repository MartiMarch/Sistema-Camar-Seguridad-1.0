package Modelo;

import ClasesAuxiliares.ArchivoConfiguracion;
import ClasesAuxiliares.Encriptador;
import java.util.ArrayList;

public class Administrador{
    private String contraseña;
    private String email;
    private String contraseñaCorreo;
    private String ruta;
    
    public Administrador(){}
    
    public Administrador(String contraseña, String email, String contraseñaCorreo, String ruta)
    {
        this.contraseña = contraseña;
        this.email = email;
        this.contraseñaCorreo = contraseñaCorreo;
        this.ruta = ruta;
    }
    
    public Administrador registrarAdministrador(String contraseña, String correo, String contraseñaCorreo)
    {
        Encriptador encriptador = new Encriptador();
        ArchivoConfiguracion archivo = new ArchivoConfiguracion();
        Administrador administrador = new Administrador(contraseña, correo, contraseñaCorreo, "C:\\SGCSR");
        ArrayList<String> hash = encriptador.generarHash(contraseña);
        contraseña += hash.get(1);
        
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
}
