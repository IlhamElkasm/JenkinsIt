package com.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;
    private String nom;
    private String  description;
    private EtatEquipement etat;



    @JsonIgnore
    @OneToMany(mappedBy = "equipement")
    private List<Ticket> tickets;

}
