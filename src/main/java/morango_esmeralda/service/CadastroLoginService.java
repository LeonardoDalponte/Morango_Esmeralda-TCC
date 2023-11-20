package morango_esmeralda.service;

import lombok.RequiredArgsConstructor;
import morango_esmeralda.Infra.security.TokenService;
import morango_esmeralda.enums.TipoUsuario;
import morango_esmeralda.dtos.requests.LoginRequestDTO;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.LoginResponseDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.excepition.UsuarioException;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
        Usuario usuarioParaCadastrar = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail())
                .orElse(null);
        if (usuarioParaCadastrar != null) {
            throw new UsuarioException("O email de usuario ja foi cadastrado");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioRequestDTO.getSenha());
        usuarioParaCadastrar = new Usuario(usuarioRequestDTO.getNome(), senhaCriptografada, usuarioRequestDTO.getEmail(),
                usuarioRequestDTO.getDataNasc(), usuarioRequestDTO.getTelefone(), TipoUsuario.CLIENTE);

        if (usuarioParaCadastrar == null) {
            throw new UsuarioException("Todos os campos devem ser prenchidos");
        }
        usuarioRepository.save(usuarioParaCadastrar);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(usuarioParaCadastrar.getIdUsuario());
        usuarioResponseDTO.setNome(usuarioParaCadastrar.getNome());
        usuarioResponseDTO.setRole(usuarioParaCadastrar.getTipo());
        usuarioResponseDTO.setEmail(usuarioParaCadastrar.getEmail());
        usuarioResponseDTO.setTelefone(usuarioParaCadastrar.getTelefone());
        usuarioResponseDTO.setData_nasc(usuarioParaCadastrar.getDataNasc());

        return usuarioResponseDTO;
    }

    public ResponseEntity<Object> login(LoginRequestDTO loginRequestDTO) {
        var emailSenha = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
        var auth = this.authenticationManager.authenticate(emailSenha);

        var token = tokenService.geradorDeToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public UsuarioResponseDTO alterar(UsuarioRequestDTO usuarioRequestDTO, Principal principal) {
        Usuario usuarioParaAlterar = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Usuario não encontrado"));

        usuarioParaAlterar.setNome(usuarioRequestDTO.getNome());
        usuarioParaAlterar.setTelefone(usuarioRequestDTO.getTelefone());
        usuarioParaAlterar.setDataNasc(usuarioRequestDTO.getDataNasc());
        usuarioParaAlterar.setSenha(new BCryptPasswordEncoder().encode(usuarioRequestDTO.getSenha()));

        Usuario usuarioAlterado = usuarioRepository.save(usuarioParaAlterar);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(usuarioAlterado.getIdUsuario());
        usuarioResponseDTO.setNome(usuarioAlterado.getNome());
        usuarioResponseDTO.setSenha(usuarioAlterado.getSenha());
        usuarioResponseDTO.setTelefone(usuarioAlterado.getTelefone());
        usuarioResponseDTO.setData_nasc(usuarioAlterado.getDataNasc());
        usuarioResponseDTO.setEmail(usuarioAlterado.getEmail());

        return usuarioResponseDTO;
    }
}
