package com.proyect.jef.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServicio {

@Autowired
    private JavaMailSender mailSender;

@Async
public void enviar(String cuerpo, String titulo, String mail){
    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(mail);
    mensaje.setFrom("zerolilit07@gmail.com");
    mensaje.setSubject(titulo);
    mensaje.setText(cuerpo);
    mailSender.send(mensaje);
}

}


