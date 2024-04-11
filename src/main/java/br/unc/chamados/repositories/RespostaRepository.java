package br.unc.chamados.repositories;

import br.unc.chamados.domain.resposta.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, String> {
    List<Resposta> findByChamadoId(String chamadoid);
}
