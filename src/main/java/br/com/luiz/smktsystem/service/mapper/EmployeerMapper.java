package br.com.luiz.smktsystem.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.dto.EmployeerLoginDTO;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;

@Mapper
public interface EmployeerMapper {

    EmployeerMapper INSTANCE = Mappers.getMapper(EmployeerMapper.class);

    @Mapping(target = "id", ignore = true) 
    Employeer registerToEntity(EmployeerRegisterDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    @Mapping(target = "name", ignore = true)
    Employeer loginToToEntity(EmployeerLoginDTO dto);
}
