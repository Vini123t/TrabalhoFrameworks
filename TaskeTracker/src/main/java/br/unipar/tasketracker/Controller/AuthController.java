package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Repository.UsuarioRepository;
import br.unipar.tasketracker.Service.UsuarioService;
import br.unipar.tasketracker.dto.UsuarioLoginDto;
import br.unipar.tasketracker.dto.UsuarioRegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioRegistroDTO registroDto) {
        usuarioService.registrarUsuario(registroDto.getNome(), registroDto.getEmail(), registroDto.getSenha());
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Usuario Logado com sucesso", HttpStatus.OK);
    }
    @GetMapping("/")
    public String home() {
        return "tarefas";
    }
}
