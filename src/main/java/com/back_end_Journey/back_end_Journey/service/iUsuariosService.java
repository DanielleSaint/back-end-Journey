package com.back_end_Journey.back_end_Journey.service;

import com.back_end_Journey.back_end_Journey.model.usuarios;
import java.util.List;

public interface iUsuariosService {
    List<usuarios> obtenerTodos();
    usuarios obtenerPorId(Integer id);
    void guardarUsuario(usuarios usuario);
    void eliminarUsuario(Integer id);
    void actualizarUsuario(Integer id, usuarios usuarioActualizado);
    usuarios obtenerPorCorreo(String correo);
}
