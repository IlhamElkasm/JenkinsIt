package com.app.Service;

import com.app.Model.Panne;

import java.util.List;

public interface PanneService {

    Panne addPanne(Panne panne);
    Panne updatePanne(Long idPann, Panne panneDetails);
    void deletePanne(Long idPann);
    List<Panne> getAllPanne();

//    List<Historique> getHistoriqueByEquipement(Long idEquipement);

}
