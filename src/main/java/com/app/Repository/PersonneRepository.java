package com.app.Repository;

import com.app.Model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    Optional<Personne> findByEmail(String email);
}


