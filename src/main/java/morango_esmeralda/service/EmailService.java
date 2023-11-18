package morango_esmeralda.service;

import jakarta.transaction.Transactional;
import morango_esmeralda.domain.Email;
import morango_esmeralda.domain.email.StatusEmail;
import morango_esmeralda.repository.EmailRepository;
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

    public Email enviarEmail(Email emailModel) {
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
                return emailRepository.save(emailModel);
            }
        }
    }


