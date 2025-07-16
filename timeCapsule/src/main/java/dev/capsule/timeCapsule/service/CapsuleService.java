package dev.capsule.timeCapsule.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import dev.capsule.timeCapsule.model.Capsule;
import dev.capsule.timeCapsule.repository.CapsuleRepository;

@Service
public class CapsuleService {

    private final CapsuleRepository capsuleRepository;
    private final JavaMailSender javaMailSender;
    
    public CapsuleService(CapsuleRepository capsuleRepository, JavaMailSender javaMailSender) {
        this.capsuleRepository = capsuleRepository;
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;

    public Capsule save(Capsule capsule) {
        return capsuleRepository.save(capsule);
    }
    
    public List<Capsule> getAllByDate(LocalDate date) {
        return capsuleRepository.findByDate(date);
    }

    public String sendMail(Capsule capsule){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(capsule.getDestinationEmail());
            simpleMailMessage.setSubject("Time capsule");
            simpleMailMessage.setText(capsule.getMessage());
            javaMailSender.send(simpleMailMessage);
            return "Email sent";
        } catch (Exception e){
            return "Error trying to send email" + e.getLocalizedMessage();
        }
    }

    public void routine(List<Capsule> capsules){
        for (Capsule capsule : capsules){
            sendMail(capsule);
            capsule.setSent(true);
        }
    }
}


