package dev.capsule.timeCapsule.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import dev.capsule.timeCapsule.model.Capsule;


@Component
public class EmailScheduler {

    private CapsuleService capsuleService;
    
    public EmailScheduler(CapsuleService capsuleService) {
        this.capsuleService = capsuleService;
    }

    @Scheduled(fixedRate = 86400000) 
    public void checkMessages() { 
        LocalDate date = LocalDate.now();
        List<Capsule> capsules = capsuleService.getAllByDate(date);
        capsuleService.routine(capsules);
    }

}
