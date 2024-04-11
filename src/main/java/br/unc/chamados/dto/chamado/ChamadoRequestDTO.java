package br.unc.chamados.dto.chamado;

import lombok.Getter;

import java.time.LocalDateTime;

public record ChamadoRequestDTO(
        String description,
        String status,
        String atendenteId,
        String usuarioId,
        LocalDateTime createdAt
) {
}
