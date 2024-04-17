package br.unc.chamados.controllers;

import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.usuario.AuthenticationDTO;
import br.unc.chamados.dto.usuario.LoginResponseDTO;
import br.unc.chamados.dto.usuario.UserRequestDTO;
import br.unc.chamados.infra.security.TokenService;
import br.unc.chamados.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            // Handle authentication failure (invalid username or password)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequestDTO userDTO){
        if(this.usuarioRepository.findByLogin(userDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        Usuario newUser = new Usuario();
        newUser.setLogin(userDTO.login());
        newUser.setPassword(encryptedPassword);
        newUser.setNome(userDTO.nome());
        newUser.setCampus(userDTO.campus());
        newUser.setSetor(userDTO.setor());
        newUser.setEmail(userDTO.email());
        newUser.setRole(userDTO.role());
        newUser.setCpf(userDTO.cpf());
        newUser.setEmailConfirmed(false);

        this.usuarioRepository.save(newUser);

        return ResponseEntity.ok().build();

    }
}
