package br.com.helton.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; 
import jakarta.validation.constraints.Positive; 
import jakarta.validation.constraints.PositiveOrZero; 
import jakarta.validation.constraints.Size; 

import java.math.BigDecimal;


    public record ProdutoRequest(

        @NotBlank(message = "o nome é obrigatorio")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,


        @NotNull(message = "a quantidade é obrigatoria")
        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        Integer quantidade,


        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco
    ){

    }
    

