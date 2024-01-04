package br.com.luiz.smktsystem.service;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.service.dto.EmployeerLoginDTO;
import br.com.luiz.smktsystem.service.dto.EmployeerRegisterDTO;
import br.com.luiz.smktsystem.service.mapper.EmployeerMapper;

public class EmployeerService {

    private EmployeerDAO employeerDAO;

    public EmployeerService(EmployeerDAO employeerDAO) {
        this.employeerDAO = employeerDAO;
    }

    public void registerEmployeer(EmployeerRegisterDTO registerDTO) {
        Employeer employeer = EmployeerMapper.INSTANCE.registerToEntity(registerDTO);
        employeerDAO.createEmployeer(employeer);
    }

    public Employeer loginEmployeer(EmployeerLoginDTO loginDTO) {
        Employeer employeer = EmployeerMapper.INSTANCE.loginToToEntity(loginDTO);
        Employeer storedEmployeer = employeerDAO.findEmployeerByEmail(employeer.getEmail());
        boolean validPassword = storedEmployeer != null && storedEmployeer.getPassword().equals(employeer.getPassword());
        if (validPassword) {
            return storedEmployeer;
        } 
        return null;
    }

    public boolean isEmailOnDatabase(String email) {
        return employeerDAO.isEmailExists(email);
    }
}

