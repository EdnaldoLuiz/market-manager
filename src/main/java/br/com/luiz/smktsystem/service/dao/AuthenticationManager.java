package br.com.luiz.smktsystem.service.dao;

import br.com.luiz.smktsystem.app.model.Employeer;
import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.dto.EmployeerLoginDTO;

public class AuthenticationManager {
    private EmployeerService employeerService;
    private AuthenticationListener authenticationListener;

    public AuthenticationManager(EmployeerService employeerService, AuthenticationListener listener) {
        this.employeerService = employeerService;
        this.authenticationListener = listener;
    }

    public void authenticate(String email, String password) {
        EmployeerLoginDTO loginDTO = new EmployeerLoginDTO(email, password);
        Employeer loggedInEmployeer = employeerService.loginEmployeer(loginDTO);

        if (loggedInEmployeer != null) {
            authenticationListener.onAuthenticationSuccess();
        } else {
            authenticationListener.onAuthenticationFailure();
        }
    }
}
