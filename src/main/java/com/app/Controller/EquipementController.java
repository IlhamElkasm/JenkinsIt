package com.app.Controller;

import com.app.Model.Equipement;
import com.app.Model.Personne;
import com.app.Service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/auth/Admin")
@CrossOrigin(origins = "http://localhost:4200/")
public class EquipementController {

    @Autowired
    private EquipementService equipementService;

    @PostMapping("/CreateEquipement")
    public Equipement creerEvent(@RequestBody Equipement equipement) {
        return equipementService.CreateEquipement(equipement);
    }

    @GetMapping("/ShowAll")
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    @DeleteMapping("/{idEquipement}")
    public void deleteCompte(@PathVariable Long idEquipement) {
        equipementService.deleteEquipements(idEquipement);
    }

    @PutMapping("/eventsPut/{idEquipement}")
    public Equipement updateEquipement(@PathVariable Long idEquipement, @RequestBody Equipement equipement) {
        return equipementService.updateEquipements(equipement, idEquipement);
    }
}
