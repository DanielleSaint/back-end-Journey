package com.back_end_Journey.back_end_Journey.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String correo;
    private String contrasena;
    private String nombre;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Enum Rol incluido dentro de la clase
    public enum Rol {
        admin,
        cliente
    }

    // Constructor vacío
    public Usuarios() {}

    // Constructor con campos
    public Usuarios(String correo, String contrasena, String nombre, String telefono, Rol rol) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
