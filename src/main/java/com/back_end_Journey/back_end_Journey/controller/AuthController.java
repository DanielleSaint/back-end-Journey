package com.back_end_Journey.back_end_Journey.controller;
import com.back_end_Journey.back_end_Journey.dto.GoogleLoginRequest;
import com.back_end_Journey.back_end_Journey.dto.LoginRequest;
import com.back_end_Journey.back_end_Journey.jwt.JwtUtil;
import com.back_end_Journey.back_end_Journey.model.Usuarios;
import com.back_end_Journey.back_end_Journey.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usuarios")
    public List<Usuarios> listarUsuarios() {
        return usuariosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Usuarios obtenerPorId(@PathVariable Integer id) {
        return usuariosService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuarios datos) {
        usuariosService.actualizarUsuario(id, datos);
        return ResponseEntity.ok("Usuario actualizado con éxito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        usuariosService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuarios user) {
        usuariosService.guardarUsuario(user);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuarios user) {
        UserDetails userDetails = usuariosService.loadUserByUsername(user.getCorreo());
        if (userDetails != null && passwordEncoder.matches(user.getContrasena(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    @PostMapping("/login-google")
    public ResponseEntity<String> loginGoogle(@RequestBody GoogleLoginRequest request) {
        UserDetails userDetails = usuariosService.loadUserByUsername(request.getCorreo());
//        if (userDetails == null) {
//            Usuarios nuevo = new Usuarios();
//            request.getCorreo()),
////            nuevo.setNombre(request.getNombre());
//            nuevo.setProveedor("google"),
//            nuevo.setRol(Usuarios.Rol.cliente),
//            nuevo.setTelefono("0000000000"),
//            nuevo.setContrasena(request.getContrasena())
//
//            usuariosService.guardarUsuario(nuevo);
//
//            String token = jwtUtil.generateToken(nuevo.getCorreo());
//            return ResponseEntity.ok(token);
////            return ResponseEntity.ok("Usuario registrado con Google");
//        }
        if (userDetails != null && passwordEncoder.matches(request.getContrasena(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
//        return ResponseEntity.ok("Login con Google exitoso");
    }

    @GetMapping("/resource")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getProtectedResource() {
        return ResponseEntity.ok("Este es un recurso protegido!");
    }
}
