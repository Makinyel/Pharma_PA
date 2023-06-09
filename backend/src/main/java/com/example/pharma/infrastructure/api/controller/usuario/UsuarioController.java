package com.example.pharma.infrastructure.api.controller.usuario;

import com.example.pharma.domain.entities.user.User;
import com.example.pharma.domain.service.usuario.UsuarioService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsuarioController {

  private UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<User> saveUsuario(@RequestBody User user) {
    return new ResponseEntity<>(usuarioService.saveUsuario(user), HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<User> getUsuarioById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(usuarioService.getUsuarioById(id));
  }

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(usuarioService.getAllUsuarios());
  }

  @PutMapping("{id}")
  public void editUsuario(@PathVariable("id") Long id, @RequestBody User user) {
    usuarioService.editUsuario(id, user);
  }

  @DeleteMapping("{id}")
  public void deleteUsuario(@PathVariable("id") Long id) {
    usuarioService.deleteUser(id);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
    return ResponseEntity.ok(usuarioService.findByEmail(email));
  }
}
