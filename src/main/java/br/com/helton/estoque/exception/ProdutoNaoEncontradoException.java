package br.com.helton.estoque.exception;

public class ProdutoNaoEncontradoException extends RuntimeException { 

    public ProdutoNaoEncontradoException(Long id) {
        super("Produto com ID " + id + " não encontrado");
    }
}