package morango_esmeralda.controllers;

import jakarta.validation.Valid;
import morango_esmeralda.domain.Email;
import morango_esmeralda.dtos.EmailDTO;
import morango_esmeralda.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailControler {
    @Autowired
    EmailService emailService;

    @PostMapping("/enviar-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDto) {
        Email emailModel = new Email();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.enviarEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
