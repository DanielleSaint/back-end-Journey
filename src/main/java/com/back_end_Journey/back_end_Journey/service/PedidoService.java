package com.back_end_Journey.back_end_Journey.service;

import com.back_end_Journey.back_end_Journey.model.Pedido;
import com.back_end_Journey.back_end_Journey.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService implements IPedidoService{

    private final IPedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> obtenerPedidos(){
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public void savePedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public void updatePedido(Long id, Pedido pedidoActualizado) {
        Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);
        if(pedidoExistente != null){
            pedidoExistente.setUsuarios(pedidoActualizado.getUsuarios());
            pedidoExistente.setFecha(pedidoActualizado.getFecha());
            pedidoExistente.setEstado(pedidoActualizado.getEstado());
            pedidoExistente.setTotal(pedidoActualizado.getTotal());
            pedidoRepository.save(pedidoExistente);
        }else{
            throw new RuntimeException("Pedido con id: " + id + "no fue encontrado.");
        }
    }
}
