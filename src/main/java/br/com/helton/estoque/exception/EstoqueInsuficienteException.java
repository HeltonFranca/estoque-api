package br.com.helton.estoque.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(
        Integer  estoqueAtual,
        Integer  quantidadeSolicitada
    ){
        super(

            "estoque insuficiente. disponivel :"
            +estoqueAtual
            +",solicitado:"
            +quantidadeSolicitada
        );
    }
    
}
