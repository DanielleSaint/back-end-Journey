package com.back_end_Journey.back_end_Journey.controller;

import com.back_end_Journey.back_end_Journey.model.usuarios;
import com.back_end_Journey.back_end_Journey.repository.iUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private iUsuariosRepository usuariosRepository;

    @GetMapping
    public List<usuarios> listarUsuarios() {
        return usuariosRepository.findAll();
    }

    @PostMapping
    public usuarios crearUsuario(@RequestBody usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public usuarios obtenerPorId(@PathVariable Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public usuarios actualizarUsuario(@PathVariable Integer id, @RequestBody usuarios datos) {
        usuarios usuario = usuariosRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setCorreo(datos.getCorreo());
            usuario.setContrasena(datos.getContrasena());
            usuario.setNombre(datos.getNombre());
            usuario.setTelefono(datos.getTelefono());
            usuario.setRol(datos.getRol());
            return usuariosRepository.save(usuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuariosRepository.deleteById(id);
    }
}
