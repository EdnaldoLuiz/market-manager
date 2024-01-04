package br.com.luiz.smktsystem.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    Product registerToEntity(ProductRegisterDTO dto);
    
}
