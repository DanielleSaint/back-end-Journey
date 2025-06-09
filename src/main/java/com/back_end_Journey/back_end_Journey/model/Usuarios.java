package com.back_end_Journey.back_end_Journey.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank
    private String correo;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;
    @Column(nullable = false)
    @NotBlank
    private String nombre;
    @Column(nullable = true)
    private String telefono;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // Enum Rol incluido dentro de la clase
    public enum Rol {
        admin,
        cliente
    }

    // Constructor vacío
//    public Usuarios() {}

    // Constructor con campos
    public Usuarios(String correo, String contrasena, String nombre, String telefono, Rol rol) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
