package com.back_end_Journey.back_end_Journey.dto;

public class UsuarioLoginResponse {
    private Integer id;
    private String nombre;
    private String correo;
    private String rol;
    private String proveedor;

    // Constructor
    public UsuarioLoginResponse(Integer id, String nombre, String correo, String rol, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.proveedor = proveedor;
    }

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
}

