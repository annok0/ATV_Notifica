package com.notifica.sistema.Repository;

import com.notifica.sistema.Entity.Notificacao;
import com.notifica.sistema.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByUsuario(Usuario usuario);
    List<Notificacao> findByUsuarioAndStatus(Usuario usuario, String status);
}
