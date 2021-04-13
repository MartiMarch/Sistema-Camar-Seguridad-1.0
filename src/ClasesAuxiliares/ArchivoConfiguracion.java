package ClasesAuxiliares;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchivoConfiguracion {
    private String ruta;
    private Properties conf;
    private OutputStream outConf;
    private InputStream inConf;
    
    public ArchivoConfiguracion()
    {
        File directorioProyecto = new File(".");
        ruta = directorioProyecto.getAbsolutePath();
        ruta = ruta.substring(0, (ruta.length()-1)) + "configuracion.properties";
       
        File archivo = new File(ruta);
        if(!(archivo.exists() && !archivo.isDirectory()))
        {
            conf = new Properties();
            conf.put("contraseña", "");
            conf.put("salt", "");
            conf.put("email", "");
            conf.put("contraseñaCorreo", "");
            conf.put("ruta", "C:\\SGCSR");
            
            try {
                outConf = new FileOutputStream(ruta);
                conf.store(outConf, null);
                outConf.close();
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(ArchivoConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) {
                Logger.getLogger(ArchivoConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void guardarParametro(String parametro, String contenido)
    {
        try{
            inConf = new FileInputStream(ruta);
            conf = new Properties();
            conf.load(inConf);
            inConf.close();
            
            outConf = new FileOutputStream(ruta);
            conf.setProperty(parametro, contenido);
            conf.store(outConf, null);
            outConf.close();  
        }catch (FileNotFoundException ex){
            Logger.getLogger(ArchivoConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex){
            Logger.getLogger(ArchivoConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String consultarParametro(String parametro)
    {
        String resultado = null;
        
        try {
            inConf = new FileInputStream(ruta);
            conf = new Properties();
            conf.load(inConf);
            resultado = (String) conf.get(parametro);
            inConf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(resultado == null)
        {
            resultado = "";
        }
        return resultado;
    }
}
