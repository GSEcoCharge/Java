// package br.com.example.ecocharge.mail;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.stereotype.Service;

// @Service
// public class EmailService {
    
//     @Autowired
//     JavaMailSender javaMailSender;

//     public void sendEmail(String destinatario, String assunto, String mensagem) {
//         SimpleMailMessage mailMessage = new SimpleMailMessage();
//         mailMessage.setFrom("contato@ecocharge.com.br");
//         mailMessage.setTo(destinatario);
//         mailMessage.setSubject(assunto);
//         mailMessage.setText(mensagem);
//         javaMailSender.send(mailMessage);

//     }

// }
