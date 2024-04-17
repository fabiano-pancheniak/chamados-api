package br.unc.chamados.dto.usuario;

import br.unc.chamados.domain.usuario.UserRole;

public record UserRequestDTO(
        String login,
        String password,
        String nome,
        String campus,
        String setor,
        String email,
        Boolean emailConfirmed,
        UserRole role,
        String cpf
) {

}
