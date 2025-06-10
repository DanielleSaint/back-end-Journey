package com.back_end_Journey.back_end_Journey.service;
import com.back_end_Journey.back_end_Journey.model.Usuarios;
import com.back_end_Journey.back_end_Journey.repository.iUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UsuariosService {

    @Autowired
    private iUsuariosRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuarios registrarUsuario(Usuarios usuario) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("El usuario ya existe");
        }
        // Establecer rol por defecto si no se especifica
        if (usuario.getRol() == null) {
            usuario.setRol(Usuarios.Rol.cliente);
        }
        // Guardar el usuario
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public Usuarios loginUsuario(String correo, String contrasena) {
        Usuarios usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null || !usuario.getContrasena().equals(contrasena)) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        return usuario;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios user = usuarioRepository.findByCorreo(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getCorreo(), user.getContrasena(), new ArrayList<>());
    }
}