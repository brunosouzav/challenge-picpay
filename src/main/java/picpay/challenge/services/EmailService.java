package picpay.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String remetente;
	
	public void sendEmail(String destinatario, String assunto, String mensagem) {
		try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destinatario);
            message.setSubject(assunto);
            message.setText(mensagem);
            message.setFrom(remetente);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new MailSendException("Erro ao enviar e-mail: " + e.getMessage(), e);
        }
    }
	
}
