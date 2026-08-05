package br.com.helton.estoque.config;

import br.com.helton.estoque.entity.Produto;
import br.com.helton.estoque.repository.ProdutoRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class CargaInicialConfig {

    @Bean
    @ConditionalOnProperty(name = "app.seed.enabled", havingValue = "true")
    public ApplicationRunner carregarProdutosIniciais(
            ProdutoRepository produtoRepository
    ) {
        return args -> {
            if (produtoRepository.count() > 0) {
                return;
            }

            produtoRepository.saveAll(List.of(
                    new Produto("Notebook", 10, new BigDecimal("3499.90")),
                    new Produto("Monitor", 15, new BigDecimal("899.90")),
                    new Produto("Teclado mecânico", 20, new BigDecimal("249.90")),
                    new Produto("Mouse", 30, new BigDecimal("119.90")),
                    new Produto("Headset", 12, new BigDecimal("299.90")),
                    new Produto("Webcam", 8, new BigDecimal("349.90")),
                    new Produto("Cadeira de escritório", 5, new BigDecimal("1099.90")),
                    new Produto("Suporte para notebook", 18, new BigDecimal("89.90"))
            ));
        };
    }
}
