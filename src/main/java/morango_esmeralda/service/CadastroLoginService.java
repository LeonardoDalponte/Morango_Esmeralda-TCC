package morango_esmeralda.service;

import lombok.RequiredArgsConstructor;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastroLoginService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioParaCadastrar = new Usuario();
        usuarioParaCadastrar.setNome(usuarioRequestDTO.getNome());
        usuarioParaCadastrar.setSenha(usuarioRequestDTO.getSenha());
        usuarioRepository.save(usuarioParaCadastrar);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(usuarioParaCadastrar.getIdUsuario());
        usuarioResponseDTO.setNome(usuarioParaCadastrar.getNome());

        return usuarioResponseDTO;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}
