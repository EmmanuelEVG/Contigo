package com.dospuntosp.contigo;

public class Horario {

    protected String id;
    protected String nombreClase;
    protected String sala;
    protected String horaIni;
    protected String horaTerm;

    public Horario() {
    }

    public Horario(String id, String nombreClase, String sala, String horaIni, String horaTerm, String nombreProfesor, String dia) {
        this.id = id;
        this.nombreClase = nombreClase;
        this.sala = sala;
        this.horaIni = horaIni;
        this.horaTerm = horaTerm;
        this.nombreProfesor = nombreProfesor;
        this.dia = dia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraTerm() {
        return horaTerm;
    }

    public void setHoraTerm(String horaTerm) {
        this.horaTerm = horaTerm;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    protected String nombreProfesor;
    protected String dia;
}
