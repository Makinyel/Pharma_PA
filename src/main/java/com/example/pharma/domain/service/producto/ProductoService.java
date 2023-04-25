package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.infrastructure.repository.producto.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoService {
    private ProductoRepository productoRepository;

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    public Producto getProducto(String id) {
        return productoRepository.getById(id);
    }
}


