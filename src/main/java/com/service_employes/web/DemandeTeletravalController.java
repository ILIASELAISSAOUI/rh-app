package com.service_employes.web;

import com.service_employes.dtos.DemandeTeletravailDTO;
import com.service_employes.dtos.JourDTO;
import com.service_employes.services.DemandeTeletravailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teletravail")
@CrossOrigin(origins = "*")
public class DemandeTeletravalController {
    private DemandeTeletravailService demandeTeletravailService;

    public DemandeTeletravalController(DemandeTeletravailService demandeTeletravailService) {
        this.demandeTeletravailService = demandeTeletravailService;
    }

    @GetMapping
    public List<DemandeTeletravailDTO> getAllDemandeTeletravail() {
        List<DemandeTeletravailDTO> demandes = demandeTeletravailService.getAllDemandeTeletravail();
        return demandes;
    }

    @GetMapping("/{employeId}/demandes")
    public List<DemandeTeletravailDTO> getDemandeTeletravail(@PathVariable long employeId) {
        return demandeTeletravailService.getDemandeTeletravailsByEmploye(employeId);
    }

    @GetMapping("/{demandeId}/jours")
    public List<JourDTO> getJoursByDemandeTeletravail(@PathVariable long demandeId) {
        return demandeTeletravailService.getJoursByDemandeTeletravails(demandeId);
    }

    @PutMapping("/{demandeId}/approuver")
    public boolean approuveDemandeTeletravail(@PathVariable long demandeId){
        return demandeTeletravailService.updateEtatDemandeTeletravail(demandeId);
    }

    @PutMapping("/jour/{jourId}/approuver")
    public boolean approuverJour(@PathVariable Long jourId) {
        return demandeTeletravailService.approuveJour(jourId);
    }

    @PutMapping("/jour/{jourId}/rejeter")
    public boolean rejectJour(@PathVariable Long jourId,@RequestBody String motif) {
        return demandeTeletravailService.rejectJour(jourId,motif);
    }

    @PutMapping("/{demandeId}/approuver-tous")
    public void approuverAll(@PathVariable Long demandeId) {
        demandeTeletravailService.approuveAll(demandeId);
    }

    @PutMapping("/{demandeId}/rejeter-tous")
    public void rejectAll(@PathVariable Long demandeId) {
        demandeTeletravailService.rejectAll(demandeId);
    }

    @PostMapping("/{employeId}")
    public DemandeTeletravailDTO addDemandeTeletravail(@PathVariable Long employeId,@RequestBody DemandeTeletravailDTO demandeTeletravailDTO) {
        return demandeTeletravailService.createDemandeTeletravail(employeId,demandeTeletravailDTO);
    }

    @PostMapping("/{demandeId}/jours")
    public DemandeTeletravailDTO addJour(@PathVariable Long demandeId, @RequestBody List<JourDTO> jours) {
            return demandeTeletravailService.addJoursToDemandeTeletravail(demandeId, jours);
    }

}
