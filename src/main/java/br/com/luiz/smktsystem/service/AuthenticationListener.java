package br.com.luiz.smktsystem.service;

public interface AuthenticationListener {
    void onAuthenticationSuccess();
    void onAuthenticationFailure();
}
