package br.unc.chamados.services;

import br.unc.chamados.domain.chamado.Chamado;
import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.chamado.ChamadoRequestDTO;
import br.unc.chamados.repositories.ChamadoRepository;
import br.unc.chamados.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    public Chamado createChamado(ChamadoRequestDTO chamadoRequestDTO, Usuario user){
        Chamado newChamado = new Chamado();

        newChamado.setDescription(chamadoRequestDTO.description());
        newChamado.setStatus('A');
        newChamado.setUsuario(user);
        newChamado.setCreatedAt(LocalDateTime.now());

        this.chamadoRepository.save(newChamado);

        return newChamado;
    }

    public Chamado setAtendente(Usuario atendente, String chamadoId){
        Chamado chamado = chamadoRepository.findById(chamadoId).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.setAtendente(atendente);

        this.chamadoRepository.save(chamado);

        return chamado;
    }

    public Chamado setStatus(Character status, String chamadoId){
        Chamado chamado = chamadoRepository.findById(chamadoId).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.setStatus(status);

        this.chamadoRepository.save(chamado);

        return chamado;
    }

    public List<Chamado> getChamados(){
        List<Chamado> chamados = chamadoRepository.findAll();
        return chamados;
    }

    public List<Chamado> getChamadosByAtendenteId(String atendenteId){
        List<Chamado> chamados = chamadoRepository.findByAtendenteId(atendenteId);
        return chamados;
    }
}
