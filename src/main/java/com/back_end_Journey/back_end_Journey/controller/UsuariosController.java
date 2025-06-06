package com.back_end_Journey.back_end_Journey.controller;
import com.back_end_Journey.back_end_Journey.JwtUtil;
import com.back_end_Journey.back_end_Journey.dto.GoogleLoginRequest;
import com.back_end_Journey.back_end_Journey.dto.LoginRequest;
import com.back_end_Journey.back_end_Journey.dto.UsuarioLoginResponse;
import com.back_end_Journey.back_end_Journey.model.Usuarios;
import com.back_end_Journey.back_end_Journey.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private UsuariosService usuariosService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        UserDetails userDetails = usuariosService.loadUserByUsername(request.getCorreo());
        if (userDetails != null && passwordEncoder.matches(request.getContrasena(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
//        return ResponseEntity.ok("Login exitoso");
    }
    //Login
//    @PostMapping("/login")
//    public ResponseEntity<UsuarioLoginResponse> login(@RequestBody LoginRequest request) {
//        try {
//            UserDetails userDetails = usuariosService.loadUserByUsername(request.getCorreo());
//            if (userDetails != null && passwordEncoder.matches(request.getContrasena(), userDetails.getPassword())) {
//                String token = jwtUtil.generateToken(userDetails.getUsername());
//
//                // Obtener el usuario completo
//                Usuarios usuario = usuariosService.obtenerPorCorreo(request.getCorreo());
//
//                // Crear respuesta con token
//                UsuarioLoginResponse response = new UsuarioLoginResponse(
//                        usuario.getId(),
//                        usuario.getNombre(),
//                        usuario.getCorreo(),
//                        usuario.getRol().toString(),
//                        usuario.getProveedor(),
//                        token // Incluir el token JWT en la respuesta
//                );
//
//                return ResponseEntity.ok(response);
//            }
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @PostMapping("/login-google")
    public ResponseEntity<String> loginGoogle(@RequestBody GoogleLoginRequest request) {
        UserDetails userDetails = usuariosService.loadUserByUsername(request.getCorreo());
        if (userDetails == null) {
            Usuarios nuevo = new Usuarios();
            nuevo.setCorreo(request.getCorreo());
            nuevo.setNombre(request.getNombre());
            nuevo.setProveedor("google");
            nuevo.setRol(Usuarios.Rol.cliente);
            nuevo.setTelefono("0000000000");
            nuevo.setContrasena(request.getContrasena());
            usuariosService.guardarUsuario(nuevo);

            String token = jwtUtil.generateToken(nuevo.getCorreo());
            return ResponseEntity.ok(token);
//            return ResponseEntity.ok("Usuario registrado con Google");
        }
        if (userDetails != null && passwordEncoder.matches(request.getContrasena(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
//        return ResponseEntity.ok("Login con Google exitoso");
    }
//    @PostMapping("/login-google")
//    public ResponseEntity<UsuarioLoginResponse> loginGoogle(@RequestBody GoogleLoginRequest request) {
//        try {
//            Usuarios usuario = usuariosService.obtenerPorCorreo(request.getCorreo());
//
//            if (usuario == null) {
//                // Si el usuario no existe, lo creamos
//                Usuarios nuevo = new Usuarios();
//                nuevo.setCorreo(request.getCorreo());
//                nuevo.setNombre(request.getNombre());
//                nuevo.setProveedor("google");
//                nuevo.setRol(Usuarios.Rol.cliente);
//                nuevo.setTelefono("0000000000");
//                // Generar una contraseña aleatoria segura si no se proporciona una
//                String password = request.getContrasena() != null && !request.getContrasena().isEmpty()
//                        ? request.getContrasena()
//                        : generateRandomPassword();
//                nuevo.setContrasena(password); // El servicio se encargará de encriptarla
//
//                usuariosService.guardarUsuario(nuevo);
//                // Generar token JWT
//                String token = jwtUtil.generateToken(nuevo.getCorreo());
//                // Crear respuesta con token
//                UsuarioLoginResponse response = new UsuarioLoginResponse(
//                        nuevo.getId(),
//                        nuevo.getNombre(),
//                        nuevo.getCorreo(),
//                        nuevo.getRol().toString(),
//                        nuevo.getProveedor(),
//                        token // Incluir el token JWT en la respuesta
//                );
//                return ResponseEntity.ok(response);
//            }
//            // Si el usuario ya existe, generamos un token JWT
//            String token = jwtUtil.generateToken(usuario.getCorreo());
//            // Crear respuesta con token
//            UsuarioLoginResponse response = new UsuarioLoginResponse(
//                    usuario.getId(),
//                    usuario.getNombre(),
//                    usuario.getCorreo(),
//                    usuario.getRol().toString(),
//                    usuario.getProveedor(),
//                    token // Incluir el token JWT en la respuesta
//            );
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//     //Mét0do para generar una contraseña aleatoria segura
//    private String generateRandomPassword() {
//        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
//        StringBuilder sb = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < 16; i++) {
//            sb.append(chars.charAt(random.nextInt(chars.length())));
//        }
//        return sb.toString();
//    }
}
