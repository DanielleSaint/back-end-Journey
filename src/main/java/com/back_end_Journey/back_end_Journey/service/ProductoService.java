package com.back_end_Journey.back_end_Journey.service;


import com.back_end_Journey.back_end_Journey.model.Producto;
import com.back_end_Journey.back_end_Journey.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    private final IProductoRepository productoRepository;

    @Autowired
    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("La publicación con ID " + id + " no existe");
        }
        productoRepository.deleteById(id);
    }

    @Override
    public void updateProducto(Long id, Producto productoActualizado) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La publicación con ID " + id + " no existe"));

        existente.setTitulo(productoActualizado.getTitulo());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setCategoria(productoActualizado.getCategoria());
        existente.setNivel(productoActualizado.getNivel());
        existente.setEdadRecomendada(productoActualizado.getEdadRecomendada());
        existente.setImagenUrl(productoActualizado.getImagenUrl());

        productoRepository.save(existente);
    }
}
