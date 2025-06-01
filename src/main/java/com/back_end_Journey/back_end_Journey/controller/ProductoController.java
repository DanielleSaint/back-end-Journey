package com.back_end_Journey.back_end_Journey.controller;

import com.back_end_Journey.back_end_Journey.model.Producto;
import com.back_end_Journey.back_end_Journey.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    @GetMapping("/ObtenerProductos")
    public List<Producto> listaProductos(){
        return productoService.obtenerTodos();
    }
}
