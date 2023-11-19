package morango_esmeralda.service;

import morango_esmeralda.domain.Email;
import morango_esmeralda.domain.Usuario;
import morango_esmeralda.domain.email.StatusEmail;
import morango_esmeralda.dtos.requests.SendPasswordEmailRequestDTO;
import morango_esmeralda.repository.EmailRepository;
import morango_esmeralda.repository.UsuarioRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    UsuarioRepository usuarioRepository;

    public void enviarEmail(Email emailModel) {
        emailModel.setDataRecebida(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEnviaEmail());
            message.setTo(emailModel.getRecebeEmail());
            message.setSubject(emailModel.getTitulo());
            message.setText(emailModel.getTexto());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERRO);
        } finally {
            emailRepository.save(emailModel);

        }
    }

    public void sendForgotPasswordEmail(SendPasswordEmailRequestDTO sendPasswordEmailRequestDTO) {
        String codigo = StringUtils.upperCase(RandomStringUtils.randomAlphabetic(6));


    }
}


