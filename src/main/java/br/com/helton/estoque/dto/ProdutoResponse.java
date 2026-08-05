package br.com.helton.estoque.dto;

import java.math.BigDecimal;

public record ProdutoResponse (

    Long id,

    String nome,
    Integer quantidade,

    BigDecimal preco
    
)
{
    
}