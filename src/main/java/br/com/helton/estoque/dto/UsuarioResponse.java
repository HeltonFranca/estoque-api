package br.com.helton.estoque.dto;
import java.util.UUID;


public record UsuarioResponse(

    UUID id,
    String username
){

}