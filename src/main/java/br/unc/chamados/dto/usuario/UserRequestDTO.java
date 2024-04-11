package br.unc.chamados.dto.usuario;

public record UserRequestDTO(
        String login,
        String password,
        String nome,
        String campus,
        String setor,
        String email,
        Boolean emailConfirmed
) {
}
