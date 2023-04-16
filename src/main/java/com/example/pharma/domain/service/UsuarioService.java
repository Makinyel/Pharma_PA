package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.Usuario.Rol;
import com.example.pharma.domain.entities.Usuario.Usuario;
import com.example.pharma.infrastructure.repository.RolRepository;
import com.example.pharma.infrastructure.repository.UsuarioRepository;
import com.example.pharma.share.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
@Slf4j
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    public Usuario saveUsuario(Usuario usuario) {
        Rol rol = rolRepository.getById(usuario.getId_rol());
        log.info("ROL==="+rol);
        usuario.setRol(rol);
        log.info("USUARIO ROL="+usuario.getRol());
        return usuarioRepository.save(usuario);
    }
    public Usuario getUsuarioById(String id) {
        Usuario user = usuarioRepository.getById(id);
        if(Objects.isNull(user)){
           new NotFoundException("Usuario " + id + " does not exist");
        }
        return user;
    }
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public void editUsuario(Usuario userDetalle){
        Usuario user = usuarioRepository.getById(userDetalle.getId());

        if (!Objects.isNull(user)) {
            user.setRol(userDetalle.getRol());
            user.setNombre(userDetalle.getNombre());
            user.setApellido(userDetalle.getApellido());
            user.setEmail(userDetalle.getEmail());
            user.setUbicacion(userDetalle.getUbicacion());
            user.setPassword(userDetalle.getPassword());
            user.setTelefono(userDetalle.getTelefono());
            user.setEstado(userDetalle.getEstado());
            user.setId_rol(userDetalle.getId_rol());

            usuarioRepository.save(user);

        }else {
            new NotFoundException("User con el Id " + userDetalle.getId() + " not found");
        }
    }
    public void deleteUser(String id){
        usuarioRepository.deleteById(id);
    }

}
