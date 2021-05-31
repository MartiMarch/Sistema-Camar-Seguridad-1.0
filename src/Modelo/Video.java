package Modelo;

public class Video {
    private String id;
    private int año;
    private int dia;
    private int mes;
    private int hora;
    private int minutos;
    private int segundos;
        
    public Video(String id, int año, int dia, int mes, int segundos, int hora, int minutos)
    {
        this.id = id;
        this.año = año;
        this.dia = dia;
        this.mes = mes;
        this.hora = hora;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public String getId() {
        return id;
    }

    public int getAño() {
        return año;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }
    
    public int getSegundos() {
        return segundos;
    }
}
