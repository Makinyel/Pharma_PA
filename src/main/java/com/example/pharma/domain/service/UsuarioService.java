package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.usuario.Usuario;
import com.example.pharma.infrastructure.repository.UsuarioRepository;
import com.example.pharma.shared.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository userRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return userRepository.save(usuario);
    }

    public Usuario getUsuarioById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Usuario " + id + " does not exist"));
    }

    public List<Usuario> getAllUsuarios() {
        return userRepository.findAll();
    }

    public void editUsuario(Long id, Usuario user) {
        Usuario existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario " + id + " does not exist"));

        existingUser.setId(user.getId());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        existingUser.setRole(user.getRole());

        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Usuario findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}