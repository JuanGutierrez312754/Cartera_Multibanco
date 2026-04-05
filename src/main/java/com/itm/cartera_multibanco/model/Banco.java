package com.itm.cartera_multibanco.model;

public class Banco {

    private Integer id;
    private String nombre;

    public Banco() {
    }

    public Banco(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}