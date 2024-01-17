package br.com.luiz.smktsystem.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.dto.EmployeerListDTO;
import br.com.luiz.smktsystem.service.dto.EmployeerLoginDTO;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;

@Mapper
public interface EmployeerMapper {

    EmployeerMapper INSTANCE = Mappers.getMapper(EmployeerMapper.class);

    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "role", ignore = true) 
    Employeer registerToEntity(EmployeerRegisterDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "role", ignore = true) 
    Employeer loginToToEntity(EmployeerLoginDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true) 
    Employeer listToEntity(EmployeerListDTO dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    EmployeerListDTO entityToListDTO(Employeer employeer);
}
