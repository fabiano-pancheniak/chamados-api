package br.unc.chamados.domain.chamado;

import br.unc.chamados.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chamado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chamado {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Character status;

    @OneToOne
    @JoinColumn(name = "atendente_id", nullable = true)
    private Usuario atendente;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
