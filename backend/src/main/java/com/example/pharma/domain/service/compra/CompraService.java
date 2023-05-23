package com.example.pharma.domain.service.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.service.persona.PersonaService;
import com.example.pharma.domain.service.usuario.UsuarioService;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.infrastructure.api.request.compra.CompraRequest;
import com.example.pharma.infrastructure.repository.compra.CompraRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void editCompra(Compra compra){
        compraRepository.editCompra(
            compra.getCode(),
            compra.getTotal(),
            compra.getTotalWithIva(),
            compra.getIva());
    }
}
