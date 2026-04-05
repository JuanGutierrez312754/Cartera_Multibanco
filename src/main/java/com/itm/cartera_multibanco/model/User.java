package com.itm.cartera_multibanco.model;

public class User {

    private String cedula;
    private String nombreCompleto;
    private String username;
    private String clave;

    public User() {
    }

    public User(String cedula, String nombreCompleto, String username, String clave) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.clave = clave;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}