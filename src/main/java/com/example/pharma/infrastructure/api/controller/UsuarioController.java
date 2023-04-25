package com.example.pharma.infrastructure.api.controller;

import com.example.pharma.domain.entities.usuario.Usuario;
import com.example.pharma.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UsuarioController {
    private UsuarioService usuarioService;
    @PostMapping("/Post")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id){
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }
    @PutMapping("/edit")
    public void editUsuario(@RequestBody Usuario user){
        usuarioService.editUsuario(user);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUsuario(@PathVariable String id){
        usuarioService.deleteUser(id);
    }
}
