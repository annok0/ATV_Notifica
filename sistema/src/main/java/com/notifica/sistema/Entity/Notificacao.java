package com.notifica.sistema.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_cpf")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private String mensagem;
    private String categoria;

    @Column(length = 10)
    private String status;

    public Notificacao() {
    }

    public Notificacao(Long id, Usuario usuario, Produto produto, String mensagem, String categoria, String status) {
        this.id = id;
        this.usuario = usuario;
        this.produto = produto;
        this.mensagem = mensagem;
        this.categoria = categoria;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
