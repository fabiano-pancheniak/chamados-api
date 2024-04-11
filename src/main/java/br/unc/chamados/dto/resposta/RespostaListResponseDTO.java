package br.unc.chamados.dto.resposta;

import br.unc.chamados.domain.resposta.Resposta;

import java.util.List;

public record RespostaListResponseDTO(List<Resposta> respostas) {
}
