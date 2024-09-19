package com.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Utilisateur extends  Personne{

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Ticket> tickets;
}
