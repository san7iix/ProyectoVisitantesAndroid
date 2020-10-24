package com.example.visitantes.Modelos;

import java.util.Date;

public class Visitante {

    private Integer id;
    private String nombre;
    private String apartamento;
    private String tipo_visitante;
    private String fecha;

    public Visitante(Integer id, String nombre, String apartamento, String tipo_visitante, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.apartamento = apartamento;
        this.tipo_visitante = tipo_visitante;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getTipo_visitante() {
        return tipo_visitante;
    }

    public void setTipo_visitante(String tipo_visitante) {
        this.tipo_visitante = tipo_visitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
