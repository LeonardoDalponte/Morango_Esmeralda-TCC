package morango_esmeralda.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.enums.StatusEmail;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDTO {
    private Integer emailId;
    private String referenciaDoPropetario;
    private String enviaEmail;
    private String recebeEmail;
    private String titulo;
    private String texto;
    private LocalDateTime DataRecebida;
    private StatusEmail statusEmail;
}
