package com.example.pharma.infrastructure.api.controller.usuario;

import com.example.pharma.domain.entities.usuario.Usuario;
import com.example.pharma.domain.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @PutMapping("{id}")
    public void editUsuario(@PathVariable("id") Long id, @RequestBody Usuario user) {
        usuarioService.editUsuario(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUser(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }
}
