package morango_esmeralda.service;

import lombok.RequiredArgsConstructor;
import morango_esmeralda.Infra.security.TokenService;
import morango_esmeralda.domain.user.UserRole;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.LoginResponseDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastroLoginService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioRequestDTO) {
        if (this.usuarioRepository.findByNome(usuarioRequestDTO.getNome()) != null) {
            throw new RuntimeException("Usuario não encontrado!");
        }

        if (usuarioRequestDTO.getRole() == UserRole.ADMIN) {
            throw new RuntimeException("Usuario não pode ser do tipo ADMIN");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioRequestDTO.getSenha());
        Usuario usuarioParaCadastrar = new Usuario(usuarioRequestDTO.getNome(), senhaCriptografada, usuarioRequestDTO.getRole(), usuarioRequestDTO.getEndereco());

        usuarioRepository.save(usuarioParaCadastrar);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(usuarioParaCadastrar.getIdUsuario());
        usuarioResponseDTO.setNome(usuarioParaCadastrar.getNome());

        return usuarioResponseDTO;
    }

    public ResponseEntity<Object> login(UsuarioRequestDTO usuarioRequestDTOd) {
        var nomeSenha = new UsernamePasswordAuthenticationToken(usuarioRequestDTOd.getNome(), usuarioRequestDTOd.getSenha());
        var auth = this.authenticationManager.authenticate(nomeSenha);

        var token = tokenService.geradorDeToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public UsuarioResponseDTO alterar(UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setNome(usuarioRequestDTO.getNome());
        usuarioResponseDTO.setSenha(usuarioRequestDTO.getSenha());
        usuarioResponseDTO.setRole(usuarioRequestDTO.getRole());
        usuarioResponseDTO.setEmail(usuarioRequestDTO.getEmail());
        return usuarioResponseDTO;
    }
}
