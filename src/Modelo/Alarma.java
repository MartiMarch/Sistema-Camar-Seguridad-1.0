package Modelo;

public class Alarma {
    private String id;
    private int año;
    private int mes;
    private int dia;
    private int minuto;
    private int segundos;
    
    public Alarma(String id, int año, int mes, int dia, int minuto, int segundos)
    {
        this.id = id;
        this.año = año;
        this.mes = mes;
        this.dia = dia;
        this.minuto = minuto;
        this.segundos = segundos;
    }
}
