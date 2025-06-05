package com.back_end_Journey.back_end_Journey.controller;
import com.back_end_Journey.back_end_Journey.dto.GoogleLoginRequest;
import com.back_end_Journey.back_end_Journey.dto.LoginRequest;
import com.back_end_Journey.back_end_Journey.dto.UsuarioLoginResponse;
import com.back_end_Journey.back_end_Journey.model.Usuarios;
import com.back_end_Journey.back_end_Journey.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private UsuariosService usuariosService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuariosController(UsuariosService usuariosService, BCryptPasswordEncoder passwordEncoder) {
        this.usuariosService = usuariosService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Usuarios> listarUsuarios() {
        return usuariosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Usuarios obtenerPorId(@PathVariable Integer id) {
        return usuariosService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody Usuarios usuario) {
        usuariosService.guardarUsuario(usuario);
        return ResponseEntity.ok("Usuario creado con éxito");
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

    //Login
    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponse> login(@RequestBody LoginRequest request) {
        Usuarios usuario = usuariosService.obtenerPorCorreo(request.getCorreo());

        if (usuario == null || !passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioLoginResponse response = new UsuarioLoginResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol().toString(),
                usuario.getProveedor()
        );
        return ResponseEntity.ok(response);
//        return ResponseEntity.ok("Login exitoso");
    }
    @PostMapping("/login-google")
    public ResponseEntity<UsuarioLoginResponse> loginGoogle(@RequestBody GoogleLoginRequest request) {
        Usuarios usuario = usuariosService.obtenerPorCorreo(request.getCorreo());

        if (usuario == null) {
            Usuarios nuevo = new Usuarios();
            nuevo.setCorreo(request.getCorreo());
            nuevo.setNombre(request.getNombre());
            nuevo.setProveedor("google");
            nuevo.setRol(Usuarios.Rol.cliente);
            nuevo.setTelefono("0000000000");
            usuariosService.guardarUsuario(nuevo);

            UsuarioLoginResponse response = new UsuarioLoginResponse(
                    nuevo.getId(),
                    nuevo.getNombre(),
                    nuevo.getCorreo(),
                    nuevo.getRol().toString(),
                    nuevo.getProveedor()
            );
            return ResponseEntity.ok(response);
//            return ResponseEntity.ok("Usuario registrado con Google");
        }
        // Si ya existe
        UsuarioLoginResponse response = new UsuarioLoginResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol().toString(),
                usuario.getProveedor()
        );
        return ResponseEntity.ok(response);
//        return ResponseEntity.ok("Login con Google exitoso");
    }
}
