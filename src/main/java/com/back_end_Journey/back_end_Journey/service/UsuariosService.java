package com.back_end_Journey.back_end_Journey.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.back_end_Journey.back_end_Journey.model.Usuarios;
import com.back_end_Journey.back_end_Journey.repository.iUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuariosService implements iUsuariosService {

    private final iUsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(iUsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; //Encriptar contraseña

    @Override
    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios obtenerPorId(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarUsuario(Usuarios usuario) {
        if (usuariosRepository.findByCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }
        if ("google".equals(usuario.getProveedor())) {
            usuario.setContrasena(null);
        } else {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }
        usuariosRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        if (!usuariosRepository.existsById(id)) {
            throw new RuntimeException("El usuario con ID " + id + " no existe");
        }
        usuariosRepository.deleteById(id);
    }

    @Override
    public void actualizarUsuario(Integer id, Usuarios usuarioActualizado) {
        Usuarios existente = usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario con ID " + id + " no existe"));

        existente.setCorreo(usuarioActualizado.getCorreo());
        if (usuarioActualizado.getContrasena() != null && !usuarioActualizado.getContrasena().isBlank()) {
            existente.setContrasena(passwordEncoder.encode(usuarioActualizado.getContrasena()));
        }
        existente.setNombre(usuarioActualizado.getNombre());
        existente.setTelefono(usuarioActualizado.getTelefono());
        existente.setRol(usuarioActualizado.getRol());

        usuariosRepository.save(existente);
    }

    @Override
    public Usuarios obtenerPorCorreo(String correo) {
        return usuariosRepository.findByCorreo(correo);
    }

    @Override
    public boolean validarCredenciales(String correo, String contrasena) {
        Usuarios usuario = usuariosRepository.findByCorreo(correo);
        if (usuario == null || usuario.getContrasena() == null) {
            return false;
        }
        return passwordEncoder.matches(contrasena, usuario.getContrasena());
    }
}
