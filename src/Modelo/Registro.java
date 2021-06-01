package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Registro {
    private HashMap<String, ArrayList<String>> registro = new HashMap<String, ArrayList<String>>();
    
    public Registro(){}
    
    public void addAlarma(String id)
    {
        id = id.replaceAll("-", "/");
        id = id.replaceAll("_", ":");
        ArrayList<String> nombresClientes = new ArrayList();
        registro.put(id, nombresClientes);
    }
    
    public void addClinete(String id, String nombreCliente)
    {
        id = id.replaceAll("-", "/");
        id = id.replaceAll("_", ":");
        Alarma alarma = new Alarma(id);
        registro.get(id).add(nombreCliente);
    }
    
    public HashMap<String, ArrayList<String>> getHashMap()
    {
        return registro;
    }
}
