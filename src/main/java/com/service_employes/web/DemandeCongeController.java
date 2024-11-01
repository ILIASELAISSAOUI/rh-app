package com.service_employes.web;

import com.service_employes.dtos.DemandeCongeDTO;
import com.service_employes.services.DemandeCongesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Conges")
@CrossOrigin(origins = "*")

public class DemandeCongeController {

    private DemandeCongesService demandeCongesService;

    public DemandeCongeController(DemandeCongesService demandeCongesService) {
        this.demandeCongesService=demandeCongesService;
    }

    @GetMapping
    public List<DemandeCongeDTO> getAllDemandeConges() {
        List<DemandeCongeDTO> demandes = demandeCongesService.getDemandeConges();
        return demandes;
    }

    @GetMapping("/{id}")
    public List<DemandeCongeDTO> getDemandeConge(@PathVariable Long id) {
        return demandeCongesService.getDemandeCongesByEmploye(id);
    }

    @PutMapping("/{id}/approuver")
    public DemandeCongeDTO approuverDemande(@PathVariable Long id) {
        return demandeCongesService.ApprouveDemande(id);
    }

    @PutMapping("/{id}/refuser")
    public DemandeCongeDTO refuserDemande(@PathVariable Long id, @RequestBody String motif) {
        return demandeCongesService.refuserDemande(id, motif);
    }

    @PostMapping("/{employeId}")
    public DemandeCongeDTO addDemandeConge(@PathVariable Long employeId,@RequestBody DemandeCongeDTO demandeCongeDTO) {
        return demandeCongesService.addDemandeConge(employeId,demandeCongeDTO);
    }
}
