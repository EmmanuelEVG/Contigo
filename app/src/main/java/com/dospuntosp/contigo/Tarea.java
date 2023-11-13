package com.dospuntosp.contigo;

public class Tarea {
    protected String id;
    protected String nombreClase;
    protected String prioridad;
    protected String horaEntrega;
    protected String diaEntrega;
    protected String descripcion;
    protected String titulo;  // Agregada la propiedad titulo
    protected String pendiente;  // Agregada la propiedad pendiente

    public Tarea(String id, String nombreClase, String prioridad, String horaEntrega, String diaEntrega, String descripcion, String titulo, String pendiente) {
        this.id = id;
        this.nombreClase = nombreClase;
        this.prioridad = prioridad;
        this.horaEntrega = horaEntrega;
        this.diaEntrega = diaEntrega;
        this.descripcion = descripcion;
        this.titulo = titulo;  // Asignación de titulo
        this.pendiente = pendiente;  // Asignación de pendiente
    }

    public Tarea() {
    }

    // Métodos getter y setter para las propiedades

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

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(String diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }
}
