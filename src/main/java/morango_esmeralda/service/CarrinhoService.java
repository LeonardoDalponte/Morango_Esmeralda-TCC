package morango_esmeralda.service;

import morango_esmeralda.domain.Carrinho;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.dtos.requests.CarrinhoRequestDTO;
import morango_esmeralda.dtos.responses.CarrinhoResponseDTO;
import morango_esmeralda.dtos.responses.UsuarioResponseDTO;
import morango_esmeralda.repository.CarrinhoRepository;
import morango_esmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public CarrinhoResponseDTO salvar(CarrinhoRequestDTO carrinhoRequestDTO) {
        Carrinho carrinhoParaSerSalvo = new Carrinho();
        Usuario usuario = usuarioRepository.findById(carrinhoRequestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        carrinhoParaSerSalvo.setUsuario(usuario);

        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinhoParaSerSalvo);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdUsuario(carrinhoSalvo.getUsuario().getIdUsuario());
        usuarioResponseDTO.setNome(carrinhoSalvo.getUsuario().getNome());

        CarrinhoResponseDTO carrinhoResponseDTO = new CarrinhoResponseDTO();

        carrinhoResponseDTO.setUsuarioResponseDTO(usuarioResponseDTO);

        return carrinhoResponseDTO;
    }
}
