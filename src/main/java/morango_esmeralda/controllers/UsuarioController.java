package morango_esmeralda.controllers;

import lombok.RequiredArgsConstructor;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.repository.UsuarioRepository;
import morango_esmeralda.service.UsuarioService;
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

    @Autowired
    AuthenticationManager authenticationManager;

    private final UsuarioService usuarioService;

    @GetMapping(path = "/buscar-por-token")
    public UsuarioResponseDTO buscarPorToken(Principal principal) {
        return usuarioService.buscarPorToken(principal);
    }

    @PutMapping(path = "/alterar")
    public UsuarioResponseDTO alterar(@RequestBody UsuarioRequestDTO usuarioRequestDTO, Principal principal) {
        return usuarioService.alterar(usuarioRequestDTO, principal);
    }

    @GetMapping()
    public List<Usuario> buscarTodos() {
        return usuarioService.buscarTodos();
    }


    @DeleteMapping(path = "/{id}")
    public void deletar(@PathVariable("id") Integer idUsuario) {
        usuarioService.deletar(idUsuario);
    }

    @PutMapping("/redefinir-senha")
    public UsuarioResponseDTO redefinirSenha(@RequestBody UsuarioRequestDTO usuarioRequestDTO,Principal principal){
       return usuarioService.redefinirSenha(usuarioRequestDTO,principal);
    }
}
