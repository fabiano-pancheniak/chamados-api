package br.unc.chamados.services;

import br.unc.chamados.ChamadosApplication;
import br.unc.chamados.domain.chamado.Chamado;
import br.unc.chamados.domain.resposta.Resposta;
import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.resposta.RespostaListResponseDTO;
import br.unc.chamados.dto.resposta.RespostaRequestDTO;
import br.unc.chamados.dto.resposta.RespostaShort;
import br.unc.chamados.repositories.RespostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RespostaService {
    private final RespostaRepository respostaRepository;

    public List<RespostaShort> getRespostas(String chamadoid){
        List<Resposta> respostas = this.respostaRepository.findByChamadoId(chamadoid);

        List<RespostaShort> respostaShort = respostas.stream().map(resposta -> {
            return new RespostaShort(resposta.getUsuario().getNome(), resposta.getResposta(), resposta.getCreatedAt());
        }).toList();

        return respostaShort;
    }
    public Resposta createResposta(RespostaRequestDTO respostaRequestDTO, Chamado chamado, Usuario usuario){
        Resposta newResposta = new Resposta();

        newResposta.setResposta(respostaRequestDTO.resposta());
        newResposta.setChamado(chamado);
        newResposta.setCreatedAt(LocalDateTime.now());
        newResposta.setUsuario(usuario);

        this.respostaRepository.save(newResposta);
        return newResposta;
    }
}
