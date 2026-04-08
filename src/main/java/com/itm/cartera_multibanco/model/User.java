package com.itm.cartera_multibanco.model;

import jakarta.validation.constraints.NotBlank; // IMPORTANTE: Añade estos imports
import jakarta.validation.constraints.Size;


public class User {

    @NotBlank(message = "La cédula es obligatoria")
    private String cedula;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre_completo;

    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 4, message = "La clave debe tener al menos 4 caracteres")
    private String clave;

    public User() {
        
    }

    public User(String cedula, String nombreCompleto, String username, String clave) {
        this.cedula = cedula;
        this.nombre_completo = nombreCompleto;
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
        return nombre_completo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombre_completo = nombreCompleto;
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