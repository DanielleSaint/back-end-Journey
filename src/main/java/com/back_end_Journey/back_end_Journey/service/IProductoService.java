package com.back_end_Journey.back_end_Journey.service;


import com.back_end_Journey.back_end_Journey.model.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> obtenerTodos();
    Producto obtenerPorId(Long id);
    void guardarProducto(Producto producto);
    void deleteProducto(Long id);
    void updateProducto(Long id, Producto productoActualizado);
}
