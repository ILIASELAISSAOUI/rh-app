package com.service_employes.services;

import com.service_employes.dtos.DemandeCongeDTO;
import com.service_employes.entities.DemandeConge;
import com.service_employes.outil.EtatDemande;

import java.util.List;

public interface DemandeCongesService {

    public DemandeCongeDTO addDemandeConge(Long employeId,DemandeCongeDTO demandeCongeDTO);
    public List<DemandeCongeDTO> getDemandeConges();
    public List<DemandeCongeDTO> getDemandeCongesByEmploye(Long employeId);
    public DemandeCongeDTO ApprouveDemande(Long id) ;
    public DemandeCongeDTO refuserDemande(Long id,String motif);

}
