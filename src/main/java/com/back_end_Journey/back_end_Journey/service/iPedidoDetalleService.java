package com.back_end_Journey.back_end_Journey.service;
import com.back_end_Journey.back_end_Journey.model.PedidoDetalle;
import java.util.List;

public interface iPedidoDetalleService {
    List<PedidoDetalle> obtenerPedidosDetalle();
    PedidoDetalle obtenerPedidoDetallePorId(Long id);
    void savePedidoDetalle(PedidoDetalle pedidoDetalle);
    void deletePedidoDetalle(Long id);
    void updatePedidoDetalle(Long id, PedidoDetalle pedidoDetalleActualizado);
}
