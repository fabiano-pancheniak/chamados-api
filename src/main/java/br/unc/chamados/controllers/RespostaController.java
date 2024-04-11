package br.unc.chamados.controllers;

import br.unc.chamados.domain.chamado.Chamado;
import br.unc.chamados.domain.resposta.Resposta;
import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.resposta.RespostaListResponseDTO;
import br.unc.chamados.dto.resposta.RespostaRequestDTO;
import br.unc.chamados.dto.resposta.RespostaShort;
import br.unc.chamados.repositories.ChamadoRepository;
import br.unc.chamados.repositories.UsuarioRepository;
import br.unc.chamados.services.RespostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resposta")
@RequiredArgsConstructor
public class RespostaController {
    private final RespostaService respostaService;
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/{chamadoId}")
    List<RespostaShort> getRespostasByEvent(@PathVariable String chamadoId){
        return this.respostaService.getRespostas(chamadoId);
    }

    @PostMapping("/{chamadoId}")
    ResponseEntity<Resposta> createResposta(@RequestBody RespostaRequestDTO body, @PathVariable String chamadoId){
        String usuarioId = body.usuarioId();
        Chamado chamado = chamadoRepository.findById(chamadoId).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Resposta respostaRequestDTO = this.respostaService.createResposta(body, chamado, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaRequestDTO);
    }

}
