package br.com.helton.estoque.exception;

public class UsernameJaExisteException extends RuntimeException {


    public UsernameJaExisteException(String username){
        super("O username '" + username + "' já está cadastrado");
    }
    
}
