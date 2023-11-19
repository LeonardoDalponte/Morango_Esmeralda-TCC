package morango_esmeralda.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.dtos.requests.LoginRequestDTO;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.repository.UsuarioRepository;
import morango_esmeralda.service.CadastroLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api")
public class CadastroLoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    private final CadastroLoginService cadastroLoginService;

    @GetMapping()
    public List<Usuario> buscarTodos() {
        return cadastroLoginService.buscarTodos();
    }


    @PostMapping(path = "/cadastrar")
    public UsuarioResponseDTO cadastrar(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        return cadastroLoginService.cadastrar(usuarioRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return cadastroLoginService.login(loginRequestDTO);
    }

    @GetMapping(path = "/buscarPorToken")
    public Usuario me(Principal principal) {
        return usuarioRepository.findByEmail(principal.getName())
                .orElseThrow();
    }


    @DeleteMapping(path = "/{id}")
    public void deletar(@PathVariable("id") Integer idUsuario) {
        cadastroLoginService.deletar(idUsuario);
    }

    @PutMapping(path = "/alterar")
    public UsuarioResponseDTO alterar(@RequestBody UsuarioRequestDTO usuarioRequestDTO, Principal principal) {
        return cadastroLoginService.alterar(usuarioRequestDTO, principal);
    }
}
