package br.com.helton.estoque.exception;

public class CredenciaisInvalidasException extends RuntimeException {

    public CredenciaisInvalidasException() {
        super("Username ou senha inválidos");
    }
}
