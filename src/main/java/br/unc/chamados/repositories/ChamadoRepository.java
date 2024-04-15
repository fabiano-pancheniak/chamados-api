package br.unc.chamados.repositories;

import br.unc.chamados.ChamadosApplication;
import br.unc.chamados.domain.chamado.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, String> {
    List<Chamado> findByAtendenteId(String atendenteId);
}
