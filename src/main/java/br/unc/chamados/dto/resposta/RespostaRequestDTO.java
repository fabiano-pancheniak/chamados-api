package br.unc.chamados.dto.resposta;
import java.time.LocalDateTime;

public record RespostaRequestDTO(
        String resposta,
        String chamadoId,
        String usuarioId,
        LocalDateTime createdAt
) {
}
