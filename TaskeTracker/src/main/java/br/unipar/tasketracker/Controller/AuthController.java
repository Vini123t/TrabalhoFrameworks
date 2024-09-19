package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.UsuarioService;
import br.unipar.tasketracker.dto.UsuarioLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto loginRequest) {
        boolean isValid = userService.validateUser(loginRequest.getEmail(), loginRequest.getSenha());
        if (isValid) {
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Invalid credentials\"}");
        }
    }
}
