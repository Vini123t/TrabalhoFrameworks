package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.UsuarioRepository;
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
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new org.springframework.security.core.userdetails.User(usuario.get().getEmail(), usuario.get().getSenha(), Collections.emptyList());
    }

    public Usuario registrarUsuario(String nome, String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        return usuarioRepository.save(usuario);
    }
}

