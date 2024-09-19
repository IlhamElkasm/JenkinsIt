package com.app.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTi;
    private String description;
    private LocalDate dateCréation;

    @Enumerated(EnumType.STRING)
    private EtatTicket etatTicket;
    @ManyToOne
    @JoinColumn(name = "idPanne")
    private Panne panne;

    @ManyToOne
    @JoinColumn(name = "idEquipement")
    private Equipement equipement;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idtechnicien")
    private TechnicienIT technicien;
}
