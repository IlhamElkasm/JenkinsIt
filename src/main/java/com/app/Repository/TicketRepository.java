package com.app.Repository;

import com.app.Model.Panne;
import com.app.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTechnicienId(Long technicienId);
}
