package com.example.pharma.domain.service.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.infrastructure.repository.producto.ProductoRepository;
import com.example.pharma.share.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ProductoService {
    private ProductoRepository productoRepository;

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    public Producto getProducto(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElseThrow(() -> new NotFoundException("Product with id: " + id + " not found"));
    }

    public List<Producto> findAllByBodega(Long idBodega) {
        return productoRepository.findAllByIdBodega(idBodega);
    }

    // * Returns true or false whether there's stock on a product */
    public boolean verifyStockProduct(Long idProduct, Integer amount) {
        log.info("Verificando stock el producto...");
        Optional<Producto> optionalProduct = productoRepository.findById(idProduct);
        return optionalProduct.isPresent() && optionalProduct.get().getStock() >= amount;
    }
}
