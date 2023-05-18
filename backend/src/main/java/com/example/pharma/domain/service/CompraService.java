package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.infrastructure.repository.CompraRepository;
import com.example.pharma.infrastructure.repository.producto.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@AllArgsConstructor
@Service
@Slf4j
public class CompraService {
    private CompraRepository compraRepository;
    private ProductoRepository productoRepository;

    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    public List<Producto> getAll(Producto producto){
        return productoRepository.findAll();
    }

    public void save(Compra compra){
        compraRepository.save(compra);

    }

}
