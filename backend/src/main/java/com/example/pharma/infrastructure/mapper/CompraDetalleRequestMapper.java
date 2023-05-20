package com.example.pharma.infrastructure.mapper;

import com.example.pharma.domain.entities.compra.CompraDetalle;
import com.example.pharma.infrastructure.api.request.CompraDetalleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompraDetalleRequestMapper extends EntityMapper<CompraDetalleRequest, CompraDetalle>{


}
