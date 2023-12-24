package br.com.luiz.smktsystem.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;

@Mapper
public interface EmployeerMapper {

    EmployeerMapper INSTANCE = Mappers.getMapper(EmployeerMapper.class);

    @Mapping(target = "id", ignore = true) 
    Employeer mapDtoToEntity(EmployeerRegisterDTO dto);
}
