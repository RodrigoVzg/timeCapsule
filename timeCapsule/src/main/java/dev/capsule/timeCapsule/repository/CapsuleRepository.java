package dev.capsule.timeCapsule.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.capsule.timeCapsule.model.Capsule;

public interface CapsuleRepository extends JpaRepository<Capsule, Long> {

    @Query("SELECT c FROM Capsule c WHERE c.dateSent = :date AND sent = false")
    List<Capsule> findByDate(@Param("date") LocalDate date);

}
