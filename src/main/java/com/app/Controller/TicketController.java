package com.app.Controller;

import com.app.Model.*;
import com.app.Repository.TechnicienRepository;
import com.app.Repository.UserRepository;
import com.app.Service.PanneService;
import com.app.Service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin(origins = "http://localhost:4200/")
public class TicketController {

    @Autowired
    private TickerService ticketService;

    @Autowired
    private PanneService panneService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicienRepository technicienRepository;

    @PostMapping("User/Add")
    public ResponseEntity<String> creerTicket(@RequestBody Ticket ticket, @AuthenticationPrincipal Utilisateur user) {
        try {
            Utilisateur utilisateurVerifie = userRepository
                    .findById(user.getId())
                    .orElseThrow(()-> new RuntimeException("utilisateur not found"));

            ticketService.creerTicket(ticket,user);

            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }

    @PutMapping("Admin/{ticketId}/assign")
    public ResponseEntity<String> assignerTicket(@PathVariable Long ticketId, @RequestParam Long technicienId) {
        try {
            TechnicienIT technicien = technicienRepository.findById(technicienId)
                    .orElseThrow(() -> new RuntimeException("Technicien not found"));
            ticketService.assignerTicket(ticketId, technicien);
            return ResponseEntity.ok("Ticket assigned successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ticket not assigned: " + e.getMessage());
        }
    }

    @GetMapping("User/ShowTicket")
    public List<Ticket> getAllTicket() {
        return ticketService.getAllTicket();
    }
}
