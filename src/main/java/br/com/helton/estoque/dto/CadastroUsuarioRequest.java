package br.com.helton.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size; 


public record CadastroUsuarioRequest(


    @NotBlank(message = "Username obrigatorio!")
    @Size(min = 3, max = 50, message = "O username deve ter entre 3 e 50 caracteres")
    String username,

    @NotBlank(message = "senha obrigatoria!")
    @Size(min = 8, max = 150, message = "A senha deve ter entre 8 e 150 caracteres")
    String senha

){

}
