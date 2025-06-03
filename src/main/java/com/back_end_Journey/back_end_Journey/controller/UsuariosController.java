package com.back_end_Journey.back_end_Journey.controller;

import com.back_end_Journey.back_end_Journey.model.Usuarios;
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
    public List<Usuarios> listarUsuarios() {
        return usuariosService.obtenerTodos();
    }

    @PostMapping
    public void crearUsuario(@RequestBody Usuarios usuario) {
        usuariosService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuarios obtenerPorId(@PathVariable Integer id) {
        return usuariosService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public void actualizarUsuario(@PathVariable Integer id, @RequestBody Usuarios datos) {
        usuariosService.actualizarUsuario(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuariosService.eliminarUsuario(id);
    }
}
