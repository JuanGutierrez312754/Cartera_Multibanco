package com.itm.cartera_multibanco.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "cedula", length = 20)
    @NotBlank(message = "La cédula es obligatoria")
    private String cedula;
    @JsonProperty("nombre_completo")
    @Column(name = "nombre_completo", nullable = false, length = 100)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre_completo;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    @NotBlank(message = "El username es obligatorio")
    private String username;

    @Column(name = "clave", nullable = false, length = 255)
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