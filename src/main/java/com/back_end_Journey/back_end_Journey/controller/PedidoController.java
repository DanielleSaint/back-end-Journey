package com.back_end_Journey.back_end_Journey.controller;

import com.back_end_Journey.back_end_Journey.model.Pedido;
import com.back_end_Journey.back_end_Journey.model.PedidoDetalle;
import com.back_end_Journey.back_end_Journey.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/obtenerPedido")
    public List<Pedido> listaPedidos(){
        return pedidoService.obtenerPedidos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id){
        return pedidoService.obtenerPedidoPorId(id);
    }

    @PostMapping("/guardarNuevoPedido")
    public ResponseEntity<String> guardarPedido (@RequestBody Pedido pedido){
        pedidoService.savePedido(pedido);
        return ResponseEntity.ok("Pedido agregado con éxito.");
    }
    @DeleteMapping("/borrarPedido/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.ok("Pedido elminiado con éxito.");
    }

    @PutMapping("/editarPedido/{id}")
    public ResponseEntity<String> editarPedido(@PathVariable Long id, @RequestBody Pedido pedidoActualizado){
        pedidoService.updatePedido(id, pedidoActualizado);
        return ResponseEntity.ok("Pedido actualizado con éxito.");
    }
}
