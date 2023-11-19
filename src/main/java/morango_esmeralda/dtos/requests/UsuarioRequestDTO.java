package morango_esmeralda.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.domain.user.UserRole;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRequestDTO {
    private String nome;
    private String senha;
    private String email;
    private Date dataNasc;
    private String telefone;
}
