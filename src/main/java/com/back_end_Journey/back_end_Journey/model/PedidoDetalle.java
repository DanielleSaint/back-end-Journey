package com.back_end_Journey.back_end_Journey.model;
import jakarta.persistence.*;

@Entity
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int cantidad;
    @Column(nullable = false)
    private double precio_unitario;
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    // Esto debe ir en Pedido @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    //Esto debe ir en Producto @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private Producto producto;

    public PedidoDetalle() {}

    public PedidoDetalle(Long id, int cantidad, double precio_unitario, Pedido pedido, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.pedido = pedido;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
