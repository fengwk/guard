package fun.fengwk.simplesender.core.service;

import fun.fengwk.simplesender.core.config.SimpleSenderProperties;
import fun.fengwk.simplesender.share.model.EmailDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 邮件发送服务。
 *
 * @see <a href="http://www.ityouknow.com/springboot/2017/05/06/spring-boot-mail.html">Spring Boot (十)：邮件服务</a>
 * @author fengwk
 */
@Service
public class EmailSendServiceImpl implements EmailSendService {

    private final SimpleSenderProperties simpleSenderProperties;
    private final JavaMailSender javaMailSender;

    public EmailSendServiceImpl(SimpleSenderProperties simpleSenderProperties, JavaMailSender javaMailSender) {
        this.simpleSenderProperties = simpleSenderProperties;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(EmailDTO emailDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(getRandomFromMail());
        simpleMailMessage.setTo(emailDTO.getTargetEmail());
        simpleMailMessage.setSubject(emailDTO.getSubject());
        simpleMailMessage.setText(emailDTO.getContent());
        javaMailSender.send(simpleMailMessage);
    }

    private String getRandomFromMail() {
        List<String> fromMails = simpleSenderProperties.getFromMails();
        int idx = ThreadLocalRandom.current().nextInt(fromMails.size());
        return fromMails.get(idx);
    }

}
