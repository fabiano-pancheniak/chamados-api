package br.unc.chamados.controllers;

import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.usuario.UserRequestDTO;
import br.unc.chamados.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService userService;


    @GetMapping
    ResponseEntity<List<Usuario>> getUsers(){
        List<Usuario> users = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    ResponseEntity<Usuario> createUser(@RequestBody UserRequestDTO body){
        Usuario userRequestDTO = this.userService.createUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequestDTO);
    }
}
