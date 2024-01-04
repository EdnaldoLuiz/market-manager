package br.com.luiz.smktsystem.service.dao;

public interface AuthenticationListener {
    void onAuthenticationSuccess();
    void onAuthenticationFailure();
}
