package com.notifica.sistema.Service;

import com.notifica.sistema.Entity.Produto;
import com.notifica.sistema.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final NotificacaoService notificacaoService;

    public ProdutoService(ProdutoRepository produtoRepository, NotificacaoService notificacaoService) {
        this.produtoRepository = produtoRepository;
        this.notificacaoService = notificacaoService;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    boolean precoAlterado = produto.getPreco() != (produtoAtualizado.getPreco());
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setCategoria(produtoAtualizado.getCategoria());

                    Produto atualizado = produtoRepository.save(produto);

                    if (precoAlterado) {
                        notificacaoService.gerarNotificacoesDePreco(atualizado);
                    }

                    return atualizado;
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
