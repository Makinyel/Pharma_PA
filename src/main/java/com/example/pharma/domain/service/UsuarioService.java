package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.usuario.Usuario;
import com.example.pharma.infrastructure.repository.UsuarioRepository;
import com.example.pharma.share.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Usuario " + id + " does not exist"));
    }

    public List<Usuario> getAllUsuarios() {
        return userRepository.findAll();
    }

    public void editUsuario(Usuario user) {
        userRepository
                .findById(user.getId())
                .orElseThrow(() -> new NotFoundException("Usuario " + user.getId() + " does not exist"));
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}