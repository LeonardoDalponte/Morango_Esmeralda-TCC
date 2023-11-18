package morango_esmeralda.domain;

import jakarta.persistence.*;
import lombok.Data;
import morango_esmeralda.domain.email.StatusEmail;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

    @Entity
    @Data
    @Table(name = "email")
    public class Email implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID emailId;
        private String referenciaDoPropetario;
        private String enviaEmail;
        private String recebeEmail;
        private String titulo;
        @Column(columnDefinition = "TEXT")
        private String texto;
        private LocalDateTime DataRecebida;
        private StatusEmail statusEmail;
    }

