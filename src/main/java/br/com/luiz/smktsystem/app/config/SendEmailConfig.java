package br.com.luiz.smktsystem.app.config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailConfig {

    public static void sendEmail() {
        String to = "contatoednaldoluiz@gmail.com";
        String from = "contato.barberhub@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "contato.barberhub@gmail.com";
        final String password = "vnmeoiiialnpapsh";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Teste de Email");
            message.setText("Este é um email de teste enviado do Java.");
            Transport.send(message);
            System.out.println("Email enviado com sucesso!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
