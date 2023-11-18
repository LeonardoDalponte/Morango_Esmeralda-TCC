package morango_esmeralda.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.domain.user.UserRole;

import javax.management.relation.Role;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioResponseDTO {

    private Integer idUsuario;

    private String nome;

    private String senha;

    private String email;

    private UserRole role;

}
