package dev.capsule.timeCapsule.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;


@Entity
@Table (name = "capsule_table")
public class Capsule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    @Size (max = 100, message = "The email must have a maximum of 100 characters")
    private String destinationEmail;
    @Lob
    @Column(length = 2000)
    @Size (max = 2000, message = "The message must have a maximum of 2000 characters")
    private String message;
    @Future
    private LocalDate dateSent;
    private boolean sent;

    public Capsule(String destinationEmail,String message,LocalDate dateSent){
        this.destinationEmail = destinationEmail;
        this.message = message;
        this.dateSent = dateSent;
        setSent(false);
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }
    public void setDestinationEmail(String destinationEmail) {
        this.destinationEmail = destinationEmail;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDate getDateSent() {
        return dateSent;
    }
    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }
    public boolean isSent() {
        return sent;
    }
    public void setSent(boolean sent) {
        this.sent = sent;
    }

}



