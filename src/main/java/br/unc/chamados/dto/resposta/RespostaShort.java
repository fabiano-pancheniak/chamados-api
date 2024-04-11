package br.unc.chamados.dto.resposta;

import java.time.LocalDateTime;

public record RespostaShort(String nome, String resposta, LocalDateTime createdAt) {
}
