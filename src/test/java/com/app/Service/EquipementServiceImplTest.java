package com.app.Service;

import com.app.Model.Equipement;
import com.app.Model.EtatEquipement;
import com.app.Repository.EquipementRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EquipementServiceImplTest {

    @Autowired
    private EquipementServiceImpl equipementService;

    @Autowired
    private EquipementRepository equipementRepository;

    @BeforeEach
    public void setUp() {
        equipementRepository.deleteAll(); // Nettoyer la base de données avant chaque test
    }


    @Test
    void createEquipement() {
        Equipement equipement = new Equipement();
        equipement.setNom("Test Equipement");
        equipement.setDescription("Description");
        equipement.setEtat(EtatEquipement.EN_SERVICE); // Utilisez l'énumération correcte

        Equipement result = equipementService.CreateEquipement(equipement);
        assertNotNull(result.getIdEquipement()); // Remplacez `getId` par `getIdEquipement`
        assertEquals(equipement.getNom(), result.getNom());
        assertEquals(equipement.getEtat(), result.getEtat()); // Vérifiez également l'état
    }

    @Test
    void getAllEquipements() {
        Equipement equipement1 = new Equipement();
        equipement1.setNom("Equipement 1");
        equipement1.setEtat(EtatEquipement.EN_SERVICE);
        equipementRepository.save(equipement1);

        Equipement equipement2 = new Equipement();
        equipement2.setNom("Equipement 2");
        equipement2.setEtat(EtatEquipement.HORS_SERVICE);
        equipementRepository.save(equipement2);

        List<Equipement> equipements = equipementService.getAllEquipements();
        assertEquals(2, equipements.size());
    }

    @Test
    void deleteEquipements() {
        Equipement equipement = new Equipement();
        equipement.setNom("Equipement à supprimer");
        equipement.setEtat(EtatEquipement.EN_SERVICE);
        Equipement savedEquipement = equipementRepository.save(equipement);

        equipementService.deleteEquipements(savedEquipement.getIdEquipement());

        assertFalse(equipementRepository.findById(savedEquipement.getIdEquipement()).isPresent());
    }

    @Test
    void updateEquipements() {
        // Create and save an equipement
        Equipement equipement = new Equipement();
        equipement.setNom("Equipement à mettre à jour");
        equipement.setDescription("Ancienne description");
        equipement.setEtat(EtatEquipement.EN_SERVICE);
        Equipement savedEquipement = equipementRepository.save(equipement);

        // Create a new Equipement object with updated values
        Equipement updatedEquipement = new Equipement();
        updatedEquipement.setNom("Equipement mis à jour");
        updatedEquipement.setDescription("Nouvelle description");
        updatedEquipement.setEtat(EtatEquipement.HORS_SERVICE);

        // Call the update method with the updated Equipement object and the id
        Equipement result = equipementService.updateEquipements(updatedEquipement, savedEquipement.getIdEquipement());

        // Assertions to verify the update
        assertNotNull(result);
        assertEquals(savedEquipement.getIdEquipement(), result.getIdEquipement());
        assertEquals("Equipement mis à jour", result.getNom());
        assertEquals("Nouvelle description", result.getDescription());
        assertEquals(EtatEquipement.HORS_SERVICE, result.getEtat());
    }
}