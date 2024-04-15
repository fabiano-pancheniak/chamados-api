package br.unc.chamados.controllers;

import br.unc.chamados.domain.chamado.Chamado;
import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.chamado.ChamadoRequestDTO;
import br.unc.chamados.repositories.UsuarioRepository;
import br.unc.chamados.services.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
@RequiredArgsConstructor
public class ChamadoController {
    private final ChamadoService chamadoService;
    private final UsuarioRepository userRepository;

    @GetMapping
    public List<Chamado> getChamados(){
        return this.chamadoService.getChamados();
    }

    @GetMapping("/{atendenteId}")
    public List<Chamado> getChamadoByAtendenteId(@PathVariable String atendenteId){
        return this.chamadoService.getChamadosByAtendenteId(atendenteId);
    }

    @PostMapping
    public ResponseEntity<Chamado> createChamado(@RequestBody ChamadoRequestDTO body){
        String usuarioId = body.usuarioId();
        Usuario user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Chamado chamadoRequestDTO = this.chamadoService.createChamado(body, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoRequestDTO);
    }

    @PatchMapping("/{chamadoId}")
    public ResponseEntity<Chamado> updateAtendente(@PathVariable String chamadoId, @RequestBody ChamadoRequestDTO body){
        String usuarioId = body.atendenteId();
        Usuario user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Chamado chamadoRequestDTO = this.chamadoService.setAtendente(user, chamadoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoRequestDTO);
    }

    @PatchMapping("/status/{chamadoId}")
    public ResponseEntity<Chamado> updateStatus(@PathVariable String chamadoId, @RequestBody ChamadoRequestDTO body){
        String status = body.status();
        Chamado chamadoRequestDTO = this.chamadoService.setStatus(status.charAt(0), chamadoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoRequestDTO);
    }
}
