package morango_esmeralda.service;

import morango_esmeralda.domain.Usuario;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.excepition.UsuarioException;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO buscarPorToken(Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Usuario não encontrado")
                );
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setTipo(usuario.getTipo());
        usuarioResponseDTO.setSenha(usuario.getSenha());
        usuarioResponseDTO.setTelefone(usuario.getTelefone());
        usuarioResponseDTO.setData_nasc(usuario.getDataNasc());
        usuarioResponseDTO.setEmail(usuario.getEmail());

        return usuarioResponseDTO;
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
        usuarioParaAlterar.setEmail(usuarioRequestDTO.getEmail());
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

    public UsuarioResponseDTO redefinirSenha(UsuarioRequestDTO usuarioRequestDTO, Principal principal) {
        Usuario usuarioParaAlterarSenha = usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Usuario não encontrado"));

        usuarioParaAlterarSenha.setSenha(new BCryptPasswordEncoder().encode(usuarioRequestDTO.getSenha()));

        Usuario usuarioComSenhaAlterada = usuarioRepository.save(usuarioParaAlterarSenha);


        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(usuarioComSenhaAlterada.getIdUsuario());
        usuarioResponseDTO.setNome(usuarioComSenhaAlterada.getNome());
        usuarioResponseDTO.setSenha(usuarioComSenhaAlterada.getSenha());

        return usuarioResponseDTO;
    }
}
