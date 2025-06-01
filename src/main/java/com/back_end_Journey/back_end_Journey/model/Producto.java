package com.back_end_Journey.back_end_Journey.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 100, message = "El título no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio debe tener hasta 8 dígitos enteros y 2 decimales")
    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;

    @Size(max = 50, message = "La categoría no puede tener más de 50 caracteres")
    @Column(length = 50)
    private String categoria;

    //Y con esta línea en el atributo:
    // Le estamos diciendo a JPA que guarde el valor del enum como texto
    // (por ejemplo, "leve", "moderado", "avanzado") en la base de datos, y no como
    // su posición (ordinal).

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Nivel nivel;

    @Size(max = 20, message = "La edad recomendada no puede tener más de 20 caracteres")
    @Column(name = "edad_recomendada", length = 20)
    private String edadRecomendada;

    @Size(max = 255, message = "La URL de la imagen no puede tener más de 255 caracteres")
    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

//    En Java, para representar ese tipo de valores fijos o limitados (como un ENUM en SQL),
//    lo más adecuado es usar un enum en Java. Por eso te lo incluí así:

    public enum Nivel {
        leve, moderado, avanzado
    }

    public Producto(Long id, String titulo, String descripcion, Double precio, String categoria, Nivel nivel, String edadRecomendada, String imagenUrl) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.nivel = nivel;
        this.edadRecomendada = edadRecomendada;
        this.imagenUrl = imagenUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEdadRecomendada() {
        return edadRecomendada;
    }

    public void setEdadRecomendada(String edadRecomendada) {
        this.edadRecomendada = edadRecomendada;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}

