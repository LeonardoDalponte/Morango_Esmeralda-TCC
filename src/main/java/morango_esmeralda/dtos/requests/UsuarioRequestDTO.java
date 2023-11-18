package morango_esmeralda.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.domain.user.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRequestDTO {

    private String nome;
    private String senha;
    private UserRole role;
    private String email;
    private String endereco;


}
