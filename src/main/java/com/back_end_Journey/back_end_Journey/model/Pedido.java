package com.back_end_Journey.back_end_Journey.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Usuario (clave foránea)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuarios;

    // Fecha del pedido
    @Column(nullable = false)
    private LocalDate fecha;

    // Estado del pedido
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Estado estado;

    // Total del pedido
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    // Relación con PedidoDetalle
//    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
//    private List<PedidoDetalle> detalles;

    // Enum para el estado
    public enum Estado {
        pendiente, pagado, enviado, entregado, cancelado
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuario) {
        this.usuarios = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

//    public List<PedidoDetalle> getDetalles() {
//        return detalles;
//    }

//    public void setDetalles(List<PedidoDetalle> detalles) {
//        this.detalles = detalles;
//    }
}