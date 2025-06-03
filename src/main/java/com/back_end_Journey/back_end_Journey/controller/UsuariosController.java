package com.back_end_Journey.back_end_Journey.controller;

import com.back_end_Journey.back_end_Journey.model.usuarios;
import com.back_end_Journey.back_end_Journey.service.iUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private iUsuariosService usuariosService;

    @GetMapping
    public List<usuarios> listarUsuarios() {
        return usuariosService.obtenerTodos();
    }

    @PostMapping
    public void crearUsuario(@RequestBody usuarios usuario) {
        usuariosService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public usuarios obtenerPorId(@PathVariable Integer id) {
        return usuariosService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public void actualizarUsuario(@PathVariable Integer id, @RequestBody usuarios datos) {
        usuariosService.actualizarUsuario(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuariosService.eliminarUsuario(id);
    }
}
