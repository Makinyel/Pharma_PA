package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.infrastructure.repository.producto.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Producto getProducto(String id) {
        return productoRepository.getById(id);
    }
    public List<Producto> findAllByBodega(String idBodega) {
        return productoRepository.findAllByIdBodega(idBodega);
    }

    //Con Este Metodo verificamos la cantidad de productos solicitados no pasen la cantidad de stocks
    public boolean verifyStockProduct(String idProduct, Integer amount){
        log.info("Verificando stock el producto...");
        var productStockActual = productoRepository.getById(idProduct);

        if(productStockActual.getStock() >= amount )
            return true;
        else
            return false;
    }


}


