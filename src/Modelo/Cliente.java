package Modelo;

public class Cliente {
    private String nombre;
    private String contraseña;
    private String salt;
    private String correo;
    
    public Cliente(){}
    
    public Cliente(String nombre, String contraseña, String salt, String correo)
    {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.salt = salt;
        this.correo = correo;
    }
    
    public Cliente(String nombre, String correo)
    {
        this.nombre = nombre;
        this.correo = correo;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public String getCorreo()
    {
        return correo;
    }
}
