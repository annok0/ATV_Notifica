package com.notifica.sistema.Service;

import com.notifica.sistema.Entity.Usuario;
import com.notifica.sistema.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorCpf(String cpf) {
        return usuarioRepository.findById(cpf);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(String cpf) {
        usuarioRepository.deleteById(cpf);
    }

    public Usuario atualizar(String cpf, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(cpf)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setSobrenome(usuarioAtualizado.getSobrenome());
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setSenha(usuarioAtualizado.getSenha());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
