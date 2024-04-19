package br.unc.chamados.services;

import br.unc.chamados.domain.usuario.Usuario;
import br.unc.chamados.dto.usuario.UserIdResponseDTO;
import br.unc.chamados.dto.usuario.UserRequestDTO;
import br.unc.chamados.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository userRepository;

    public List<Usuario> getAllUsers(){
        return this.userRepository.findAll();
    }

    public UserDetails getUser(String login){
        return this.userRepository.findByLogin(login);
    }

    public String getUserId(String login){
        Usuario user = this.userRepository.findByLogin(login);
        return user.getId();
    }

    public Usuario createUser(UserRequestDTO userDTO){
        Usuario newUser = new Usuario();
        newUser.setLogin(userDTO.login());
        newUser.setPassword(userDTO.password());
        newUser.setNome(userDTO.nome());
        newUser.setCampus(userDTO.campus());
        newUser.setSetor(userDTO.setor());
        newUser.setEmail(userDTO.email());
        newUser.setRole(userDTO.role());
        newUser.setCpf(userDTO.cpf());
        newUser.setEmailConfirmed(false);

        this.userRepository.save(newUser);

        return newUser;
    }
}
