package br.com.luiz.smktsystem.service;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.EmployeerMapper;

public class EmployeerService {
    private EmployeerDAO employeerDAO;

    public EmployeerService(EmployeerDAO employeerDAO) {
        this.employeerDAO = employeerDAO;
    }

    public void registerEmployeer(EmployeerRegisterDTO registerDTO) {
        Employeer employeer = EmployeerMapper.INSTANCE.mapDtoToEntity(registerDTO);
        employeerDAO.createEmployeer(employeer);
    }
}

