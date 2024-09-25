package br.unipar.tasktraker.services;

import br.unipar.tasktraker.models.Usuario;
import br.unipar.tasktraker.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public boolean autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true; // Autenticado com sucesso
        }
        return false; // Falha na autenticação
    }

}
