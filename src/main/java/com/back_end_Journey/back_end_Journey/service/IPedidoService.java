package com.back_end_Journey.back_end_Journey.service;

import com.back_end_Journey.back_end_Journey.model.Pedido;
import com.back_end_Journey.back_end_Journey.model.PedidoDetalle;

import java.util.List;

public interface IPedidoService {
    List<Pedido> obtenerPedidos();
    Pedido obtenerPedidoPorId(Long id);
    void savePedido(Pedido pedido);
    void deletePedido(Long id);
    void updatePedido(Long id, Pedido pedidoActualizado);

}
