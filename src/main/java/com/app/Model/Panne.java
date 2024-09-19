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
public class Panne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPanne;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "panne")
    private List<Ticket> tickets;
}
