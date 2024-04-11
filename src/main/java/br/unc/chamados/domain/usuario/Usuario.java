package br.unc.chamados.domain.usuario;

import br.unc.chamados.domain.chamado.Chamado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuario")
public class Usuario {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(name = "email_confirmed")
    private Boolean emailConfirmed;
    private String campus;
    private String setor;

}
