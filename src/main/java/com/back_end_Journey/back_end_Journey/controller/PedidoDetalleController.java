package com.back_end_Journey.back_end_Journey.controller;
import com.back_end_Journey.back_end_Journey.model.PedidoDetalle;
import com.back_end_Journey.back_end_Journey.service.PedidoDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos_detalle")
public class PedidoDetalleController {
    private final PedidoDetalleService pedidoDetalleService;

    public PedidoDetalleController(PedidoDetalleService pedidoDetalleService) {
        this.pedidoDetalleService = pedidoDetalleService;
    }
    @GetMapping("/obtenerDetalles")
    public List<PedidoDetalle> listaDetalles() {
        return pedidoDetalleService.obtenerPedidosDetalle();
    }

    @GetMapping("/{id}")
    public PedidoDetalle obtenerDetallePorId(@PathVariable Long id) {
        return pedidoDetalleService.obtenerPedidoDetallePorId(id);
    }

    @PostMapping("/guardarNuevoDetalle")
    public ResponseEntity<String> guardarDetalle(@RequestBody PedidoDetalle pedidoDetalle) {
        pedidoDetalleService.savePedidoDetalle(pedidoDetalle);
        return ResponseEntity.ok("PedidoDetalle agregado con éxito");
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarDetalle(@PathVariable Long id) {
        pedidoDetalleService.deletePedidoDetalle(id);
        return ResponseEntity.ok("PedidoDetalle eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarDetalle(@PathVariable Long id, @RequestBody PedidoDetalle detalleActualizado) {
        pedidoDetalleService.updatePedidoDetalle(id, detalleActualizado);
        return ResponseEntity.ok("PedidoDetalle actualizado con éxito");
    }
}

