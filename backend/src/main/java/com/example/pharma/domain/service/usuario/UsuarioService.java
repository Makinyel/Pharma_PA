package com.example.pharma.domain.service.usuario;

import com.example.pharma.domain.entities.user.User;
import com.example.pharma.infrastructure.repository.usuario.UsuarioRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UsuarioService {

  private UsuarioRepository userRepository;

  public User saveUsuario(User user) {
    return userRepository.save(user);
  }

  public User getUsuarioById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(
            "User " + id + " does not exist"));
  }

  public List<User> getAllUsuarios() {
    return userRepository.findAll();
  }

  public void editUsuario(Long id, User user) {
    User existingUser = userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("User " + id + " does not exist"));

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

  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() ->
    new NotFoundException("User Not Found: " + email));
  }
}