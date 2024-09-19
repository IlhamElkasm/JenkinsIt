package com.app.Service;

import com.app.Model.Panne;
import com.app.Repository.EquipementRepository;
import com.app.Repository.PanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PanneServiceImpl implements PanneService {

    @Autowired
    private PanneRepository panneRepository;

    public Panne addPanne(Panne panne) {
        Panne savedPanne = panneRepository.save(panne);
        return savedPanne;
    }

    public Panne updatePanne(Long id, Panne panneDetails) {
        Panne panne = panneRepository.findById(id).orElseThrow(() -> new RuntimeException("Panne not found"));
        panne.setDescription(panneDetails.getDescription());
        Panne updatedPanne = panneRepository.save(panne);
        return updatedPanne;
    }

    @Override
    public List<Panne> getAllPanne() {
        return  panneRepository.findAll();
    }

    @Override
    public void deletePanne(Long idPann) {
        panneRepository.deleteById(idPann);
    }

}
