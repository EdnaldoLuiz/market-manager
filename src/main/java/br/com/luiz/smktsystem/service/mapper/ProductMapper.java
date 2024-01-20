package br.com.luiz.smktsystem.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.luiz.smktsystem.app.model.Product;
import br.com.luiz.smktsystem.service.dto.ProductListDTO;
import br.com.luiz.smktsystem.service.dto.ProductRegisterDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductRegisterDTO entityToRegisterDTO(Product entity);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "image", ignore = true) 
    Product listToEntity(ProductListDTO dto);

    ProductListDTO entityToListDTO(Product employeer);
    
}
