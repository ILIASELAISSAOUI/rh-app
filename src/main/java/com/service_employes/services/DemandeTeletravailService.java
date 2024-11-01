package com.service_employes.services;

import com.service_employes.dtos.DemandeTeletravailDTO;
import com.service_employes.dtos.JourDTO;

import java.util.List;

public interface DemandeTeletravailService {

    //public JourDTO addJour(Long demandeId, JourDTO jourTeletravail);
    public DemandeTeletravailDTO createDemandeTeletravail(Long employeId,DemandeTeletravailDTO demandeTeletravailDTO);
    public boolean updateEtatDemandeTeletravail(Long demandeId);
    public DemandeTeletravailDTO addJoursToDemandeTeletravail(Long demandeId, List<JourDTO> jourTeletravails);
    public List<DemandeTeletravailDTO> getAllDemandeTeletravail() ;
    public List<DemandeTeletravailDTO> getDemandeTeletravailsByEmploye(Long employeId);
    public List<JourDTO> getJoursByDemandeTeletravails(Long demandeId);
    public boolean approuveJour(Long idJour);
    public boolean rejectJour(Long idJour,String motif) ;
    public void approuveAll(Long idDemandeTeletravail);
    public void rejectAll(Long idDemandeTeletravail) ;
    public void rejectExpiredDemandes();
}
