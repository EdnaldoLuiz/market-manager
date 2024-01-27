package br.com.luiz.smktsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.service.dto.EmployeerListDTO;
import br.com.luiz.smktsystem.service.dto.EmployeerLoginDTO;
import br.com.luiz.smktsystem.service.mapper.EmployeerMapper;

public class EmployeerService {

    private EmployeerDAO employeerDAO;
    private Employeer authenticatedUser;

    public EmployeerService(EmployeerDAO employeerDAO) {
        this.employeerDAO = employeerDAO;
    }

    public void registerEmployeer(Employeer employeer) {
        employeerDAO.createEmployeer(employeer);
    }

    public List<EmployeerListDTO> listEmployeers() {
        return employeerDAO.getAllEmployeers().stream()
                .map(employeer -> EmployeerMapper.INSTANCE.entityToListDTO(employeer))
                .collect(Collectors.toList());
    }

    public Employeer loginEmployeer(EmployeerLoginDTO loginDTO) {
        Employeer employeer = EmployeerMapper.INSTANCE.loginToToEntity(loginDTO);
        Employeer storedEmployeer = employeerDAO.findEmployeerByEmail(employeer.getEmail());
        boolean validPassword = storedEmployeer != null && storedEmployeer.getPassword().equals(employeer.getPassword());
        if (validPassword) {
            authenticatedUser = storedEmployeer;
            return storedEmployeer;
        } 
        return null;
    }

    public Employeer getAuthenticatedUser() {
        return authenticatedUser;
    }

    public boolean isEmailOnDatabase(String email) {
        return employeerDAO.isEmailExists(email);
    }
}

