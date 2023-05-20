package com.example.pharma.domain.service.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.compra.CompraDetalle;
import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.service.PersonaService;
import com.example.pharma.domain.service.UsuarioService;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.infrastructure.api.request.CompraRequest;
import com.example.pharma.infrastructure.repository.PersonaRepository;
import com.example.pharma.infrastructure.repository.UsuarioRepository;
import com.example.pharma.infrastructure.repository.compra.CompraRepository;
import com.example.pharma.infrastructure.repository.producto.ProductoRepository;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@AllArgsConstructor
@Service
@Slf4j
public class CompraService {
    private CompraRepository compraRepository;
    private CompraDetalleService compraDetalleService;
    private PersonaService personaService;
    private UsuarioService usuarioService;
    private ProductoService productoService;

    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    public List<Producto> getAll(Producto producto){
        return productoService.getAll();
    }

    public Compra getById(Long id){
        return compraRepository.getById(id);
    }


    public Compra save(CompraRequest compraRequest){

        Compra compra = new Compra();

        compra.setIva(compraRequest.getIva());
        compra.setFecha(LocalDate.now());
        compra.setCodeFactura(compraRequest.getCodeFactura());
        compra.setTotal(0);
        compra.setTotalWithIva(0);

        var user = usuarioService.findByEmail(compraRequest.getUsuario());
        var person = personaService.findByName(compraRequest.getProveedor());

        compra.setUsuario(user);
        compra.setProveedor(person);

        return compraRepository.save(compra);
    }
    public void editCompra(Compra compra){
        compraRepository.editCompra(
            compra.getTotal(),
            compra.getTotalWithIva(),
            compra.getIva());
    }

}
