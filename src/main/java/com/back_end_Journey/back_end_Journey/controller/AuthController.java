package com.back_end_Journey.back_end_Journey.controller;
import com.back_end_Journey.back_end_Journey.dto.LoginRequest;
import com.back_end_Journey.back_end_Journey.jwt.JwtUtil;
import com.back_end_Journey.back_end_Journey.model.Usuarios;
import com.back_end_Journey.back_end_Journey.repository.iUsuariosRepository;
import com.back_end_Journey.back_end_Journey.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuariosService usuarioService;

    @Autowired
    private iUsuariosRepository usuarioRepository;

    @PostMapping("/usuarios/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuarios usuario) {
        try {
            Usuarios nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = usuarioService.loadUserByUsername(loginRequest.getCorreo());
            if (userDetails != null && passwordEncoder.matches(loginRequest.getContrasena(), userDetails.getPassword())) {
                Usuarios usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo());
                Map<String, Object> response = new HashMap<>();
                response.put("token", jwtUtil.generateToken(userDetails.getUsername()));
                response.put("nombre", usuario.getNombre());
                response.put("rol", usuario.getRol().name());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(401).body("Credenciales inválidas");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
//        UserDetails userDetails = usuarioService.loadUserByUsername(loginRequest.getCorreo());
//        if (userDetails != null && passwordEncoder.matches(loginRequest.getContrasena(), userDetails.getPassword())) {
//            String token = jwtUtil.generateToken(userDetails.getUsername());
//            return ResponseEntity.ok(token);
//        }
//        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    // Añadir en UsuariosService
    public Usuarios getUsuarioByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @GetMapping("/resource")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getProtectedResource() {
        return ResponseEntity.ok("Este es un recurso protegido!");
    }
}