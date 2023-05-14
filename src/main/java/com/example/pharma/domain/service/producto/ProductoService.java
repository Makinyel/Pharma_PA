package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Concentracion;
import com.example.pharma.domain.entities.producto.Marca;
import com.example.pharma.domain.entities.producto.Presentacion;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.infrastructure.api.request.ProductRequest;
import com.example.pharma.infrastructure.repository.producto.ProductoRepository;
import com.example.pharma.shared.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final MarcaService marcaService;
    private final PresentacionService presentacionService;
    private final ConcentracionService concentracionService;

    public Producto save(ProductRequest productRequest) {
        Producto producto = new Producto();
        producto.setNombre(productRequest.getNombre());
        producto.setDescripcion(productRequest.getDescripcion());
        producto.setPrecioCosto(productRequest.getPrecioCosto());
        producto.setPrecioVenta(productRequest.getPrecioVenta());

        // Retrieve the related entities by name
        Marca marca = marcaService.getByName(productRequest.getMarca());
        Presentacion presentacion = presentacionService.getByName(productRequest.getPresentacion());
        Concentracion concentracion = concentracionService.getByName(productRequest.getConcentracion());

        // Set the related entities in the product
        producto.setMarca(marca);
        producto.setPresentacion(presentacion);
        producto.setConcentracion(concentracion);
        return productoRepository.save(producto);
    }

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Producto getById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElseThrow(() -> new NotFoundException("Product with id: " + id + " not found"));
    }

    public List<Producto> getAllByWarehouse(Long idBodega) {
        return productoRepository.findAllByIdBodega(idBodega);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}
