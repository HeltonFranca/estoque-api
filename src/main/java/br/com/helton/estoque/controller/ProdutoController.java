package br.com.helton.estoque.controller;
import br.com.helton.estoque.dto.MovimentacaoEstoqueRequest; 

import org.springframework.web.bind.annotation.PatchMapping;
import br.com.helton.estoque.dto.ProdutoRequest;
import br.com.helton.estoque.dto.ProdutoResponse;
import br.com.helton.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoResponse> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse cadastrar(
            @Valid @RequestBody ProdutoRequest request
    ) {
        return produtoService.cadastrar(request);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequest request
    ) {
        return produtoService.atualizar(id, request);
    }
    @PatchMapping("/{id}/entrada")
    public ProdutoResponse adicionaEstoque(

        @PathVariable Long id,
        @Valid @RequestBody MovimentacaoEstoqueRequest request
    ){
        return produtoService.adicionarEstoque(id, request);
        
    }

    @PatchMapping("/{id}/saida")
    public ProdutoResponse retirarEstoque(
            @PathVariable Long id,
            @Valid @RequestBody MovimentacaoEstoqueRequest request
    ) {
        return produtoService.retirarEstoque(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}
