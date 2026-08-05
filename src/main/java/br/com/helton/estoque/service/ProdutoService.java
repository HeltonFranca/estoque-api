package br.com.helton.estoque.service;
import br.com.helton.estoque.dto.ProdutoRequest;
import br.com.helton.estoque.dto.ProdutoResponse;
import br.com.helton.estoque.entity.Produto;
import br.com.helton.estoque.exception.ProdutoNaoEncontradoException;
import br.com.helton.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import br.com.helton.estoque.dto.MovimentacaoEstoqueRequest;
import java.util.List;

@Service
public class ProdutoService {
    public ProdutoResponse adicionarEstoque(
            Long id,
            MovimentacaoEstoqueRequest request
    ) {
        Produto produto = buscarEntidadePorId(id);

        produto.adicionarEstoque(request.quantidade());

        Produto produtoAtualizado = produtoRepository.save(produto);

        return converterParaResponse(produtoAtualizado);
    }

    public ProdutoResponse retirarEstoque(
            Long id,
            MovimentacaoEstoqueRequest request
    ) {
        Produto produto = buscarEntidadePorId(id);

        produto.retirarEstoque(request.quantidade());

        Produto produtoAtualizado = produtoRepository.save(produto);

        return converterParaResponse(produtoAtualizado);
    }
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.findAll()
                .stream() 
                .map(this::converterParaResponse) 
                .toList(); 
    }

public ProdutoResponse buscarPorId(Long id){
    Produto produto = buscarEntidadePorId(id);

    return converterParaResponse(produto);
}

public ProdutoResponse cadastrar(ProdutoRequest request){
    Produto produto = new Produto (
        request.nome(),
        request.quantidade(),
        request.preco()

    );
     Produto produtoSalvo = produtoRepository.save(produto);
     return converterParaResponse(produtoSalvo); 
}
public ProdutoResponse atualizar(Long id, ProdutoRequest request){

    Produto produtoExistente = buscarEntidadePorId(id);

    produtoExistente.setNome(request.nome());

    produtoExistente.setQuantidade(request.quantidade());

    produtoExistente.setPreco(request.preco());

    Produto produtoAtualizado = produtoRepository.save(produtoExistente);

    return converterParaResponse(produtoAtualizado);
}

 public void excluir(Long id) {
        Produto produtoExistente = buscarEntidadePorId(id);

        produtoRepository.delete(produtoExistente);
    }

    private Produto buscarEntidadePorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    private ProdutoResponse converterParaResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getQuantidade(),
                produto.getPreco()
        );
    }
}
