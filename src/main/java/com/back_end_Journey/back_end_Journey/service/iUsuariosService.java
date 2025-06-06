package com.back_end_Journey.back_end_Journey.service;
import com.back_end_Journey.back_end_Journey.model.Usuarios;
import java.util.List;

public interface iUsuariosService {
    List<Usuarios> obtenerTodos();
    Usuarios obtenerPorId(Integer id);
    void guardarUsuario(Usuarios usuario);
    void eliminarUsuario(Integer id);
    void actualizarUsuario(Integer id, Usuarios usuarioActualizado);
    Usuarios obtenerPorCorreo(String correo);
    boolean validarCredenciales(String correo, String contrasena);
}
