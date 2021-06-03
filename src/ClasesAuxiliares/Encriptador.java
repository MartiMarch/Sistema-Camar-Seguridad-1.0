package ClasesAuxiliares;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encriptador {

    private SecretKeySpec secreto;
    private byte[] clave;
    private String digitos = "AaEeIiOoUuBbCcDdFfGgHhJjKkLlMmNnÑnPpQqRrSsTtVvWwXxYyZz1234567890?¿<>*-+=[]{}:,;_";
    private static int LONGITUD_SECRETO = 16;
    
    public Encriptador(){}

    /*
    public void crearClave(String clave) {
        MessageDigest sha = null;
        try {
            this.clave = clave.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            this.clave = sha.digest(this.clave);
            this.clave = Arrays.copyOf(this.clave, 16);
            secreto = new SecretKeySpec(this.clave, "AES");
        } 
        catch(NoSuchAlgorithmException e) {e.printStackTrace();} 
        catch(UnsupportedEncodingException e) {e.printStackTrace();}
    }
    */

    public String encriptar(String texto, String clave) {
        String salida = "";
        try {
            clave = getSecreto(clave);
            //crearClave(clave);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //cipher.init(Cipher.ENCRYPT_MODE, secreto);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(clave.getBytes("utf-8"), "AES"));
            salida = Base64.getEncoder().encodeToString(cipher.doFinal(texto.getBytes("UTF-8")));
        } catch (Exception e) {e.printStackTrace();}
        
        return salida;
    }

    public String desencriptar(String texto, String clave) {
        String salida = "";
        try {
            clave = getSecreto(clave);
            //crearClave(clave);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            //cipher.init(Cipher.DECRYPT_MODE, secreto);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(clave.getBytes("utf-8"), "AES"));
            salida = new String(cipher.doFinal(Base64.getDecoder().decode(texto)));
        } 
        catch (Exception e) {e.printStackTrace();}
        
        return salida;
    }
    
    public ArrayList<String> generarHash(String clave)
    {
        ArrayList<String> datos = new ArrayList<>();
        String hash = "";
        String salt = "";
        SecureRandom rand = new SecureRandom();
        for(int i = 0; i < 16; ++i)
        {
            int posicion = rand.nextInt(digitos.length());
            salt += digitos.charAt(posicion);
        }
        clave += salt;
        hash = encriptar(clave, clave);
        datos.add(hash);
        datos.add(salt);
        
        return datos;
    }
    
    public String getSecreto(String clave)
    {
        if(clave.length() < LONGITUD_SECRETO)
        {
            int numPad = LONGITUD_SECRETO - clave.length();

            for (int i = 0; i < numPad; i++)
            {
              clave += "0";
            }
        }
        else if (clave.length() > LONGITUD_SECRETO)
        {
            clave =  clave.substring(0, LONGITUD_SECRETO);
        }

        return clave;
    }
}