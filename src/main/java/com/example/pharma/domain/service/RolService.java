package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.Usuario.Rol;
import com.example.pharma.infrastructure.repository.RolRepository;
import com.example.pharma.share.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RolService {
    private RolRepository rolRepository;
    public Rol saveRol(Rol rol){
        return rolRepository.save(rol);
    }

    public Rol getRolById(String id){
        Rol rol = rolRepository.getById(id);
        if (Objects.isNull(rol)){
            new NotFoundException("Rol " + id + " not found");
        }
        return rol;
    }
    public List<Rol> getRolAll(){
        return rolRepository.findAll();
    }

    public void editRol(Rol roldetalles){
        Rol rol = rolRepository.getById(roldetalles.getId());
        if (!Objects.isNull(rol)){
            rol.setTiporol(roldetalles.getTiporol());
            rol.setId(roldetalles.getId());
        }
        new NotFoundException("Rol " + roldetalles.getId()+ " not found");
    }

    public void deleteRol(Rol rol){
        rolRepository.deleteById(rol.getId());
    }

}
