package com.notifica.sistema.Service;

import com.notifica.sistema.Entity.Notificacao;
import com.notifica.sistema.Entity.Produto;
import com.notifica.sistema.Entity.Usuario;
import com.notifica.sistema.Repository.NotificacaoRepository;
import com.notifica.sistema.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository, UsuarioRepository usuarioRepository) {
        this.notificacaoRepository = notificacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void gerarNotificacoesDePreco(Produto produto) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario usuario : usuarios) {
            Notificacao notificacao = new Notificacao();
            notificacao.setUsuario(usuario);
            notificacao.setProduto(produto);
            notificacao.setMensagem("O preço do produto '" + produto.getNome() + "' foi atualizado para R$" + produto.getPreco());
            notificacao.setCategoria("Atualização de Preço");
            notificacao.setStatus("pendente");

            notificacaoRepository.save(notificacao);
        }
    }

    public List<Notificacao> listarPorUsuario(String cpf) {
        Usuario usuario = usuarioRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return notificacaoRepository.findByUsuario(usuario);
    }

    public List<Notificacao> listarPorUsuarioEStatus(String cpf, String status) {
        Usuario usuario = usuarioRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return notificacaoRepository.findByUsuarioAndStatus(usuario, status);
    }

    public Optional<Notificacao> buscarPorId(Long id) {
        return notificacaoRepository.findById(id);
    }

    public Notificacao marcarComoLida(Long id) {
        return notificacaoRepository.findById(id)
                .map(notificacao -> {
                    notificacao.setStatus("lida");
                    return notificacaoRepository.save(notificacao);
                })
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
    }
}
