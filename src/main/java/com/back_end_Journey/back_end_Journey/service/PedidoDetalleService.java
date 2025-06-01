package com.back_end_Journey.back_end_Journey.service;
import com.back_end_Journey.back_end_Journey.model.PedidoDetalle;
import com.back_end_Journey.back_end_Journey.repository.iPedidoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoDetalleService implements iPedidoDetalleService{

    private final iPedidoDetalleRepository pedidoDetalleRepository;
    @Autowired
    public PedidoDetalleService(iPedidoDetalleRepository pedidoDetalleRepository) {
        this.pedidoDetalleRepository = pedidoDetalleRepository;
    }

    @Override
    public List<PedidoDetalle> obtenerPedidosDetalle() {
        return pedidoDetalleRepository.findAll();
    }

    @Override
    public PedidoDetalle obtenerPedidoDetallePorId(Long id) {
        return pedidoDetalleRepository.findById(id).orElse(null);
    }

    @Override
    public void savePedidoDetalle(PedidoDetalle pedidoDetalle) {
        pedidoDetalleRepository.save(pedidoDetalle);
    }

    @Override
    public void deletePedidoDetalle(Long id) {
        pedidoDetalleRepository.deleteById(id);
    }

    @Override
    public void updatePedidoDetalle(Long id, PedidoDetalle pedidoDetalleActualizado) {
        PedidoDetalle pedidoDetalleExistente = pedidoDetalleRepository.findById(id).orElse(null);
        if (pedidoDetalleExistente != null){
            pedidoDetalleExistente.setCantidad(pedidoDetalleActualizado.getCantidad());
            pedidoDetalleExistente.setPrecio_unitario(pedidoDetalleActualizado.getPrecio_unitario());
            pedidoDetalleExistente.setPedido(pedidoDetalleActualizado.getPedido());
            pedidoDetalleExistente.setProducto(pedidoDetalleActualizado.getProducto());
            pedidoDetalleRepository.save(pedidoDetalleExistente);
        }else{
            throw new RuntimeException("Pedido Detalle con id: " + id + " no encontrado");
        }
    }
}
