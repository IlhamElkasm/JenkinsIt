package com.app.Service;

import com.app.Model.Equipement;

import java.util.List;

public interface EquipementService {
    Equipement CreateEquipement(Equipement equipement);
    List<Equipement> getAllEquipements();
    void deleteEquipements(Long idEquipement) ;
    Equipement updateEquipements(Equipement equipement, Long idEquipement);
}
