package dev.capsule.timeCapsule.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import dev.capsule.timeCapsule.model.Capsule;
import dev.capsule.timeCapsule.model.CapsuleRequestDTO;
import dev.capsule.timeCapsule.service.CapsuleService;
import dev.capsule.timeCapsule.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/capsule")
public class CapsuleController {

    private final CapsuleService capsuleService;
    private final RateLimiterService rateLimiterService;

    public CapsuleController(CapsuleService capsuleService,RateLimiterService rateLimiterService) {
        this.capsuleService = capsuleService;
        this.rateLimiterService = rateLimiterService;
    }

    @PostMapping
    public ResponseEntity<?> create(HttpServletRequest request, @Valid @RequestBody CapsuleRequestDTO capsuleRequestDTO){

        String clientIp = request.getRemoteAddr();

        if (!rateLimiterService.isAllowed(clientIp)) {
            return ResponseEntity.status(429).body("Limite de requisições excedido");
        }

        if (capsuleRequestDTO.getHiddenField() != null && !capsuleRequestDTO.getHiddenField().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Suspicious access detected");
        }

        if (capsuleRequestDTO.getDestinationEmail() == null || !capsuleRequestDTO.getDestinationEmail().matches("^[\\w\\-.]+@[\\w\\-.]+\\.\\w{2,}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }

        if (capsuleRequestDTO.getMessage() == null || capsuleRequestDTO.getMessage().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message cannot be empty");
        }

        if (capsuleRequestDTO.getDateSent() == null || capsuleRequestDTO.getDateSent().isBefore(LocalDate.now().plusDays(1))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be in the future");
        }

        Capsule capsule = new Capsule(capsuleRequestDTO.getDestinationEmail(), capsuleRequestDTO.getMessage(), capsuleRequestDTO.getDateSent());
        Capsule saved = capsuleService.save(capsule);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}