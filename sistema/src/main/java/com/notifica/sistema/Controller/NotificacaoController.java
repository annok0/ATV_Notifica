package com.notifica.sistema.Controller;

import com.notifica.sistema.Entity.Notificacao;
import com.notifica.sistema.Service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<List<Notificacao>> listarPorUsuario(
            @PathVariable String cpf,
            @RequestParam(required = false) String status
    ) {
        if (status != null) {
            return ResponseEntity.ok(notificacaoService.listarPorUsuarioEStatus(cpf, status));
        } else {
            return ResponseEntity.ok(notificacaoService.listarPorUsuario(cpf));
        }
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Notificacao> marcarComoLida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacaoService.marcarComoLida(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> buscarPorId(@PathVariable Long id) {
        return notificacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
