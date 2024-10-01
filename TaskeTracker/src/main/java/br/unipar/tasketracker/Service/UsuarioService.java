package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.UsuarioRepository;
import br.unipar.tasketracker.exception.AuthenticationException;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    public Usuario login(String email, String senha) {
        // Buscar o usuário pelo email
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Verificar se a senha está correta
        if (!usuario.getSenha().equals(senha)) {
            throw new AuthenticationException("Senha incorreta");
        }

        return usuario; // Se o email e senha estiverem corretos, retornar o usuário
    }
}


