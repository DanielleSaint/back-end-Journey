package com.back_end_Journey.back_end_Journey.controller;

import com.back_end_Journey.back_end_Journey.model.Producto;
import com.back_end_Journey.back_end_Journey.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
//Ignore
@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}")
    public Producto obtenerPorID(@PathVariable Long id){
        return productoService.obtenerPorId(id);
    }

//    @PostMapping(value = "/guardarNuevoProducto")
//    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
//        productoService.guardarProducto(producto);
//        return ResponseEntity.ok("Producto agregado con éxito");
//    }

    @PostMapping(value = "/guardarNuevoProducto", consumes = {"multipart/form-data"})
    public ResponseEntity<String> guardarProducto(
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("categoria") String categoria,
            @RequestParam("nivel") String nivel,
            @RequestParam("edadRecomendada") String edadRecomendada,
            @RequestParam("imagenUrl") MultipartFile imagen
    ) {
        // Aquí debes crear el objeto Producto y guardar la imagen como corresponda
        Producto producto = new Producto();
        producto.setTitulo(titulo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(BigDecimal.valueOf(precio));
        producto.setCategoria(categoria);
        producto.setNivel(Producto.Nivel.valueOf(nivel));
        producto.setEdadRecomendada(edadRecomendada);
        // Aquí deberías guardar la imagen y setear la URL o el path en el producto

        productoService.guardarProducto(producto);
        return ResponseEntity.ok("Producto agregado con éxito");
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        productoService.updateProducto(id, productoActualizado);
        return ResponseEntity.ok("Producto actualizado con éxito");
    }
}
