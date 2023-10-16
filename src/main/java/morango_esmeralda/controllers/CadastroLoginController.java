package morango_esmeralda.controllers;

import lombok.RequiredArgsConstructor;
import morango_esmeralda.dtos.requests.UsuarioRequestDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.service.CadastroLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api")
public class CadastroLoginController {
    private final CadastroLoginService cadastroLoginService;

    @GetMapping()
    public List<Usuario> buscarTodos() {
        return cadastroLoginService.buscarTodos();

    }

    @Autowired
    private AuthenticationManager authenticationManager;





    @PostMapping(path = "/cadastrar")
    public UsuarioResponseDTO cadastrar(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return cadastroLoginService.cadastrar(usuarioRequestDTO);


    }

    @DeleteMapping(path = "/{id}")
    public void deletar(@PathVariable("id") Integer idUsuario) {
        cadastroLoginService.deletar(idUsuario);

    }

}
