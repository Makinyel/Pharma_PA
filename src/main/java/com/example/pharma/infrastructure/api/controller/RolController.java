package com.example.pharma.infrastructure.api.controller;


import com.example.pharma.domain.entities.usuario.Rol;
import com.example.pharma.domain.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rol")

public class RolController {
    private RolService rolService;

    @PostMapping("/post")
    public ResponseEntity<Rol> saveRol(@RequestBody Rol rol){
        return  new ResponseEntity<Rol>(rolService.saveRol(rol), HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Rol>> getAllRol(){
        return ResponseEntity.ok(rolService.getRolAll());
    }

}
