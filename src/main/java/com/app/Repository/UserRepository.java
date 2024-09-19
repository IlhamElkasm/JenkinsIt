package com.app.Repository;

import com.app.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur,Long> {
}
