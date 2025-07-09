package dev.capsule.timeCapsule.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CapsuleRequestDTO {

    @NotBlank (message = "Email is required")
    @Size (max = 100, message = "The email must have a maximum of 100 characters")
    private String destinationEmail;
    @NotBlank (message = "The message is mandatory")
    @Size (max = 2000, message = "The message must have a maximum of 2000 characters")
    private String message;
    @Future
    private LocalDate dateSent;
    private String hiddenField;

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
    public String getHiddenField() {
        return hiddenField;
    }
    public void setHiddenField(String hiddenField) {
        this.hiddenField = hiddenField;
    }
    
}
