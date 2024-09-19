package com.app.Controller;

import com.app.Model.Panne;
import com.app.Service.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/Admin")
@CrossOrigin(origins = "http://localhost:4200/")
public class PanneController {

    @Autowired
    private PanneService panneService;

    @PostMapping("/panne")
    public Panne createPanne(@RequestBody Panne panne) {
        return panneService.addPanne(panne);
    }

    @PutMapping("/{id}")
    public Panne updatePanne(@PathVariable Long id, @RequestBody Panne panneDetails) {
        return panneService.updatePanne(id, panneDetails);
    }

    @GetMapping("/ShowAllPanne")
    public List<Panne> getAllPanne() {
        return panneService.getAllPanne();
    }

    @DeleteMapping("/delete/{idpanne}")
    public void deletePanne(@PathVariable Long idpanne) {
        panneService.deletePanne(idpanne);
    }
}
