package com.app.Repository;

import com.app.Model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {

    Optional<Equipement> findById(Long idEquipement);

    @Query(value = "select count(*) from Equipement ")
    long count();
}
