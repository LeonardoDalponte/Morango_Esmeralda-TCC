package morango_esmeralda.controllers;

import lombok.RequiredArgsConstructor;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.excepition.UsuarioException;
import morango_esmeralda.repository.UsuarioRepository;
import morango_esmeralda.service.CadastroLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    private final CadastroLoginService cadastroLoginService;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping(path = "/buscar-por-token")
    public Usuario me(Principal principal) {
        return usuarioRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsuarioException("Usuario não encontrado"));
    }

    @PutMapping(path = "/alterar")
    public UsuarioResponseDTO alterar(@RequestBody UsuarioRequestDTO usuarioRequestDTO, Principal principal) {
        return cadastroLoginService.alterar(usuarioRequestDTO, principal);
    }

    @GetMapping()
    public List<Usuario> buscarTodos() {
        return cadastroLoginService.buscarTodos();
    }


    @DeleteMapping(path = "/{id}")
    public void deletar(@PathVariable("id") Integer idUsuario) {
        cadastroLoginService.deletar(idUsuario);
    }


}
