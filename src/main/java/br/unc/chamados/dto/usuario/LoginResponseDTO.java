package br.unc.chamados.dto.usuario;

import br.unc.chamados.domain.usuario.UserRole;

public record LoginResponseDTO(String token, UserRole userRole, String userId) {
}
