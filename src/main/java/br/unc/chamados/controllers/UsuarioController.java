package br.unc.chamados.controllers;

import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.usuario.UserIdResponseDTO;
import br.unc.chamados.dto.usuario.UserRequestDTO;
import br.unc.chamados.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService userService;


    @GetMapping
    ResponseEntity<List<Usuario>> getUsers(){
        List<Usuario> users = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{login}")
    ResponseEntity<UserDetails> getUser(@PathVariable String login){
        UserDetails user = this.userService.getUser(login);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/userid/{login}")
    ResponseEntity<UserIdResponseDTO> getUserId(@PathVariable String login){
        String userId = this.userService.getUserId(login);
        return ResponseEntity.status(HttpStatus.OK).body(new UserIdResponseDTO(userId));
    }


    @PostMapping
    ResponseEntity<Usuario> createUser(@RequestBody UserRequestDTO body){
        Usuario userRequestDTO = this.userService.createUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequestDTO);
    }
}
