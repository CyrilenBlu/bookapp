package blue.bookapp.services;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    JavaMailSender getJavaMailSender();
    void sendMessage(String to, String subject, String text);
}
